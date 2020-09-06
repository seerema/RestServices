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

import java.util.ArrayList;
import java.util.List;

import com.seerema.shared.dto.EntityDto;
import com.seerema.shared.dto.EntityFieldDto;
import com.seerema.shared.dto.FieldCategoryDto;
import com.seerema.shared.dto.FieldDto;

/**
 * Integration test for Abstract Entity Controller
 */

public abstract class AbstractEntityControllerTest<T1 extends EntityDto>
    extends AbstractSharedEntityRestControllerTest<T1> {

  @Override
  protected void initEntity(T1 entity) {
    FieldCategoryDto fc = new FieldCategoryDto();
    fc.setId(1);
    entity.setName("Test");
    entity.setFieldCat(fc);
    setTestEntityFields(entity);
  }

  private void setTestEntityFields(T1 entity) {
    List<EntityFieldDto> cfields = new ArrayList<>();
    cfields.add(getTestFieldsDto(1, "Test Notes"));
    entity.setEntityFields(cfields);
  }

  private EntityFieldDto getTestFieldsDto(int fid, String value) {
    FieldDto field = new FieldDto();
    field.setId(fid);

    EntityFieldDto cfield = new EntityFieldDto();
    cfield.setValue(value);
    cfield.setField(field);

    return cfield;
  }

  @Override
  protected void setEntityPartial(T1 entity) {
    entity.setName("");
  }

  @Override
  protected void setEntityInvalid(T1 entity) {
    entity.setName("");
  }

  @Override
  protected String getEntityUrl() {
    return "entity";
  }

  @Override
  protected String getEntitiesUrl() {
    return "entities";
  }

  @Override
  protected void updateEntity(T1 entity) {
    entity.setId(getEntityIdx());
    entity.setName(getUpdateValue());
  }

  @Override
  protected int getEntityIdx() {
    return 2;
  }

  @Override
  protected String getUpdateValue() {
    return "Temp";
  }

  @Override
  protected String getUpdateIdx() {
    return "name";
  }
}