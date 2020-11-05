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

package com.seerema.shared.jpa.status;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import com.seerema.shared.jpa.base.AbstractSharedJpaTestAppConfig;

/**
 * Test Configuration for SharedJpa tests
 */

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableConfigurationProperties
@Import(SharedJpaStatusServicesConfig.class)
public class SharedJpaStatusTestAppConfig
    extends AbstractSharedJpaTestAppConfig {

  @Override
  protected String getTestName() {
    return "SharedJpaStatusTest";
  }
}
