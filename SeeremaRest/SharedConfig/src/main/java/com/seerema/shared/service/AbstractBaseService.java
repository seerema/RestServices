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

package com.seerema.shared.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;

import com.seerema.shared.config.RestConfig;

public abstract class AbstractBaseService<T extends RestConfig>
    implements BaseService<T> {

  @Autowired
  @Qualifier("rest_info")
  private RestInfo _info;

  @Autowired
  @Qualifier("log")
  private Logger _log;

  @Override
  public String getWsVersion() {
    return StringUtils.isEmpty(getRestInfo().getRestVersion()) ? "local"
        : getRestInfo().getRestVersion();
  }

  @Override
  public RestInfo getRestInfo() {
    return _info;
  }

  @Override
  public Logger getLogger() {
    return _log;
  }

  @Override
  public String getAppVersion() {
    return getRestInfo().getBuildVersion();
  }
}
