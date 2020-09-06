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

package com.seerema.shared.jpa.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.seerema.base.WsSrvException;
import com.seerema.shared.dto.ModuleDto;
import com.seerema.shared.jpa.base.model.Module;
import com.seerema.shared.jpa.base.repo.ModuleRepo;
import com.seerema.srv.shared.mapper.EntityMapper;

/**
 * Abstract JPA configuration with module detection
 */

public abstract class AbstractJpaConfiguration {

  @Autowired
  private ModuleRepo _repo;

  protected abstract String getModuleName();

  @Bean
  public ModuleDto getModule() throws WsSrvException {
    // Find module
    Module module = _repo.findByName(getModuleName());

    return new EntityMapper().map(module, ModuleDto.class);
  }
}
