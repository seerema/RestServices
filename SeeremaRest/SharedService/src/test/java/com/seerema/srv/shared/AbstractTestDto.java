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

import java.util.List;

import com.seerema.srv.shared.annotations.ModelItem;

/**
 * AbstractTestDto
 */
public class AbstractTestDto<T extends SimpleDto> {

  @ModelItem("obj")
  private T dto;

  @ModelItem
  private List<T> list;

  public T getDto() {
    return dto;
  }

  public void setDto(T dto) {
    this.dto = dto;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }
}
