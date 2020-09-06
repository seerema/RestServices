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

import com.seerema.base.WsSrvException;

public interface RequestLogger {

  /**
   * Get Request Id
   * 
   * @return the request id
   */
  public Long getRequestId();

  void setLogger(Logger logger);

  void error(String s);

  void error(Exception e);

  void error(WsSrvException e);

  void error(String errId, String errInfo, String[] errDetails);

  void error(String errId, String[] errDetails);

  void error(int httpCode, String errId, String errInfo, String[] errDetails);

  void error(String[] dmsg);

  void error(int id, String msg, String dmsg);

  void error_ex(String key, String msg);

  void warn(String msg);

  void debug(String msg);

  void debug_ex(String key, String value);

  void debug_ex(String key, int value);

  void debug_ex(String key, String[] value);

  void info(String msg);

  void trace(String msg);

  /**
   * Get Login User
   * @return
   */
  public String getLoginUser();
}