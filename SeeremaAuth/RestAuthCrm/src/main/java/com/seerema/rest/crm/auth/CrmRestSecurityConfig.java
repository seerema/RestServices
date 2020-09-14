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

package com.seerema.rest.crm.auth;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.seerema.crm.srv.shared.CrmConstants;
import com.seerema.rest.auth.base.AbstractRestSecurityConfig;

/**
 * Security configuration for CRM REST services
 * 
 */
@EnableWebSecurity
public class CrmRestSecurityConfig extends AbstractRestSecurityConfig {

  @Override
  protected String getModuleName() {
    return CrmConstants.MODULE_NAME;
  }

  @Override
  protected void addCustSecurityConfig(HttpSecurity sconfig) throws Exception {
    // @@formatter:off
    
    sconfig.authorizeRequests()
        .antMatchers(HttpMethod.DELETE, "/crm/cust_comm_history/{\\d+}")
          .hasAnyRole("SBS_MANAGER", "SBS_ADMIN");
    
    // @@formatter:on
  }
}
