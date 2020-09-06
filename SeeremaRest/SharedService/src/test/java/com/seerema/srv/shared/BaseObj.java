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

public class BaseObj {

  private Integer a;

  private Long b;

  public BaseObj() {
  }

  public BaseObj(Integer a, Long b) {
    this.a = a;
    this.b = b;
  }

  public Integer getA() {
    return a;
  }

  public void setA(Integer a) {
    this.a = a;
  }

  public Long getB() {
    return b;
  }

  public void setB(Long b) {
    this.b = b;
  }
}