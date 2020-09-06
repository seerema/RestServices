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

package com.seerema.shared.jpa.status;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.seerema.shared.dto.EntityDto;
import com.seerema.shared.dto.EntityExDto;
import com.seerema.shared.jpa.base.AbstractSharedJpaTestAppConfig;
import com.seerema.shared.jpa.base.model.DbEntity;
import com.seerema.shared.jpa.base.service.EntityService;
import com.seerema.shared.jpa.base.service.impl.BaseEntityServiceImpl;
import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.shared.jpa.status.service.EntityStatusService;
import com.seerema.shared.jpa.status.service.impl.AbstractEntityStatusServiceImpl;

/**
 * Test Configuration for SharedJpa tests
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableConfigurationProperties
public class SharedJpaStatusTestAppConfig
    extends AbstractSharedJpaTestAppConfig {

  @Override
  protected String getTestName() {
    return "SharedJpaStatusTest";
  }

  // @Bean
  public EntityService<DbEntity, EntityDto> entityService() {
    return new BaseEntityServiceImpl();
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
