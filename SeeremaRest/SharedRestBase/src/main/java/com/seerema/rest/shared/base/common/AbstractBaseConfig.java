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

package com.seerema.rest.shared.base.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import com.seerema.shared.service.RestInfo;
import com.seerema.shared.service.impl.AppRestInfo;

/**
 * Base minimal rest service application configuration
 * 
 */

public abstract class AbstractBaseConfig {

  protected abstract String getAppName();

  @Bean("log")
  public Logger getLogger() {
    Logger _log = LoggerFactory.getLogger(getAppName());
    _log.info("Poehali ...");
    return _log;
  }

  @Bean("rest_info")
  @DependsOn("log")
  protected RestInfo wsInfo() {
    return new AppRestInfo(getAppName());
  }

}
