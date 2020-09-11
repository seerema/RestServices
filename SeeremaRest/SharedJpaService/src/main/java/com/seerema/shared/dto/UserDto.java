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

import com.seerema.shared.jpa.base.model.User;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

@DtoFor(User.class)
public class UserDto extends AbstractEntityDto {

  @NotEmpty
  @ModelItem(getter = false)
  private String name;

  public UserDto() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
