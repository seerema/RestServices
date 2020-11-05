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

package com.seerema.crm.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.seerema.crm.srv.shared.CrmConstants;
import com.seerema.shared.dto.ModuleDto;
import com.seerema.shared.jpa.status.service.impl.AbstractStatusServiceImpl;

/**
 * Catalog Field Service
 */

@Service("status_" + CrmConstants.MODULE_NAME)
public class CrmStatusServiceImpl extends AbstractStatusServiceImpl {

  @Autowired
  @Qualifier("mod_" + CrmConstants.MODULE_NAME)
  private ModuleDto _mod;

  @Override
  protected int getModuleId() {
    return _mod.getId();
  }
}