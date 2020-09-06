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

package com.seerema.rest.shared.ui.app.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

import com.seerema.shared.config.RestConfig;

/**
 * Base configuration for REST API
 *
 */

public class BaseRestAppConfig extends RestConfig {

  // Serial Version UID
  private static final long serialVersionUID = 1L;

  @Value("${home.dir}")
  public void initHomeDir(String homeDir) {
    setHomeDir(homeDir);
  }

  @PostConstruct
  public void initConfig() throws Exception {
    initAppConfig();
  }
}
