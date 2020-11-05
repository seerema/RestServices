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

package com.seerema.rest.entity.shared.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.seerema.rest.shared.base.rest.AbstractSharedEntityControllerTest;
import com.seerema.shared.dto.ModuleDto;

/**
 * Integration test for Base Quest Controller
 */

public abstract class AbstractSharedEntityRestControllerTest<T1>
    extends AbstractSharedEntityControllerTest<T1> {

  @Autowired
  private ModuleDto _mod;

  @Override
  protected String getModuleName() {
    return _mod.getName();
  }

  protected ModuleDto getModule() {
    return _mod;
  }

  @Override
  protected String getSecurityPrefix() {
    return "";
  }
}