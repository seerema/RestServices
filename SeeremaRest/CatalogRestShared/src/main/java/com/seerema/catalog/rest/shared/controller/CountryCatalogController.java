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
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.base.WsSrvException;
import com.seerema.catalog.srv.dto.CountryDto;
import com.seerema.catalog.srv.jpa.model.Country;
import com.seerema.catalog.srv.shared.CatalogConstants;
import com.seerema.rest.shared.base.common.RestBaseConstants;
import com.seerema.rest.shared.base.controller.BaseController;
import com.seerema.shared.jpa.base.service.BaseEntityService;
import com.seerema.shared.rest.response.BaseResponse;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Country REST API
 *
 */
@Validated
@RestController
@RequestMapping("/" + CatalogConstants.MODULE_NAME)
public class CountryCatalogController extends BaseController {

  @Autowired
  private BaseEntityService<Country, CountryDto> _service;

  @RequestMapping(value = "/country", method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse create(@Valid @RequestBody CountryDto country)
      throws WsSrvException {
    return _service.createEntity(country);
  }

  @RequestMapping(value = "/countries", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse readAll() throws WsSrvException {
    return _service.readEntities();
  }

  @RequestMapping(value = "/country/{id}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse read(@PathVariable Integer id) throws WsSrvException {
    return _service.readEntity(id);
  }

  @RequestMapping(value = "/country/{id}", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse update(@Valid @RequestBody CountryDto country)
      throws WsSrvException {
    return _service.updateEntity(country);
  }

  @RequestMapping(value = RestBaseConstants.PRIVATE_URL + "/country/{id}",
      method = RequestMethod.DELETE)
  public BaseResponse delete(@PathVariable Integer id) throws WsSrvException {
    return _service.deleteEntity(id);
  }

}
