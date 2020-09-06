/*
 * Seerema Business Solutions - http://www.seerema.com/
 * 
 * Copyright 2020 IvaLab Inc. and by respective contributors (see below).
 * 
 * Released under the LGPL v3 or higher
 * See http://www.gnu.org/licenses/lgpl.txt
 *
 * Contributors:
 * 
 */

package com.seerema.shared.jpa.status.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.UnexpectedRollbackException;

import com.seerema.base.WsSrvException;
import com.seerema.shared.Constants;
import com.seerema.shared.dto.EntityDto;
import com.seerema.shared.dto.EntityExDto;
import com.seerema.shared.dto.ModuleDto;
import com.seerema.shared.dto.StatusDto;
import com.seerema.shared.dto.StatusHistoryDto;
import com.seerema.shared.jpa.base.model.DbEntity;
import com.seerema.shared.jpa.base.service.EntityService;
import com.seerema.shared.jpa.base.service.impl.AbstractEntityServiceImpl;
import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.shared.jpa.status.model.Status;
import com.seerema.shared.jpa.status.model.StatusHistory;
import com.seerema.shared.jpa.status.repo.EntityExRepo;
import com.seerema.shared.jpa.status.repo.StatusHistoryRepo;
import com.seerema.shared.jpa.status.repo.StatusRepo;
import com.seerema.shared.jpa.status.service.EntityStatusService;
import com.seerema.shared.jpa.status.shared.ErrorCodes;
import com.seerema.shared.rest.response.BaseGoodResponse;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Implementation for Abstract Entity Service
 * The exact implementation required defining the New Status
 * 
 */

