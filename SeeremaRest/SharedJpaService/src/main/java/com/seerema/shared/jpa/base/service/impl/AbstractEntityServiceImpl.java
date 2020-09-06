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

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.seerema.base.WsSrvException;
import com.seerema.shared.dto.AbstractEntityDto;
import com.seerema.shared.jpa.base.model.BaseEntity;
import com.seerema.shared.jpa.base.service.BaseEntityService;
import com.seerema.shared.rest.response.BaseGoodResponse;
import com.seerema.shared.rest.response.DataGoodResponse;
import com.seerema.srv.shared.mapper.EntityMapper;

/**
 * Partial Implementation for BaseEntity Service
 *
 * @param <T1> Entity
 * @param <T2> EntityDto
 */

public abstract class AbstractEntityServiceImpl<T1 extends BaseEntity, T2 extends AbstractEntityDto>
    extends BaseDbAccessService implements BaseEntityService<T1, T2> {

  @Autowired
  private EntityMapper entityMapper;

  protected abstract CrudRepository<T1, Integer> getEntityRepo();

  protected abstract Iterable<T1> findAll();

  protected abstract Class<T2> getEntityDtoClass();

  protected abstract String getEntityCreateErrorCode();

  protected abstract String getEntityReadErrorCode();

  protected abstract String getEntitiesReadErrorCode();

  protected abstract String getEntityUpdateErrorCode();

  protected abstract String getEntityDeleteErrorCode();

  @Override
  public DataGoodResponse createEntity(T2 dto) throws WsSrvException {
    @SuppressWarnings("unchecked")
    Class<T1> clazz = (Class<T1>) getDtoDestClass(dto);

    try {
      return new DataGoodResponse(entityMapper
          .map(createEntity(entityMapper.convert(dto, clazz)), dto.getClass()));
    } catch (DataAccessException e) {
      throw throwError(getEntityCreateErrorCode(), e);
    }
  }

  @Override
  public T1 createEntity(T1 entity) throws WsSrvException {
    try {
      return getEntityRepo().save(entity);
    } catch (DataAccessException e) {
      throw throwError(getEntityCreateErrorCode(), e);
    }
  }

  @Override
  public T1 readRawEntity(Integer id) throws WsSrvException {
    Optional<T1> entity;

    try {
      entity = getEntityRepo().findById(id);
    } catch (DataAccessException e) {
      throw throwError(getEntityReadErrorCode(), e);
    }

    return entity.isPresent() ? entity.get() : null;
  }

  @Override
  public DataGoodResponse readEntity(Integer id) throws WsSrvException {
    return new DataGoodResponse(
        entityMapper.map(readRawEntity(id), getEntityDtoClass()));
  }

  @Override
  public DataGoodResponse readEntities() throws WsSrvException {
    Iterable<T1> entities;

    try {
      entities = findAll();
    } catch (DataAccessException e) {
      throw throwError(getEntityReadErrorCode(), e);
    }

    return new DataGoodResponse(
        entityMapper.map(entities, getEntityDtoClass()));
  }

  @Override
  public DataGoodResponse updateEntity(T2 dto) throws WsSrvException {
    @SuppressWarnings("unchecked")
    Class<T1> clazz = (Class<T1>) getDtoDestClass(dto);

    try {
      return new DataGoodResponse(entityMapper.map(
          updateEntity(entityMapper.convert(dto, clazz)), getEntityDtoClass()));
    } catch (DataAccessException e) {
      throw throwError(getEntityUpdateErrorCode(), e);
    }
  }

  @Override
  public T1 updateEntity(T1 entity) throws WsSrvException {
    try {
      return getEntityRepo().save(entity);
    } catch (DataAccessException e) {
      throw throwError(getEntityUpdateErrorCode(), e);
    }
  }

  @Override
  public BaseGoodResponse deleteEntity(Integer id) throws WsSrvException {
    try {
      getEntityRepo().deleteById(id);
    } catch (DataAccessException e) {
      throw throwError(getEntityDeleteErrorCode(), e);
    }

    return new BaseGoodResponse();
  }

  public EntityMapper getEntityMapper() {
    return entityMapper;
  }
}
