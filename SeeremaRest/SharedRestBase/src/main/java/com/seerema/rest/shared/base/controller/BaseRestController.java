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

package com.seerema.rest.shared.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.seerema.shared.config.AbstractConfig;
import com.seerema.shared.service.BaseService;

/**
 * Same as BaseController but added mapping for version
 * 
 *
 */

public abstract class BaseRestController<T extends BaseService<T1>, T1 extends AbstractConfig>
    extends BaseController {

  @Autowired
  @Qualifier("base_srv")
  private BaseService<T1> _bs;

  protected BaseService<T1> getBaseService() {
    return _bs;
  }

  protected String getVersion() {
    return _bs.getWsVersion();
  }
}
