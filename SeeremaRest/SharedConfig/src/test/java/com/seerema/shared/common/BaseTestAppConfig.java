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

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import com.seerema.shared.service.RestInfo;
import com.seerema.shared.service.impl.AppRestInfo;

/**
 * Test Application Configuration
 * 
 */

public abstract class BaseTestAppConfig {

  protected abstract String getTestName();

  @Bean("log")
  public Logger logger() {
    TestConstants.LOG.info("Poehali ...");
    return TestConstants.LOG;
  }

  @Bean("rest_info")
  @DependsOn("log")
  protected RestInfo appInfo() {
    return new AppRestInfo(getTestName() + "Test");
  }

  protected Logger getLogger() {
    return TestConstants.LOG;
  }

}
