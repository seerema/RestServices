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

package com.seerema.crm.rest.shared.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.base.WsSrvException;
import com.seerema.crm.srv.service.CustomerService;
import com.seerema.crm.srv.shared.CrmConstants;
import com.seerema.rest.entity_ex.shared.controller.AbstractEntityExController;
import com.seerema.shared.dto.EntityExDto;
import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.shared.jpa.status.service.EntityStatusService;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * CRM Contact REST API
 *
 */
@Validated
@RestController
@RequestMapping("/" + CrmConstants.MODULE_NAME)
public class CustomerController extends AbstractEntityExController {

  @Autowired
  private CustomerService _cust_service;

  /****************** USER API ******************/

  @RequestMapping(value = "/entities/lead", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse readUserLeads(HttpServletRequest req)
      throws WsSrvException {
    return _cust_service.findUserLeads(getUserName(req));
  }

  @RequestMapping(value = "/entities/customer", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse readUserCustomers(HttpServletRequest req)
      throws WsSrvException {
    return _cust_service.findUserCustomers(getUserName(req));
  }

  @Override
  protected EntityStatusService<EntityEx, EntityExDto>
      getEntityStatusService() {
    return _cust_service;
  }
}
