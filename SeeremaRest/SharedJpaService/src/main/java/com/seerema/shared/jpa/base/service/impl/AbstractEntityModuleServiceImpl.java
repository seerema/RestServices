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

package com.seerema.shared.jpa.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.seerema.base.WsSrvException;
import com.seerema.shared.dto.AbstractEntityDto;
import com.seerema.shared.dto.ModuleDto;
import com.seerema.shared.jpa.base.model.AbstractEntityModule;
import com.seerema.shared.jpa.base.model.Module;

/**
 * Partial Implementation for BaseEntity Service with Module link
 *
 * @param <T1> Entity
 * @param <T2> EntityDto
 */

public abstract class AbstractEntityModuleServiceImpl<T1 extends AbstractEntityModule, T2 extends AbstractEntityDto>
    extends AbstractEntityServiceImpl<T1, T2> {

  @Autowired
  private ModuleDto _mod;

  @Override
  public T1 createEntity(T1 entity) throws WsSrvException {
    // Inject module into entity
    entity.setModule(new Module(_mod.getId()));

    return super.createEntity(entity);
  }

  @Override
  public T1 updateEntity(T1 entity) throws WsSrvException {
    // Inject module into entity
    entity.setModule(new Module(_mod.getId()));

    return super.updateEntity(entity);
  }

}
