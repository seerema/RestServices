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

package com.seerema.rest.entity.shared;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.rest.entity.shared.controller.BaseUserController;
import com.seerema.shared.jpa.base.service.UserService;
import com.seerema.shared.jpa.base.service.impl.BaseUserService;

/**
 * Test Configuration for Entity tests
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableConfigurationProperties
public class TestSharedEntityRestConfiguration
    extends AbstractEntityRestTestConfiguration {

  @Override
  protected String getTestName() {
    return "SharedEntityRestTest";
  }

  @Bean
  public UserService testUserService() {
    return new BaseUserService();
  }

  @RestController
  @RequestMapping("/test")
  public class TestUserController extends BaseUserController {

  }
}
