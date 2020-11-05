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

import com.seerema.shared.dto.FieldDto;
import com.seerema.shared.jpa.base.model.Field;
import com.seerema.shared.jpa.base.repo.FieldRepo;
import com.seerema.shared.jpa.base.shared.ErrorCodes;

/**
 * Implementation for Field Service
 */
public abstract class AbstractFieldServiceImpl
    extends AbstractEntityServiceImpl<Field, FieldDto> {

  @Autowired
  private FieldRepo _repo;

  protected abstract int getModuleId();

  @Override
  protected Iterable<Field> findAll() {
    return _repo.findAllByFieldCatModuleId(getModuleId());
  }

  @Override
  protected Class<FieldDto> getEntityDtoClass() {
    return FieldDto.class;
  }

  @Override
  protected CrudRepository<Field, Integer> getEntityRepo() {
    return _repo;
  }

  @Override
  protected String getEntityCreateErrorCode() {
    return ErrorCodes.ERROR_CREATE_FIELD.name();
  }

  @Override
  protected String getEntityReadErrorCode() {
    return ErrorCodes.ERROR_READ_FIELD.name();
  }

  @Override
  protected String getEntitiesReadErrorCode() {
    return ErrorCodes.ERROR_READ_FIELDS.name();
  }

  @Override
  protected String getEntityUpdateErrorCode() {
    return ErrorCodes.ERROR_UPDATE_FIELD.name();
  }

  @Override
  protected String getEntityDeleteErrorCode() {
    return ErrorCodes.ERROR_DELETE_FIELD.name();
  }
}
