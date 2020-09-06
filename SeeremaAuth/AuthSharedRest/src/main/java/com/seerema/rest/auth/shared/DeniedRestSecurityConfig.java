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
package com.seerema.rest.auth.shared;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

/**
 * Security configuration for Base REST services
 * By default it has the LOWEST_PRECEDENCE i.e should be the last
 * 
 */
@Configuration
@Order
public class DeniedRestSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()

    // @@formatter:off
    
        // Disable /login redirection
        .exceptionHandling().authenticationEntryPoint(
           new Http403ForbiddenEntryPoint())
        
        .and().antMatcher("/**").authorizeRequests()
 
        .anyRequest().authenticated();
    
    // @formatter:on
  }
}
