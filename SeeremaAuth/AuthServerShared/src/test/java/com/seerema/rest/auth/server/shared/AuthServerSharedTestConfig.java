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

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;

/**
 * Test Configuration class with authentication manager
 * 
 */

@SpringBootConfiguration
@EnableAutoConfiguration
public class AuthServerSharedTestConfig {

  @Bean
  AuthenticationProvider TestSecurityProvider() {
    return new AuthServerSharedTestProvider();
  }

}
