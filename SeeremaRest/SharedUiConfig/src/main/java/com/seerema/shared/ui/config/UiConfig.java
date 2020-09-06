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

import org.slf4j.Logger;

import com.seerema.shared.config.AbstractConfig;
import com.seerema.shared.config.PropertyItem;

/**
 * Base Web Service Configuration file
 * 
 */
public class UiConfig extends AbstractConfig {

  // Serial Version UID
  private static final long serialVersionUID = 1L;

  // Default UI language
  @PropertyItem
  private String lang;

  /**
   * Default constructor
   */
  public UiConfig() {
    super();
  }

  /**
   * Class Constructor with external property file only
   * 
   * @param fileName Name of external file
   */
  public UiConfig(String fileName) {
    super(fileName);
  }

  /**
   * Simplified constructor
   * 
   * @param fileName
   * @param logger
   */
  public UiConfig(String fileName, Logger logger) {
    super(fileName, logger);
  }

  public UiConfig(String fileName, String homeDir, Logger logger) {
    super(fileName, homeDir, logger);
  }

  @Override
  public String getPrefix() {
    return "ui_cfg";
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  @Override
  public String toString() {
    return super.toString() + " lang=" + lang + ";";
  }
}
