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

/**
 * LinkedObj test class
 *
 */
public class LinkedObj {

  private Integer id;

  private ExtObj extObj;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ExtObj getExtObj() {
    return extObj;
  }

  public void setExtObj(ExtObj extObj) {
    this.extObj = extObj;
  }
}