public abstract class AbstractEntityStatusServiceImpl
    extends AbstractEntityServiceImpl<EntityEx, EntityExDto>
    implements EntityStatusService<EntityEx, EntityExDto> {

  protected abstract String getNewStatus();

  @Autowired
  EntityService<DbEntity, EntityDto> _srv;

  @Autowired
  EntityExRepo _repo;

  @Autowired
  StatusRepo _srepo;

  @Autowired
  StatusHistoryRepo _shrepo;

  @Autowired
  private ModuleDto _mod;

  @Override
  public DataGoodResponse createEntity(EntityExDto dto) throws WsSrvException {
    try {
      return createEntityEx(dto, Constants.ANONYMOUS_USER);
    } catch (UnexpectedRollbackException e) {
      throw new WsSrvException(ErrorCodes.ERROR_CREATE_ENTITY_EX.name(), e);
    }
  }

  @Override
  public DataGoodResponse createEntity(EntityExDto dto, String userName)
      throws WsSrvException {
    try {
      return createEntityEx(dto, userName);
    } catch (UnexpectedRollbackException e) {
      throw new WsSrvException(ErrorCodes.ERROR_CREATE_ENTITY_EX_USER.name(),
          e);
    }
  }

  @Override
  public DataGoodResponse updateEntity(EntityExDto dto) throws WsSrvException {
    try {
      return updateEntityEx(dto, Constants.ANONYMOUS_USER, false);
    } catch (UnexpectedRollbackException e) {
      throw new WsSrvException(ErrorCodes.ERROR_UPDATE_ENTITY_EX.name(), e);
    }
  }

  @Override
  public DataGoodResponse updateEntity(EntityExDto dto, String userName,
      Boolean allowOverride) throws WsSrvException {

    try {
      return updateEntityEx(dto, userName, allowOverride);
    } catch (UnexpectedRollbackException e) {
      throw new WsSrvException(ErrorCodes.ERROR_UPDATE_ENTITY_EX_USER.name(),
          e);
    }
  }

  @Override
  public DataGoodResponse readEntities(String username) throws WsSrvException {
    Iterable<EntityEx> entities;

    try {
      entities = _repo.findAllByUserName(username);
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_READ_ENTITIES_EX_USER.name(), e);
    }

    return new DataGoodResponse(
        getEntityMapper().map(entities, EntityExDto.class));
  }

  @Override
  public DataGoodResponse readEntity(Integer id, String username)
      throws WsSrvException {
    Optional<EntityEx> entity;

    try {
      entity = _repo.findByIdAndUserName(id, username);
    } catch (DataAccessException e) {
      throw throwError(getEntityReadErrorCode(), e);
    }

    return new DataGoodResponse(entity.isPresent()
        ? getEntityMapper().map(entity.get(), getEntityDtoClass()) : null);
  }

  @Override
  public BaseGoodResponse deleteEntity(Integer id) throws WsSrvException {
    return deleteEntityEx(id);
  }

  @Transactional
  private BaseGoodResponse deleteEntityEx(Integer id) throws WsSrvException {
    // Delete master record first in order do not break foreign key
    super.deleteEntity(id);

    // Delete slave record
    return _srv.deleteEntity(id);
  }

  @Transactional
  private DataGoodResponse createEntityEx(EntityExDto dto, String userName)
      throws WsSrvException {
    // Find ID for new status
    Status status = _srepo.findByNameAndModuleId(getNewStatus(), _mod.getId());

    // Inject new status and status history
    dto.setStatus(new StatusDto(status.getId()));

    // Inject user name directly into DB Entity since it's not mapped by DTO
    EntityEx entity = insertUserName(dto, userName);

    // First save slave entity
    DbEntity dbe = _srv.createRawEntity(entity.getDbEntity());
    entity.setId(dbe.getId());

    // Save master entity after
    EntityEx ex = createEntity(entity);

    EntityExDto dnew = getEntityMapper().map(ex, EntityExDto.class);

    // Than save first history for new status
    insertStatusHistory(dnew, userName);

    return new DataGoodResponse(dnew);
  }

  @Transactional
  private DataGoodResponse updateEntityEx(EntityExDto dto, String userName,
      Boolean allowOverride) throws WsSrvException {
    // Get the previous status
    EntityEx dprev = readRawEntity(dto.getId());

    // Quick Check
    if (dprev == null)
      throw new WsSrvException(ErrorCodes.INVALID_UPDATE_ENTITY_EX.name(),
          "Unable update non-existing entity",
          "Entity with id #" + dto.getId() + " not found");

    // Check if username matches
    if (!(dprev.getUserName().equals(userName) || allowOverride))
      throw new WsSrvException(ErrorCodes.USER_ACCESS_DENIED.name(),
          "Username for update doesn't match existing", "Existing username [" +
              dprev.getUserName() + "] != request username [" + userName + "]");

    // Remember old status
    int status = dprev.getStatus().getId();

    // Inject same user name directly into DB Entity since it's not mapped by DTO
    EntityEx entity = insertUserName(dto, dprev.getUserName());

    // First save slave entity
    DbEntity dbe = _srv.updateEntity(entity.getDbEntity());
    entity.setDbEntity(dbe);

    // After that save master entity
    updateEntity(entity);
    EntityExDto dnew = getEntityMapper().map(readRawEntity(entity.getId()),
        getEntityDtoClass());

    // Insert hew history if status changed
    if (status != dto.getStatus().getId())
      insertStatusHistory(dnew, userName);

    return new DataGoodResponse(dnew);
  }

  private EntityEx insertUserName(EntityExDto dto, String userName)
      throws WsSrvException {
    @SuppressWarnings("unchecked")
    Class<EntityEx> clazz = (Class<EntityEx>) getDtoDestClass(dto);

    EntityEx entity = getEntityMapper().convert(dto, clazz);

    // Check if username needs to be injected
    if (userName != null)
      entity.setUserName(userName);

    return entity;
  }

  private void insertStatusHistory(EntityExDto dto, String userName)
      throws WsSrvException {

    // Create status history raw record
    StatusHistoryDto sh = new StatusHistoryDto();
    sh.setStatus(dto.getStatus());
    sh.setCreated(LocalDateTime.now(ZoneOffset.UTC));

    @SuppressWarnings("unchecked")
    Class<StatusHistory> clazz = (Class<StatusHistory>) getDtoDestClass(sh);

    // Entity and created date needs to be injected directly into db model since it's not mapped by dto
    StatusHistory model = getEntityMapper().convert(sh, clazz);
    model.setEntity(getEntity(dto));
    model.setUserName(userName);

    StatusHistory saved;
    try {
      saved = _shrepo.save(model);
      sh.setId(saved.getId());
      sh.setUserName(userName);
    } catch (DataAccessException e) {
      throw throwError(
          com.seerema.shared.jpa.status.shared.ErrorCodes.ERROR_CREATE_STATUS_HISTORY
              .name(),
          e);
    }

    dto.addStatusHistory(sh);
  }

  private EntityEx getEntity(EntityExDto dto) throws WsSrvException {
    @SuppressWarnings("unchecked")
    Class<EntityEx> clazz = (Class<EntityEx>) getDtoDestClass(dto);

    // Instantiate new entity
    EntityEx entity;
    try {
      entity = clazz.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new WsSrvException(ErrorCodes.ERROR_INIT_ENTITY_EX.name(), e);
    }

    entity.setId(dto.getId());
    return entity;
  }

  @Override
  protected Iterable<EntityEx> findAll() {
    return _repo.findAllByDbEntityModuleId(_mod.getId());
  }

  @Override
  protected Class<EntityExDto> getEntityDtoClass() {
    return EntityExDto.class;
  }

  @Override
  protected CrudRepository<EntityEx, Integer> getEntityRepo() {
    return _repo;
  }

  @Override
  protected String getEntityCreateErrorCode() {
    return ErrorCodes.ERROR_CREATE_ENTITY_EX.name();
  }

  @Override
  protected String getEntityUpdateErrorCode() {
    return ErrorCodes.ERROR_UPDATE_ENTITY_EX.name();
  }

  @Override
  protected String getEntityReadErrorCode() {
    return ErrorCodes.ERROR_READ_ENTITY_EX.name();
  }

  @Override
  protected String getEntitiesReadErrorCode() {
    return ErrorCodes.ERROR_READ_ENTITIES_EX.name();
  }

  @Override
  protected String getEntityDeleteErrorCode() {
    return ErrorCodes.ERROR_DELETE_ENTITY_EX.name();
  }
}
