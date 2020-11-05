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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.seerema.shared.dto.EntityDto;
import com.seerema.shared.dto.EntityExDto;
import com.seerema.shared.dto.ModuleDto;
import com.seerema.shared.jpa.base.model.DbEntity;
import com.seerema.shared.jpa.base.service.EntityService;
import com.seerema.shared.jpa.base.service.impl.AbstractBaseEntityServiceImpl;
import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.shared.jpa.status.service.EntityStatusService;
import com.seerema.shared.jpa.status.service.StatusService;
import com.seerema.shared.jpa.status.service.impl.AbstractEntityStatusServiceImpl;
import com.seerema.shared.jpa.status.service.impl.AbstractStatusServiceImpl;

/**
 * Test Configuration for Test Jpa Status Services
 */

public class SharedJpaStatusServicesConfig {

  @Autowired
  private ModuleDto _mod;

  private EntityService<DbEntity, EntityDto> _srv;

  @Bean
  public EntityService<DbEntity, EntityDto> entityService() {
    _srv = new AbstractBaseEntityServiceImpl() {

      @Override
      protected int getModuleId() {
        return _mod.getId();
      }
    };

    return _srv;
  }

  @Bean
  public EntityStatusService<EntityEx, EntityExDto> entityStatusService() {
    return new AbstractEntityStatusServiceImpl() {

      @Override
      protected String getNewStatus() {
        return "LL_NEW";
      }

      @Override
      protected int getModuleId() {
        return _mod.getId();
      }

      @Override
      protected EntityService<DbEntity, EntityDto> getEntityService() {
        return _srv;
      }
    };
  }

  @Bean
  public StatusService statusService() {
    return new AbstractStatusServiceImpl() {

      @Autowired
      private ModuleDto _mod;

      @Override
      protected int getModuleId() {
        return _mod.getId();
      }
    };
  }

}
