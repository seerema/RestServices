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

package com.seerema.catalog.srv;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.seerema.shared.ui.config.common.AbstractSharedUiTestExAppConfig;

/**
 * Test Configuration for Catalog tests
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableConfigurationProperties
public class TestCatalogServiceConfiguration
    extends AbstractSharedUiTestExAppConfig {

  @Override
  protected String getTestName() {
    return "CatalogServiceTest";
  }
}
