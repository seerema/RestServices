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

package com.seerema.shared.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.seerema.shared.jpa.base.model.DbEntity;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

/**
 * DTO for Abstract Entity with custom fields
 * 
 * @param <EntityDto> EntityDto
 * @param <EntityFieldDto> EntityFieldDto
 * @param <FieldCategoryDto> FieldCategoryDto
 */
@DtoFor(DbEntity.class)
public class EntityDto extends AbstractEntityDto {

  @NotEmpty
  @ModelItem
  @Size(max = 191)
  private String name;

  @ModelItem
  private EntityDto parent;

  @ModelItem
  private List<EntityFieldDto> entityFields;

  @NotNull
  @ModelItem
  private FieldCategoryDto fieldCat;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EntityDto getParent() {
    return parent;
  }

  public void setParent(EntityDto parent) {
    this.parent = parent;
  }

  public List<EntityFieldDto> getEntityFields() {
    return entityFields;
  }

  public void setEntityFields(List<EntityFieldDto> entityFields) {
    this.entityFields = entityFields;
  }

  public FieldCategoryDto getFieldCat() {
    return fieldCat;
  }

  public void setFieldCat(FieldCategoryDto fieldCat) {
    this.fieldCat = fieldCat;
  }
}