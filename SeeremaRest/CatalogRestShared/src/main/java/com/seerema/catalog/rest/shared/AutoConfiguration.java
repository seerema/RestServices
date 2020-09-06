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

package com.seerema.catalog.rest.shared;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.seerema.rest.shared.ui.app.config.AbstractBaseRestConfig;

/**
 * AutoConfiguration class for shared library
 */
@Configuration
@ComponentScan("com.seerema.catalog.rest.shared")
public class AutoConfiguration extends AbstractBaseRestConfig {

  @Override
  protected String getAppName() {
    return "Catalog Rest API";
  }
}
