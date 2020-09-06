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

package com.seerema.srv.shared;

import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

/**
 * LinkedDto test class
 *
 */

@DtoFor(LinkedObj.class)
public class LinkedDto {

  @ModelItem
  private Integer id;

  @ModelItem("extObj")
  private ExtDto extDto;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setExtDto(ExtDto extDto) {
    this.extDto = extDto;
  }

  public ExtDto getExtDto() {
    return extDto;
  }
}
