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

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.rest.shared.base.controller.BaseVersionController;
import com.seerema.shared.common.AbstractSharedAppConfig;

/**
 * Configuration class for Spring Boot Test Launcher
 * 
 */

@SpringBootConfiguration
@EnableAutoConfiguration
public class TestSharedRestBaseAppConfig extends AbstractSharedAppConfig {

  @Override
  protected String getTestName() {
    return "SharedRestBase";
  }

  @RestController
  @RequestMapping
  public class TestRestVersionController extends BaseVersionController {

  }
}
