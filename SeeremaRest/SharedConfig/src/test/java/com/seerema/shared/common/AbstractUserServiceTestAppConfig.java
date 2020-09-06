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

import com.seerema.shared.service.RequestLogger;
import com.seerema.shared.service.impl.AnonymousUserServiceImpl;
import com.seerema.shared.service.impl.RequestLoggerImpl;

/**
 * Test Application Configuration
 * 
 */

public abstract class AbstractUserServiceTestAppConfig
    extends AbstractHomeDirTestAppConfig {

  @Bean("user_service")
  public UserService anonymousService() {
    return new AnonymousUserServiceImpl();
  }

  @Bean("rlog")
  RequestLogger requestLogger() {
    return new RequestLoggerImpl();
  }
}
