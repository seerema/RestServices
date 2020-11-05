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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seerema.base.WsSrvException;
import com.seerema.rest.shared.base.controller.BaseController;
import com.seerema.shared.dto.EntityExDto;
import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.shared.jpa.status.service.EntityStatusService;
import com.seerema.shared.rest.response.BaseResponse;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Entity REST API
 */
public abstract class AbstractEntityExController extends BaseController {

  protected abstract EntityStatusService<EntityEx, EntityExDto>
      getEntityStatusService();

  /****************** USER API ******************/

  @RequestMapping(value = "/entity", method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse create(@Valid @RequestBody EntityExDto entity,
      HttpServletRequest req) throws WsSrvException {
    return getEntityStatusService().createEntity(entity, getUserName(req));
  }

  @RequestMapping(value = "/entities", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse readUserEntities(HttpServletRequest req)
      throws WsSrvException {
    return getEntityStatusService().readEntities(getUserName(req));
  }

  @RequestMapping(value = "/entity/{id}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse read(@PathVariable Integer id, HttpServletRequest req)
      throws WsSrvException {
    return getEntityStatusService().readEntity(id, getUserName(req));
  }

  @RequestMapping(value = "/entity/{id}", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse update(@Valid @RequestBody EntityExDto entity,
      HttpServletRequest req) throws WsSrvException {
    return getEntityStatusService().updateEntity(entity, getUserName(req),
        false);
  }

  /****************** PRIVATE API ******************/

  @RequestMapping(value = "/private/entity/{id}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse read(@PathVariable Integer id) throws WsSrvException {
    return getEntityStatusService().readEntity(id);
  }

  @RequestMapping(value = "/private/entities", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse readEntities(HttpServletRequest req)
      throws WsSrvException {
    return getEntityStatusService().readEntities();
  }

  @RequestMapping(value = "/private/entity/{id}", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse updateRestricted(@Valid @RequestBody EntityExDto entity,
      HttpServletRequest req) throws WsSrvException {
    return getEntityStatusService().updateEntity(entity, getUserName(req),
        true);
  }

  @RequestMapping(value = "/private/entity/{id}", method = RequestMethod.DELETE)
  public BaseResponse delete(@PathVariable Integer id) throws WsSrvException {
    return getEntityStatusService().deleteEntity(id);
  }
}
