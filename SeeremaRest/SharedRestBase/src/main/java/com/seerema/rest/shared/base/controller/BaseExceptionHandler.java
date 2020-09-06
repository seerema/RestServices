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
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.seerema.base.WsSrvException;
import com.seerema.shared.IServiceErrorCode;
import com.seerema.shared.config.RestConfig;
import com.seerema.shared.rest.response.BadResponse;
import com.seerema.shared.service.RequestLogger;

@ControllerAdvice
public class BaseExceptionHandler {

  @Autowired
  @Qualifier("rlog")
  private RequestLogger _rlog;

  @Autowired(required = false)
  @Qualifier("rest_cfg")
  private RestConfig _rcfg;

  @ExceptionHandler(WsSrvException.class)
  public ResponseEntity<BadResponse> handleWsSrvException(WsSrvException e,
      HttpServletResponse resp, HttpServletRequest req) {
    e.setRequestId(_rlog.getRequestId());
    _rlog.error(e);

    if (e instanceof IServiceErrorCode)
      resp.setStatus(
          ((IServiceErrorCode) e).getServiceErrorCode(e.getErrorCode()));

    // Check if debug parameter supplied
    String[] debug = req.getParameterValues("debug");
    return new ResponseEntity<BadResponse>(
        new BadResponse(e,
            _rcfg.getDebug() && debug != null && "on".equals(debug[0])),
        HttpStatus.OK);
  }
}
