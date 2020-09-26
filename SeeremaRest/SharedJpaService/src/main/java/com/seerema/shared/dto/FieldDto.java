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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seerema.shared.jpa.base.model.Field;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

/**
 * Field DTO
 */
@DtoFor(Field.class)
public class FieldDto extends AbstractEntityDto {

  @NotEmpty
  @ModelItem
  @Size(max = 50)
  private String name;

  @NotNull
  @ModelItem
  private FieldCategoryDto fieldCat;

  @ModelItem
  @JsonIgnore
  @Size(max = 1)
  private String isSystem;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FieldCategoryDto getFieldCat() {
    return fieldCat;
  }

  public void setFieldCat(FieldCategoryDto fieldCat) {
    this.fieldCat = fieldCat;
  }

  public String getIsSystem() {
    return isSystem;
  }

  public void setIsSystem(String isSystem) {
    this.isSystem = isSystem;
  }

  public boolean getReadOnly() {
    return "Y".equals(isSystem);
  }
}
