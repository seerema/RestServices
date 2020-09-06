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

package com.seerema.rest.auth.common.service;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.seerema.rest.auth.common.Constants;
import com.seerema.shared.common.UserService;

/**
 * Implementation of UserService for authenticated user access
 * 
 */
public class AuthUserService implements UserService, Serializable {

  // Default Serial Version UID
  private static final long serialVersionUID = 1L;

  private String _luser;

  @Autowired
  public void setLoginUser(HttpSession session) {
    _luser = session.getAttribute(Constants.USER_NAME_KEY).toString();
  }

  @Override
  public String getLoginUser() {
    return _luser;
  }

}
