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

  protected abstract String getModuleName();

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    HttpSecurity sconfig = http.csrf().disable()

    // @@formatter:off
    
        // Disable /login redirection
        .exceptionHandling().authenticationEntryPoint(
            new Http403ForbiddenEntryPoint())
        
        .and().antMatcher("/" + getModuleName() + "/**")
        .authorizeRequests()
 
        /************* Quest API *************/

        // All entities view restriction
        .antMatchers(HttpMethod.GET,
            "/" + getModuleName() + "/entities/all").
            hasAnyRole("SBS_MANAGER","SBS_ADMIN")
        
        // Restrict any entity delete
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
        
        /************* ADMIN API *************/
        .antMatchers("/" + getModuleName() + "/admin/**").
            hasAnyRole("SBS_ADMIN").and();
        
        /************* CUSTOM API *************/
    
        addCustSecurityConfig(sconfig);
    
        /************* LAST DENIED RULE *************/

        sconfig.authorizeRequests().anyRequest().authenticated();
    
    
    
    // @formatter:on
  }

  protected void addCustSecurityConfig(HttpSecurity sconfig) throws Exception {
    // Do nothing by default;
  }
}
