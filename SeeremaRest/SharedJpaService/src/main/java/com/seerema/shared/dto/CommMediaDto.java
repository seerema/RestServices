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
import com.seerema.shared.jpa.base.model.CommMedia;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

@DtoFor(CommMedia.class)
public class CommMediaDto extends AbstractEntityDto {

  @NotEmpty
  @ModelItem
  @Size(max = 25)
  private String name;

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
