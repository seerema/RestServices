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

import com.seerema.shared.config.AbstractConfig;

public interface BaseService<T extends AbstractConfig> {

  public String getAppVersion();

  T getWsConfig();

  public RestInfo getRestInfo();

  public Logger getLogger();

  public String getWsVersion();
}
