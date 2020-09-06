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

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import com.seerema.catalog.srv.shared.CatalogConstants;

/**
 * Security configuration fBase REST services
 * 
 */
@EnableWebSecurity
public class CatalogRestSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()

    // @@formatter:off
    
        // Disable /login redirection
        .exceptionHandling().authenticationEntryPoint(
           new Http403ForbiddenEntryPoint())
        
        .and().antMatcher("/" + CatalogConstants.MODULE_NAME + "/**")
        .authorizeRequests()
 
        /************* CATALOG API *************/
        
        // Restrict any catalog delete
        .antMatchers(HttpMethod.DELETE, "/" +
            CatalogConstants.MODULE_NAME +"/country/{\\d+}").
            hasAnyRole("SBS_MANAGER","SBS_ADMIN")
        .antMatchers(HttpMethod.DELETE, "/" +
            CatalogConstants.MODULE_NAME +"/region/{\\d+}").
            hasAnyRole("SBS_MANAGER","SBS_ADMIN")
        .antMatchers(HttpMethod.DELETE, "/" +
            CatalogConstants.MODULE_NAME +"/city/{\\d+}").
            hasAnyRole("SBS_MANAGER","SBS_ADMIN")
        .antMatchers(HttpMethod.DELETE, "/" +
            CatalogConstants.MODULE_NAME +"/address/{\\d+}").
            hasAnyRole("SBS_MANAGER","SBS_ADMIN")
        .antMatchers(HttpMethod.DELETE, "/" +
            CatalogConstants.MODULE_NAME +"/entity/{\\d+}").
            hasAnyRole("SBS_MANAGER","SBS_ADMIN")
        .antMatchers(HttpMethod.DELETE, "/" +
            CatalogConstants.MODULE_NAME +"/bfile_cat/{\\d+}").
            hasAnyRole("SBS_MANAGER","SBS_ADMIN")
        .antMatchers(HttpMethod.DELETE, "/" +
            CatalogConstants.MODULE_NAME +"/bfile/{\\d+}").
            hasAnyRole("SBS_MANAGER","SBS_ADMIN")
        .antMatchers(HttpMethod.DELETE, "/" +
            CatalogConstants.MODULE_NAME +"/field_cat/{\\d+}").
            hasAnyRole("SBS_MANAGER","SBS_ADMIN")
        .antMatchers(HttpMethod.DELETE, "/" +
            CatalogConstants.MODULE_NAME +"/field/{\\d+}").
            hasAnyRole("SBS_MANAGER","SBS_ADMIN")
            
        /************* LAST DENIED RULE *************/
        .anyRequest().authenticated();
    
    // @formatter:on
  }
}
