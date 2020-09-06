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

package com.seerema.rest.auth.shared.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seerema.rest.auth.shared.common.AuthSharedTestConstants;
import com.seerema.rest.auth.shared.service.TestLoggerService;
import com.seerema.shared.common.UserService;

/**
 * Test Logger interface implementation
 * 
 */
@Service
public class TestLoggerServiceImpl implements TestLoggerService {

  @Autowired
  private UserService _usrv;

  @Override
  public void logRequest() {
    AuthSharedTestConstants.LOG.info(_usrv.getLoginUser());
  }

}
