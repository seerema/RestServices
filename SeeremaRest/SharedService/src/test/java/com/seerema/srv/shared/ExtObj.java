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

public class ExtObj extends BaseObj {

  private BaseValue value;

  private List<BaseValue> values;

  private List<LinkedObj> linked;

  public ExtObj() {
  }

  public ExtObj(Integer a, Long b, BaseValue value, List<BaseValue> values) {
    super(a, b);

    this.value = value;
    this.values = values;
  }

  public BaseValue getValue() {
    return value;
  }

  public void setValue(BaseValue value) {
    this.value = value;
  }

  public List<BaseValue> getValues() {
    return values;
  }

  public void setValues(List<BaseValue> values) {
    this.values = values;
  }

  public List<LinkedObj> getLinked() {
    return linked;
  }

  public void setLinked(List<LinkedObj> linked) {
    this.linked = linked;
  }
}