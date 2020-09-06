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

package com.seerema.shared.jpa.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.seerema.base.WsSrvException;
import com.seerema.shared.dto.EntityDto;
import com.seerema.shared.dto.ModuleDto;
import com.seerema.shared.jpa.base.model.DbEntity;
import com.seerema.shared.jpa.base.model.EntityField;
import com.seerema.shared.jpa.base.repo.DbEntityRepo;
import com.seerema.shared.jpa.base.repo.EntityFieldRepo;
import com.seerema.shared.jpa.base.service.EntityService;
import com.seerema.shared.jpa.base.shared.ErrorCodes;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Implementation for Abstract Entity Service for entities with dynamic Fields
 * 
 * @param <EntityDto> AbstractEntityDto
 */
@Service
public class BaseEntityServiceImpl
    extends AbstractEntityModuleServiceImpl<DbEntity, EntityDto>
    implements EntityService<DbEntity, EntityDto> {

  @Autowired
  private EntityFieldRepo _frepo;

  @Autowired
  private DbEntityRepo _drepo;

  @Autowired
  private ModuleDto _mod;

  @Override
  public DataGoodResponse createEntity(EntityDto dto) throws WsSrvException {
    @SuppressWarnings("unchecked")
    Class<DbEntity> clazz = (Class<DbEntity>) getDtoDestClass(dto);

    try {
      return new DataGoodResponse(getEntityMapper().map(
          createEntityEx(getEntityMapper().convert(dto, clazz)),
          dto.getClass()));
    } catch (DataAccessException e) {
      throw throwError(getEntityCreateErrorCode(), e);
    }
  }

  @Override
  public DataGoodResponse readEntity(Integer id) throws WsSrvException {
    return new DataGoodResponse(
        getEntityMapper().map(readRawEntity(id), getEntityDtoClass()));
  }

  @Override
  public DataGoodResponse updateEntity(EntityDto dto) throws WsSrvException {
    @SuppressWarnings("unchecked")
    Class<DbEntity> clazz = (Class<DbEntity>) getDtoDestClass(dto);

    return new DataGoodResponse(getEntityMapper().map(
        updateEntity(getEntityMapper().convert(dto, clazz)), dto.getClass()));
  }

  @Override
  public DbEntity updateEntity(DbEntity entity) throws WsSrvException {
    // Before save update entity in each field
    List<EntityField> fields = entity.getEntityFields();
    if (fields != null)
      for (EntityField field : fields)
        field.setEntity(entity);

    // Finally save entity
    return super.updateEntity(entity);
  }

  @Override
  public DbEntity createEntity(DbEntity entity) throws WsSrvException {
    return createEntityEx(entity);
  }

  @Transactional
  private DbEntity createEntityEx(DbEntity entity) throws WsSrvException {
    return createRawEntity(entity);
  }

  @Override
  public DbEntity createRawEntity(DbEntity entity) throws WsSrvException {
    // Temporarily remove entity fields
    List<EntityField> fields = entity.getEntityFields();
    entity.setEntityFields(null);

    // Save entity first
    DbEntity ent = super.createEntity(entity);

    // Save entity fields one by one
    if (fields != null) {
      List<EntityField> list = new ArrayList<>();

      for (EntityField field : fields) {
        field.setEntity(ent);
        // Create each individual entity field
        list.add(_frepo.save(field));
      }

      ent.setEntityFields(list);
    }

    return ent;
  }

  @Override
  protected CrudRepository<DbEntity, Integer> getEntityRepo() {
    return _drepo;
  }

  @Override
  protected Iterable<DbEntity> findAll() {
    return _drepo.findAllByModuleIdOrderByNameAsc(_mod.getId());
  }

  @Override
  protected Class<EntityDto> getEntityDtoClass() {
    return EntityDto.class;
  }

  @Override
  protected String getEntityCreateErrorCode() {
    return ErrorCodes.ERROR_CREATE_ENTITY.name();
  }

  @Override
  protected String getEntityReadErrorCode() {
    return ErrorCodes.ERROR_READ_ENTITY.name();
  }

  @Override
  protected String getEntitiesReadErrorCode() {
    return ErrorCodes.ERROR_READ_ENTITIES.name();
  }

  @Override
  protected String getEntityUpdateErrorCode() {
    return ErrorCodes.ERROR_UPDATE_ENTITY.name();
  }

  @Override
  protected String getEntityDeleteErrorCode() {
    return ErrorCodes.ERROR_DELETE_ENTITY.name();
  }
}
