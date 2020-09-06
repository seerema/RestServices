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

import com.seerema.shared.dto.FieldCategoryDto;
import com.seerema.shared.dto.ModuleDto;
import com.seerema.shared.jpa.base.model.FieldCategory;
import com.seerema.shared.jpa.base.repo.FieldCategoryRepo;
import com.seerema.shared.jpa.base.shared.ErrorCodes;

/**
 * Implementation for FieldCategory Service
 */
@Service
public class FieldCategoryServiceImpl
    extends AbstractEntityModuleServiceImpl<FieldCategory, FieldCategoryDto> {

  @Autowired
  private ModuleDto _mod;

  @Autowired
  private FieldCategoryRepo _repo;

  @Override
  protected Iterable<FieldCategory> findAll() {
    return _repo.findAllByModuleId(_mod.getId());
  }

  @Override
  protected Class<FieldCategoryDto> getEntityDtoClass() {
    return FieldCategoryDto.class;
  }

  @Override
  protected CrudRepository<FieldCategory, Integer> getEntityRepo() {
    return _repo;
  }

  @Override
  protected String getEntityCreateErrorCode() {
    return ErrorCodes.ERROR_CREATE_FIELD_CAT.name();
  }

  @Override
  protected String getEntityReadErrorCode() {
    return ErrorCodes.ERROR_READ_FIELD_CAT.name();
  }

  @Override
  protected String getEntitiesReadErrorCode() {
    return ErrorCodes.ERROR_READ_FIELD_CATS.name();
  }

  @Override
  protected String getEntityUpdateErrorCode() {
    return ErrorCodes.ERROR_UPDATE_FIELD_CAT.name();
  }

  @Override
  protected String getEntityDeleteErrorCode() {
    return ErrorCodes.ERROR_DELETE_FIELD_CAT.name();
  }
}
