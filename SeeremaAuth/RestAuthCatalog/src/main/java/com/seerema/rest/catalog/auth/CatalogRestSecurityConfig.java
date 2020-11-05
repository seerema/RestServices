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

package com.seerema.rest.catalog.auth;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.seerema.catalog.srv.shared.CatalogConstants;
import com.seerema.rest.auth.base.AbstractRestSecurityConfig;

/**
 * Security configuration fBase REST services
 * 
 */
@EnableWebSecurity
public class CatalogRestSecurityConfig extends AbstractRestSecurityConfig {

  @Override
  protected String getModuleName() {
    return CatalogConstants.MODULE_NAME;
  }
}
