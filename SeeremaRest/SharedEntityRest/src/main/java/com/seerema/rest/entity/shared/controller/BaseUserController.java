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

package com.seerema.rest.entity.shared.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seerema.base.WsSrvException;
import com.seerema.rest.shared.base.controller.BaseController;
import com.seerema.shared.jpa.base.service.UserService;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * User REST API
 *
 */
public class BaseUserController extends BaseController {

  @Autowired
  private UserService _service;

  @RequestMapping(value = "/users", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse readAll() throws WsSrvException {
    return _service.readUsers();
  }

  @RequestMapping(value = "/user_reg", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse checkUser(HttpServletRequest req)
      throws WsSrvException {
    return _service.checkUser(getUserName(req));
  }
}
