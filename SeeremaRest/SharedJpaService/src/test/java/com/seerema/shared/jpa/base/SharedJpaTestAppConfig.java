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

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.seerema.shared.jpa.base.service.UserService;
import com.seerema.shared.jpa.base.service.impl.BaseUserService;

/**
 * Test Configuration for SharedJpa tests
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableConfigurationProperties
public class SharedJpaTestAppConfig extends AbstractSharedJpaTestAppConfig {

  @Override
  protected String getTestName() {
    return "SharedJpaTest";
  }

  @Bean
  public UserService testUserService() {
    return new BaseUserService();
  }
}
