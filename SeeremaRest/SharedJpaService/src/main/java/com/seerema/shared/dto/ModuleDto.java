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
import javax.validation.constraints.Size;

import com.seerema.shared.jpa.base.model.Module;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

/**
 * Module DTO
 * 
 */
@DtoFor(Module.class)
public class ModuleDto extends AbstractEntityDto {

  @NotEmpty
  @ModelItem
  @Size(max = 25)
  private String name;

  public ModuleDto() {
  }

  public ModuleDto(int id) {
    setId(id);
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
