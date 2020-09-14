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
import com.seerema.catalog.srv.shared.CatalogConstants;
import com.seerema.rest.shared.base.controller.BaseController;
import com.seerema.shared.dto.CommMediaDto;
import com.seerema.shared.jpa.base.model.CommMedia;
import com.seerema.shared.jpa.base.service.BaseEntityService;
import com.seerema.shared.rest.response.BaseResponse;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * CommMedia REST API
 *
 */
@Validated
@RestController
@RequestMapping("/" + CatalogConstants.MODULE_NAME)
public class CommMediaCatalogController extends BaseController {

  @Autowired
  private BaseEntityService<CommMedia, CommMediaDto> _service;

  @RequestMapping(value = "/comm_media", method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse create(@Valid @RequestBody CommMediaDto cmedia)
      throws WsSrvException {
    return _service.createEntity(cmedia);
  }

  @RequestMapping(value = "/comm_medias", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse readAll() throws WsSrvException {
    return _service.readEntities();
  }

  @RequestMapping(value = "/comm_media/{id}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse read(@PathVariable Integer id) throws WsSrvException {
    return _service.readEntity(id);
  }

  @RequestMapping(value = "/comm_media/{id}", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse update(@Valid @RequestBody CommMediaDto cmedia)
      throws WsSrvException {
    return _service.updateEntity(cmedia);
  }

  @RequestMapping(value = "/comm_media/{id}", method = RequestMethod.DELETE)
  public BaseResponse delete(@PathVariable Integer id) throws WsSrvException {
    return _service.deleteEntity(id);
  }
}
