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

import java.util.List;
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
import com.seerema.shared.dto.EntityStatusHistoryDto;
import com.seerema.shared.dto.EntityUserHistoryDto;
import com.seerema.shared.dto.ModuleDto;
import com.seerema.shared.dto.StatusDto;
import com.seerema.shared.jpa.base.model.DbEntity;
import com.seerema.shared.jpa.base.model.User;
import com.seerema.shared.jpa.base.service.EntityService;
import com.seerema.shared.jpa.base.service.impl.AbstractEntityUserServiceImpl;
import com.seerema.shared.jpa.base.service.impl.ServiceData;
import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.shared.jpa.status.model.EntityStatusHistory;
import com.seerema.shared.jpa.status.model.EntityUserHistory;
import com.seerema.shared.jpa.status.model.Status;
import com.seerema.shared.jpa.status.repo.EntityExRepo;
import com.seerema.shared.jpa.status.repo.EntityStatusHistoryRepo;
import com.seerema.shared.jpa.status.repo.EntityUserHistoryRepo;
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
    extends AbstractEntityUserServiceImpl<EntityEx, EntityExDto>
    implements EntityStatusService<EntityEx, EntityExDto> {

  protected abstract String getNewStatus();

  @Autowired
  private EntityService<DbEntity, EntityDto> _srv;

  @Autowired
  private EntityExRepo _repo;

  @Autowired
  private StatusRepo _srepo;

  @Autowired
  private EntityStatusHistoryRepo _shrepo;

  @Autowired
  private EntityUserHistoryRepo _uhrepo;

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

  protected List<EntityExDto> readMappedEntities(String username)
      throws WsSrvException {
    Iterable<EntityEx> entities;

    try {
      entities = _repo.findAllByUserName(username);
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_READ_ENTITIES_EX_USER.name(), e);
    }

    return getEntityMapper().map(entities, EntityExDto.class);
  }

  @Override
  public DataGoodResponse readEntities(String username) throws WsSrvException {
    return new DataGoodResponse(readMappedEntities(username));
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
    User owner = findUser(userName);
    EntityEx entity = insertUser(dto, owner);

    // First save slave entity
    DbEntity dbe = _srv.createRawEntity(entity.getDbEntity());
    entity.setId(dbe.getId());

    // Save master entity after
    EntityEx ex = createEntity(entity);

    EntityExDto dnew = getEntityMapper().map(ex, EntityExDto.class);

    // Save first history for new status
    insertEntityStatusHistory(dnew, owner);

    // Save initial owner
    insertEntityOwnerHistory(dnew, owner);

    return new DataGoodResponse(dnew);
  }

  @Transactional
  private DataGoodResponse updateEntityEx(EntityExDto dto, String userName,
      Boolean allowOverride) throws WsSrvException {
    ServiceData sdata = checkUserAccess(dto, userName, allowOverride);

    EntityEx dprev = (EntityEx) sdata.getEntity();

    // Remember old status
    int status = dprev.getStatus().getId();

    // Remember old user
    int uid = dprev.getUser().getId();

    // Inject user directly into DB Entity since it's not mapped by DTO
    // If user is not defined than take existing user
    boolean fuser = dto.getUser() != null;
    EntityEx entity = insertUser(dto,
        fuser ? new User(dto.getUser().getId(), dto.getUser().getName())
            : sdata.getUser());

    // First save slave entity
    DbEntity dbe = _srv.updateEntity(entity.getDbEntity());
    entity.setDbEntity(dbe);

    // After that save master entity
    updateEntity(entity);
    EntityExDto dnew = getEntityMapper().map(readRawEntity(entity.getId()),
        getEntityDtoClass());

    // Insert hew history if status changed
    if (status != dto.getStatus().getId())
      insertEntityStatusHistory(dnew, sdata.getUser());

    // Insert new owner history if owner changed
    if (fuser && uid != dto.getUser().getId())
      insertEntityOwnerHistory(dnew, sdata.getUser());

    return new DataGoodResponse(dnew);
  }

  private void insertEntityStatusHistory(EntityExDto dto, User user)
      throws WsSrvException {

    EntityStatusHistory saved;
    try {
      saved = _shrepo.save(new EntityStatusHistory(new EntityEx(dto.getId()),
          new Status(dto.getStatus().getId(), dto.getStatus().getName()),
          user));
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_CREATE_STATUS_HISTORY.name(), e);
    }

    dto.addStatusHistory(
        getEntityMapper().map(saved, EntityStatusHistoryDto.class));
  }

  private void insertEntityOwnerHistory(EntityExDto dto, User user)
      throws WsSrvException {

    EntityUserHistory saved;
    try {
      saved = _uhrepo.save(new EntityUserHistory(new EntityEx(dto.getId()),
          new User(dto.getUser().getId(), dto.getUser().getName()), user));
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_CREATE_STATUS_HISTORY.name(), e);
    }

    dto.addOwnerHistory(
        getEntityMapper().map(saved, EntityUserHistoryDto.class));
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
