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

package com.seerema.catalog.rest.shared.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.base.WsSrvException;

/**
 * Error controller to test Exception Handler
 *
 */

@RestController
public class ErrorController {

  public static String ERR_CODE = "SMTHING_GOES_WRONG";

  public static String ERR_MSG = "Something goes wrong";

  @RequestMapping("/ws_error")
  public void getWsSrvError() throws WsSrvException {
    throw new WsSrvException(ERR_CODE, ERR_MSG);
  }
}
