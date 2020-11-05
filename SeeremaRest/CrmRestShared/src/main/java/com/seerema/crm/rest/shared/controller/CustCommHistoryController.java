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
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.base.WsSrvException;
import com.seerema.crm.srv.dto.CustCommHistoryDto;
import com.seerema.crm.srv.service.CustCommHistoryService;
import com.seerema.crm.srv.shared.CrmConstants;
import com.seerema.rest.shared.base.controller.BaseController;
import com.seerema.shared.rest.response.BaseResponse;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * CRM CustCommHistory REST API
 *
 */
@Validated
@RestController
@RequestMapping("/" + CrmConstants.MODULE_NAME)
public class CustCommHistoryController extends BaseController {

  @Autowired
  private CustCommHistoryService _service;

  @RequestMapping(value = "/cust_comm_history", method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse create(@Valid @RequestBody CustCommHistoryDto cch,
      HttpServletRequest req) throws WsSrvException {
    return _service.createEntity(cch, getUserName(req));
  }

  @RequestMapping(value = "/cust_comm_histories/{id}",
      method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse readCustCommHistories(
      @PathVariable("id") Integer entityId) throws WsSrvException {
    return _service.readCustCommHistories(entityId);
  }

  @RequestMapping(value = "/cust_comm_history/{id}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse read(@PathVariable Integer id) throws WsSrvException {
    return _service.readEntity(id);
  }

  @RequestMapping(value = "/cust_comm_history/{id}",
      method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse update(@Valid @RequestBody CustCommHistoryDto cch,
      HttpServletRequest req) throws WsSrvException {
    return _service.updateEntity(cch, getUserName(req), false);
  }

  @RequestMapping(value = "/private/cust_comm_history/{id}",
      method = RequestMethod.DELETE)
  public BaseResponse delete(@PathVariable Integer id) throws WsSrvException {
    return _service.deleteEntity(id);
  }
}
