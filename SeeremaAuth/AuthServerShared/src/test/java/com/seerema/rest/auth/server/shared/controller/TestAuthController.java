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
package com.seerema.rest.auth.server.shared.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.rest.auth.common.Constants;

/**
 * Test REST controllers
 * 
 */
@RestController
public class TestAuthController {

  @RequestMapping(path = "/user_info", method = RequestMethod.GET)
  public String showLoginUserInfo(Authentication authentication,
      HttpServletRequest req) {
    // Insert user name was used from login from standard http session
    String message = Constants.USER_NAME_KEY + ":" +
        req.getSession(false).getAttribute(Constants.USER_NAME_KEY).toString() +
        "|";

    if (authentication != null) {
      // Insert user name was used from login from authentication object
      message += "authenticationName:" + authentication.getName() + "|roles:";
      String roles = "";
      for (GrantedAuthority authority : authentication.getAuthorities())
        roles += "," + authority.getAuthority();

      message += roles.substring(1);
    }

    return message + "|sessionId:" + req.getSession(false).getId();
  }
}
