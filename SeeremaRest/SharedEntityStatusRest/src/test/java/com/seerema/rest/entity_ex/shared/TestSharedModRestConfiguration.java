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

package com.seerema.rest.entity_ex.shared;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.rest.entity.shared.AbstractEntityRestTestConfiguration;
import com.seerema.rest.entity_ex.shared.controller.EntityExController;
import com.seerema.shared.dto.EntityExDto;
import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.shared.jpa.status.service.EntityStatusService;
import com.seerema.shared.jpa.status.service.impl.AbstractEntityStatusServiceImpl;

/**
 * Test Configuration for Catalog tests
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableConfigurationProperties
public class TestSharedModRestConfiguration
    extends AbstractEntityRestTestConfiguration {

  @Override
  protected String getTestName() {
    return "SharedEntityRestTest";
  }

  @Validated
  @RestController
  @RequestMapping("/test")
  public class TestEntityExController extends EntityExController {

  }

  @Bean
  public EntityStatusService<EntityEx, EntityExDto> entityServiceStatus() {
    return new AbstractEntityStatusServiceImpl() {

      @Override
      protected String getNewStatus() {
        return "LL_NEW";
      }
    };
  }
}
