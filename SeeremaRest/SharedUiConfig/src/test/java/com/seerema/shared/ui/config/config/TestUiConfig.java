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

package com.seerema.shared.ui.config.config;

import com.seerema.shared.config.PropertyItem;
import com.seerema.shared.ui.config.UiConfig;

/**
 * Demo Web Clien configuration
 * 
 */
public class TestUiConfig extends UiConfig {

  // Serial Version UID
  private static final long serialVersionUID = 1L;

  @PropertyItem
  String param1;

  @PropertyItem
  String param2;

  @PropertyItem
  String param3;

  /**
   * Default constructor
   */
  public TestUiConfig() {
    // TODO Auto-generated constructor stub
  }

  public String getParam1() {
    return param1;
  }

  public void setParam1(String param1) {
    this.param1 = param1;
  }

  public String getParam2() {
    return param2;
  }

  public void setParam2(String param2) {
    this.param2 = param2;
  }

  public String getParam3() {
    return param3;
  }

  public void setParam3(String param3) {
    this.param3 = param3;
  }

  @Override
  public String toString() {
    return super.toString() + "param1=" + param1 + ";" + "param2=" + param2 +
        ";" + "param3=" + param3 + ";";
  }
}
