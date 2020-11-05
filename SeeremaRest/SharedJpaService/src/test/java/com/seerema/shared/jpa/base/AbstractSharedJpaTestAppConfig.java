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

package com.seerema.shared.jpa.base;

import org.springframework.context.annotation.Bean;

import com.seerema.shared.common.BaseTestAppConfig;
import com.seerema.shared.dto.ModuleDto;

/**
 * Base Test Configuration for SharedJpa tests
 */

public abstract class AbstractSharedJpaTestAppConfig extends BaseTestAppConfig {

  @Bean
  public ModuleDto getModuleDto() {
    return new ModuleDto(0);
  }
}
