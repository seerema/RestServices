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

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Test Configuration for Entity tests
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableConfigurationProperties
public class TestSharedEntityRestConfiguration
    extends AbstractEntityRestTestConfiguration {

  @Override
  protected String getTestName() {
    return "SharedEntityRestTest";
  }
}
