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

package com.seerema.shared.ui.config;

/**
 * Base class for all future UI Configuration Properties
 * 
 */
public class UIConfigProperties {

  private String lang;

  /**
   * @return the lang
   */
  public String getLang() {
    return lang;
  }

  /**
   * @param lang
   *          the lang to set
   */
  public void setLang(String lang) {
    this.lang = lang;
  }
}
