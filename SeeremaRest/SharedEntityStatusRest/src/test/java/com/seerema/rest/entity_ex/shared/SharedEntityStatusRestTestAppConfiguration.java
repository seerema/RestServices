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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.rest.entity.shared.AbstractEntityRestTestConfiguration;
import com.seerema.rest.entity_ex.shared.controller.AbstractEntityExController;
import com.seerema.shared.dto.EntityExDto;
import com.seerema.shared.jpa.status.SharedJpaStatusServicesConfig;
import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.shared.jpa.status.service.EntityStatusService;

/**
 * Test Configuration for Catalog tests
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableConfigurationProperties
@Import(SharedJpaStatusServicesConfig.class)
public class SharedEntityStatusRestTestAppConfiguration
    extends AbstractEntityRestTestConfiguration {

  @Override
  protected String getTestName() {
    return "SharedEntityRestTest";
  }

  @Validated
  @RestController
  @RequestMapping("/test")
  public class TestEntityExController extends AbstractEntityExController {

    @Autowired
    private EntityStatusService<EntityEx, EntityExDto> _service;

    @Override
    protected EntityStatusService<EntityEx, EntityExDto>
        getEntityStatusService() {
      return _service;
    }

  }
}
