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

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.context.annotation.SessionScope;

import com.seerema.rest.shared.base.common.AbstractBaseConfig;
import com.seerema.shared.config.RestConfigProperties;
import com.seerema.shared.service.RequestLogger;
import com.seerema.shared.service.impl.RequestLoggerImpl;
import com.seerema.shared.ui.config.UIConfigProperties;

/**
 * Base minimal rest service application configuration
 * 
 */

public abstract class AbstractBaseRestConfig extends AbstractBaseConfig {

  @Bean("rest_cfg")
  BaseRestAppConfig baseRestAppConfig() {
    return new BaseRestAppConfig();
  }

  @Bean("ui_cfg")
  BaseUiAppConfig baseUiAppConfig() {
    return new BaseUiAppConfig();
  }

  @Bean("rest_cfg_props")
  @ConfigurationProperties("rest.config")
  public RestConfigProperties restConfigProperties() {
    return new RestConfigProperties();
  }

  @Bean("ui_cfg_props")
  @ConfigurationProperties("ui.config")
  public UIConfigProperties uiConfigProperties() {
    return new UIConfigProperties();
  }

  @Bean("rlog")
  @SessionScope
  @DependsOn({ "log", "user_service" })
  public RequestLogger requestLogger() {
    return new RequestLoggerImpl();
  }
}
