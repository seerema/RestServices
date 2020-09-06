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

package com.seerema.rest.shared.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.context.annotation.SessionScope;

import com.seerema.shared.common.AbstractAnonynmousUserTestAppConfig;
import com.seerema.shared.service.RequestLogger;
import com.seerema.shared.service.impl.RequestLoggerImpl;

/**
 * Test Application Configuration for Anonymous User Service
 * 
 */

public abstract class AbstractBaseAppTestConfig
    extends AbstractAnonynmousUserTestAppConfig {

  @Bean("rlog")
  @SessionScope
  @DependsOn({ "log", "user_service" })
  public RequestLogger requestLogger() {
    return new RequestLoggerImpl();
  }
}
