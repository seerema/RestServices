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

package com.seerema.rest.entity.shared;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import com.seerema.shared.config.RestConfig;
import com.seerema.shared.config.RestConfigProperties;
import com.seerema.shared.dto.ModuleDto;
import com.seerema.shared.ui.config.common.AbstractSharedUiTestExAppConfig;

/**
 * Shared Configuration for Entity tests
 */
public abstract class AbstractEntityRestTestConfiguration
    extends AbstractSharedUiTestExAppConfig {

  @Bean
  public ModuleDto getModuleDto() {
    ModuleDto module = new ModuleDto(0);
    module.setName("test");

    return module;
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
