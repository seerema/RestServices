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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seerema.shared.jpa.status.model.Status;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

/**
 * The Status DTO
 */

@DtoFor(Status.class)
public class StatusDto extends AbstractEntityDto {

  @JsonIgnore
  @ModelItem(setter = false)
  @Size(max = 1)
  private String isSystem;

  @NotEmpty
  @ModelItem
  @Size(max = 50)
  private String name;

  public StatusDto() {
  }

  public StatusDto(int id) {
    this();
    setId(id);
  }

  public String getIsSystem() {
    return this.isSystem;
  }

  public void setIsSystem(String isSystem) {
    this.isSystem = isSystem;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}