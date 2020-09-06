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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.seerema.shared.dto.EntityFieldDto;
import com.seerema.shared.jpa.base.model.EntityField;
import com.seerema.shared.jpa.base.repo.EntityFieldRepo;
import com.seerema.shared.jpa.base.shared.ErrorCodes;

/**
 * Implementation for FieldCategory Service
 */
@Service
public class EntityFieldServiceImpl
    extends AbstractEntityServiceImpl<EntityField, EntityFieldDto> {

  @Autowired
  private EntityFieldRepo _repo;

  @Override
  protected String getEntityCreateErrorCode() {
    return ErrorCodes.ERROR_CREATE_ENTITY_FIELD.name();
  }

  @Override
  protected String getEntityReadErrorCode() {
    return ErrorCodes.ERROR_READ_ENTITY_FIELD.name();
  }

  @Override
  protected String getEntitiesReadErrorCode() {
    return ErrorCodes.ERROR_READ_ENTITY_FIELDS.name();
  }

  @Override
  protected String getEntityUpdateErrorCode() {
    return ErrorCodes.ERROR_UPDATE_ENTITY_FIELD.name();
  }

  @Override
  protected String getEntityDeleteErrorCode() {
    return ErrorCodes.ERROR_DELETE_ENTITY_FIELD.name();
  }

  @Override
  protected CrudRepository<EntityField, Integer> getEntityRepo() {
    return _repo;
  }

  @Override
  protected Iterable<EntityField> findAll() {
    return _repo.findAll();
  }

  @Override
  protected Class<EntityFieldDto> getEntityDtoClass() {
    return EntityFieldDto.class;
  }
}
