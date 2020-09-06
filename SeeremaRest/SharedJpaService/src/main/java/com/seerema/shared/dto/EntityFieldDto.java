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

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.seerema.shared.jpa.base.model.EntityField;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

/**
 * The EntityDto DTO
 */
@DtoFor(EntityField.class)
public class EntityFieldDto extends AbstractEntityDto {

  @NotNull
  @ModelItem
  private FieldDto field;

  @NotEmpty
  @ModelItem
  @Size(max = 255)
  private String value;

  @ModelItem(setter = false)
  private EntityDto entity;

  public EntityFieldDto() {
  }

  public FieldDto getField() {
    return field;
  }

  public void setField(FieldDto field) {
    this.field = field;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public EntityDto getEntity() {
    return entity;
  }

  public void setEntity(EntityDto entity) {
    this.entity = entity;
  }
}