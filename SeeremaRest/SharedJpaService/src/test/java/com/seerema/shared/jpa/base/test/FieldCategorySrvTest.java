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

package com.seerema.shared.jpa.base.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.seerema.base.WsSrvException;
import com.seerema.shared.dto.FieldCategoryDto;
import com.seerema.shared.jpa.base.model.FieldCategory;
import com.seerema.shared.jpa.base.service.BaseEntityService;

/**
 * Test Field Category Service
 */

public class FieldCategorySrvTest
    extends AbstractEntitySrvTest<FieldCategory, FieldCategoryDto> {

  private static final String NEW_NAME = "ZZ TOP";

  private static final String UPD_NAME = "METALLICA";

  @Autowired
  BaseEntityService<FieldCategory, FieldCategoryDto> _srv;

  @Override
  protected void initEntity(FieldCategoryDto entity) {
    entity.setName(NEW_NAME);
  }

  @Override
  protected void checkNewEntity(FieldCategoryDto entity) throws WsSrvException {
    assertEquals(NEW_NAME, entity.getName());
  }

  @Override
  protected void updateEntityl(FieldCategoryDto entity) {
    entity.setName(UPD_NAME);
  }

  @Override
  protected void checkUpdatedEntity(FieldCategoryDto entity)
      throws WsSrvException {
    assertEquals(UPD_NAME, entity.getName());
  }

  @Override
  protected BaseEntityService<FieldCategory, FieldCategoryDto>
      getEntityService() {
    return _srv;
  }

  @Override
  protected Class<FieldCategoryDto> getEntityDtoClass() {
    return FieldCategoryDto.class;
  }

  @Override
  protected int getInitSize() {
    return 0;
  }

  @Override
  protected String getCreateError() {
    return "ERROR_CREATE_FIELD_CAT - Exception type org.springframework.dao.DataIntegrityViolationException" +
        " [not-null property references a null or transient value :" +
        " com.seerema.shared.jpa.base.model.FieldCategory.name; nested" +
        " exception is org.hibernate.PropertyValueException: not-null property" +
        " references a null or transient value : com.seerema.shared.jpa.base.model.FieldCategory.name]";
  }

  @Override
  protected String getDupError() {
    return "ERROR_CREATE_FIELD_CAT - Exception type org.springframework.dao.DataIntegrityViolationException " +
        "[could not execute statement; SQL [n/a]; constraint [UK13PPBLXLC5RAX5WC61PN5DV0C]; " +
        "nested exception is org.hibernate.exception.ConstraintViolationException: " +
        "could not execute statement]";
  }

}
