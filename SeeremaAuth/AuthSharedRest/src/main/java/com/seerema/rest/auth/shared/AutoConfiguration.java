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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.context.annotation.SessionScope;

import com.seerema.rest.auth.common.Constants;
import com.seerema.rest.auth.common.service.AuthUserService;

/**
 * AutoConfiguration file for shared library
 * 
 */
@Configuration
@ComponentScan("com.seerema.rest.auth.shared")
public class AutoConfiguration {

  @Bean
  public CookieHttpSessionIdResolver cookieHttpSessionIdResolver() {
    CookieHttpSessionIdResolver resolver = new CookieHttpSessionIdResolver();
    DefaultCookieSerializer serializer = new DefaultCookieSerializer();
    serializer.setCookieName(Constants.COOKIE_SESSION_NAME);
    serializer.setUseBase64Encoding(false);
    resolver.setCookieSerializer(serializer);

    return resolver;
  }

  @SessionScope
  @Bean("user_service")
  public AuthUserService authUserService() {
    return new AuthUserService();
  }
}
