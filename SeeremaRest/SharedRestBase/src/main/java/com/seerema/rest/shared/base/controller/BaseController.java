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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.seerema.rest.shared.base.common.RestBaseConstants;
import com.seerema.shared.Constants;

/**
 * Base Controller class
 *
 */
public class BaseController {

  protected String getUserName(HttpServletRequest req) {
    HttpSession session = req.getSession(false);
    if (session == null)
      return Constants.ANONYMOUS_USER;

    Object result = session.getAttribute(RestBaseConstants.USER_NAME_KEY);

    return result != null ? result.toString() : Constants.ANONYMOUS_USER;
  }
}
