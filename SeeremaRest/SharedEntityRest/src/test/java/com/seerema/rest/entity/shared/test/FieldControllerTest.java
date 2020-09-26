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

package com.seerema.rest.entity.shared.test;

import com.seerema.shared.dto.FieldCategoryDto;
import com.seerema.shared.dto.FieldDto;

/**
 * Integration test for Abstract Field Controller
 */

public class FieldControllerTest
    extends AbstractSharedEntityRestControllerTest<FieldDto> {

  @Override
  protected void initEntity(FieldDto entity) {
    FieldCategoryDto fcat = new FieldCategoryDto();
    fcat.setId(1);

    entity.setName("Test");
    entity.setFieldCat(fcat);
  }

  @Override
  protected void setEntityPartial(FieldDto entity) {
    entity.setName("");
  }

  @Override
  protected void setEntityInvalid(FieldDto entity) {
    entity.setName("");
  }

  @Override
  protected String getEntityUrl() {
    return "field";
  }

  @Override
  protected String getEntitiesUrl() {
    return "fields";
  }

  @Override
  protected void updateEntity(FieldDto entity) {
    entity.setId(getEntityIdx());
    entity.setName(getUpdateValue());
  }

  @Override
  protected int getEntityIdx() {
    return 2;
  }

  @Override
  protected String getUpdateValue() {
    return "Demo";
  }

  @Override
  protected String getUpdateIdx() {
    return "name";
  }

  @Override
  protected FieldDto getEntity() {
    return new FieldDto();
  }

  @Override
  protected String getApiPrefix() {
    return "admin/";
  }
}