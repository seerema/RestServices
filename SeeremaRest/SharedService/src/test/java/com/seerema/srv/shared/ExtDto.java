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

import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

@DtoFor(ExtObj.class)
public class ExtDto extends SimpleDto {

  @ModelItem
  private ValueDto value;

  @ModelItem
  private List<ValueDto> values;

  @ModelItem(setter = false)
  private List<LinkedDto> linked;

  public ExtDto() {
  }

  public ExtDto(Integer id, Long b, ValueDto value, List<ValueDto> values) {
    super(id, b);

    this.value = value;
    this.values = values;
  }

  public ValueDto getValue() {
    return value;
  }

  public void setValue(ValueDto value) {
    this.value = value;
  }

  public List<ValueDto> getValues() {
    return values;
  }

  public void setValues(List<ValueDto> values) {
    this.values = values;
  }

  public List<LinkedDto> getLinked() {
    return linked;
  }

  public void setLinked(List<LinkedDto> linked) {
    this.linked = linked;
  }
}
