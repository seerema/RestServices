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

package com.seerema.rest.entity_ex.shared.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seerema.base.WsSrvException;
import com.seerema.rest.shared.base.controller.BaseController;
import com.seerema.shared.jpa.status.service.StatusService;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Status REST API
 *
 */
public abstract class AbstractStatusController extends BaseController {

  // @Autowired
  // private StatusService _service;

  protected abstract StatusService getStatusService();

  @RequestMapping(value = "/statuses", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse readAll() throws WsSrvException {
    return getStatusService().readStatuses();
  }

}
