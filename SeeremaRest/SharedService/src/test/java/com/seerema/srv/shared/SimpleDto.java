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
import com.seerema.srv.shared.dto.BaseDto;

@DtoFor(BaseObj.class)
public class SimpleDto extends BaseDto {

  @ModelItem("a")
  private Integer id;

  @ModelItem
  private Long b;

  public SimpleDto() {
  }

  public SimpleDto(Integer id, Long b) {
    this.id = id;
    this.b = b;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Long getB() {
    return b;
  }

  public void setB(Long b) {
    this.b = b;
  }
}