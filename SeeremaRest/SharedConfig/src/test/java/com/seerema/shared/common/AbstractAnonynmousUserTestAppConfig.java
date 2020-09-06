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

package com.seerema.shared.common;

import org.springframework.context.annotation.Bean;

import com.seerema.shared.service.impl.AnonymousUserServiceImpl;

/**
 * Test Application Configuration for Anonymous User Service
 * 
 */

public abstract class AbstractAnonynmousUserTestAppConfig {

  @Bean("user_service")
  public UserService anonymousService() {
    return new AnonymousUserServiceImpl();
  }
}
