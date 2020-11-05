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
import org.springframework.stereotype.Service;

import com.seerema.shared.dto.ModuleDto;

/**
 * Test FieldCategory Service
 */

@Service
public class TestFieldServiceImpl extends AbstractFieldServiceImpl {

  @Autowired
  private ModuleDto _mod;

  @Override
  protected int getModuleId() {
    return _mod.getId();
  }

}
