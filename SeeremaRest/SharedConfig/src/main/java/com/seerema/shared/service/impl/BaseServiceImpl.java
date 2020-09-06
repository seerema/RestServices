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

package com.seerema.shared.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.seerema.shared.config.RestConfig;
import com.seerema.shared.service.AbstractBaseService;

@Service("base_srv")
public class BaseServiceImpl extends AbstractBaseService<RestConfig> {

  @Autowired(required = false)
  @Qualifier("rest_cfg")
  private RestConfig _wcfg;

  @Override
  public RestConfig getWsConfig() {
    return _wcfg;
  }

}
