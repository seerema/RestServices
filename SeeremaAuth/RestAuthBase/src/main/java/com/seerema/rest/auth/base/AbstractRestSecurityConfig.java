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

package com.seerema.rest.auth.base;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

/**
 * Security configuration fBase REST services
 * 
 */
public abstract class AbstractRestSecurityConfig
    extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()

    // @@formatter:off
    
        // Disable /login redirection
        .exceptionHandling().authenticationEntryPoint(
            new Http403ForbiddenEntryPoint())
        
        .and().antMatcher("/" + getModuleName() + "/**")
        .authorizeRequests()
 
        /************* Quest API *************/

        // All quests view restriction
        .antMatchers(HttpMethod.GET,
            "/" + getModuleName() + "/entities/all").
            hasAnyRole("SBS_MANAGER","SBS_ADMIN")
        
        // Restrict any task delete
        .antMatchers(HttpMethod.DELETE, 
            "/" + getModuleName() +"/field_cat/{\\d+}").
            hasAnyRole("SBS_MANAGER","SBS_ADMIN")
        .antMatchers(HttpMethod.DELETE, 
            "/" + getModuleName() +"/field/{\\d+}").
            hasAnyRole("SBS_MANAGER","SBS_ADMIN")
        .antMatchers(HttpMethod.DELETE, 
            "/" + getModuleName() +"/entity/{\\d+}").
            hasAnyRole("SBS_MANAGER","SBS_ADMIN")
        .antMatchers(HttpMethod.DELETE, 
            "/" + getModuleName() +"/entity_field/{\\d+}").
            hasAnyRole("SBS_MANAGER","SBS_ADMIN")
            
        /************* PRIVATE API *************/

        // All private api
        .antMatchers(HttpMethod.GET,
            "/" + getModuleName() + "/private/**").
            hasAnyRole("SBS_MANAGER", "SBS_ADMIN")
            
        /************* LAST DENIED RULE *************/

        .anyRequest().authenticated();
    
    // @formatter:on
  }

  protected abstract String getModuleName();
}