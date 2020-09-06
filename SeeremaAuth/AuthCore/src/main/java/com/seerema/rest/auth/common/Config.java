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

package com.seerema.rest.auth.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;

/**
 * Configuration class for RedisHttpSession Define special namespace to store
 * session data
 * 
 * Default session timeout is 1 day (86400 seconds)
 *
 */
@Configuration
@EnableRedisHttpSession(redisNamespace = Constants.SESSION_NAMESPACE,
    maxInactiveIntervalInSeconds = 86400)
public class Config {

  @Value("${cookie.domain:" + Constants.DEF_COOKIE_DOMAIN + "}")
  private String cookieDomain;

  @Value("${cookie.path:" + Constants.DEF_COOKIE_PATH + "}")
  private String cookiePath;

  @Bean
  public CookieSerializer cookieSerializer() {
    DefaultCookieSerializer s = new DefaultCookieSerializer();
    s.setCookieName(Constants.COOKIE_SESSION_NAME);
    s.setDomainName(cookieDomain);
    s.setCookiePath(cookiePath);
    s.setUseBase64Encoding(false);

    return s;
  }

  // @Bean
  public HttpSessionIdResolver httpSessionIdResolver() {
    CookieHttpSessionIdResolver resolver = new CookieHttpSessionIdResolver();
    return resolver;
  }
}
