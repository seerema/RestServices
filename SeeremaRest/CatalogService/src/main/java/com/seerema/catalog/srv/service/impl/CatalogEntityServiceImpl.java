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

package com.seerema.catalog.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.seerema.catalog.srv.shared.CatalogConstants;
import com.seerema.shared.dto.ModuleDto;
import com.seerema.shared.jpa.base.service.impl.AbstractBaseEntityServiceImpl;

/**
 * Catalog Entity Service
 */

@Service("entity_" + CatalogConstants.MODULE_NAME)
public class CatalogEntityServiceImpl extends AbstractBaseEntityServiceImpl {

  @Autowired
  @Qualifier("mod_" + CatalogConstants.MODULE_NAME)
  private ModuleDto _mod;

  @Override
  protected int getModuleId() {
    return _mod.getId();
  }

}