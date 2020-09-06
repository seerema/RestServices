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

package com.seerema.shared.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import com.seerema.shared.config.RestConfig;
import com.seerema.shared.config.RestConfigProperties;

/**
 * Test Application Configuration
 * 
 */

public abstract class AbstractSharedAppConfig
    extends AbstractRestTestAppConfig<RestConfig> {

  @Override
  public RestConfigProperties getWsConfigProperties() {
    return new RestConfigProperties();
  }

  @Override
  public RestConfig getBaseWsConfig() {
    return new RestConfig();
  }

  @Bean("rest_cfg_props")
  @ConfigurationProperties("test.rest.config")
  public RestConfigProperties restConfigProps() throws Exception {
    return getWsConfigProps();
  }

  @Bean("rest_cfg")
  @DependsOn({ "rest_cfg_props", "log" })
  public RestConfig restCfg() throws Exception {
    return getWsCfg();
  }
}
