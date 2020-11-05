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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.base.WsSrvException;
import com.seerema.catalog.srv.shared.CatalogConstants;
import com.seerema.rest.shared.base.common.RestBaseConstants;
import com.seerema.rest.shared.base.controller.BaseController;
import com.seerema.shared.dto.EntityDto;
import com.seerema.shared.jpa.base.model.DbEntity;
import com.seerema.shared.jpa.base.service.EntityService;
import com.seerema.shared.rest.response.BaseResponse;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Entity REST API
 *
 */
@Validated
@RestController
@RequestMapping("/" + CatalogConstants.MODULE_NAME)
public class BusinessInfoController extends BaseController {

  @Autowired
  @Qualifier("entity_" + CatalogConstants.MODULE_NAME)
  private EntityService<DbEntity, EntityDto> _service;

  @RequestMapping(value = "/entity", method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse create(@Valid @RequestBody EntityDto business_info)
      throws WsSrvException {
    return _service.createEntity(business_info);
  }

  @RequestMapping(value = "/entities", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse readAll() throws WsSrvException {
    return _service.readEntities();
  }

  @RequestMapping(value = "/entity/{id}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse read(@PathVariable Integer id) throws WsSrvException {
    return _service.readEntity(id);
  }

  @RequestMapping(value = "/entity/{id}", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse update(@Valid @RequestBody EntityDto business_info)
      throws WsSrvException {
    return _service.updateEntity(business_info);
  }

  @RequestMapping(value = RestBaseConstants.PRIVATE_URL + "/entity/{id}",
      method = RequestMethod.DELETE)
  public BaseResponse delete(@PathVariable Integer id) throws WsSrvException {
    return _service.deleteEntity(id);
  }
}
