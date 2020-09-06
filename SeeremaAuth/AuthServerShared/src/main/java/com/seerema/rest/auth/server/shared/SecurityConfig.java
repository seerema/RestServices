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

package com.seerema.rest.auth.server.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import com.seerema.rest.auth.common.Constants;
import com.seerema.rest.auth.server.shared.config.OkStatusAuthenticationSuccessHandler;
import com.seerema.rest.auth.server.shared.config.UnauthorizedStatusAuthenticationFailureHandler;

/**
 * Security Configuration
 * 
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private AuthenticationProvider _auth;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // @formatter:off

		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

				// Exclude Spring Actuator urls
				.antMatchers(HttpMethod.GET, "/actuator/info").permitAll()
				.antMatchers(HttpMethod.GET, "/actuator/health").permitAll()

				.anyRequest().authenticated()

				.and().exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))

				.and().formLogin().usernameParameter(Constants.USERNAME_PARAM)
				  .passwordParameter(Constants.PASSWORD_PARAM).loginProcessingUrl("/login")
				  .successHandler(new OkStatusAuthenticationSuccessHandler())
				  .failureHandler(new UnauthorizedStatusAuthenticationFailureHandler())

				.and().logout().logoutUrl("/logout").deleteCookies("remove")
				    .invalidateHttpSession(true)
				    .logoutSuccessHandler((
				        new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)));

		// @formatter:on
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(_auth);
  }
}
