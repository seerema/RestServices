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

package com.seerema.task.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.seerema.shared.dto.ModuleDto;
import com.seerema.shared.jpa.status.service.impl.AbstractStatusServiceImpl;
import com.seerema.task.srv.shared.TaskConstants;

/**
 * Catalog Field Service
 */

@Service("status_" + TaskConstants.MODULE_NAME)
public class TaskStatusServiceImpl extends AbstractStatusServiceImpl {

  @Autowired
  @Qualifier("mod_" + TaskConstants.MODULE_NAME)
  private ModuleDto _mod;

  @Override
  protected int getModuleId() {
    return _mod.getId();
  }
}