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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seerema.base.WsSrvException;
import com.seerema.rest.shared.base.controller.BaseController;
import com.seerema.shared.dto.FieldCategoryDto;
import com.seerema.shared.jpa.base.model.FieldCategory;
import com.seerema.shared.jpa.base.service.BaseEntityService;
import com.seerema.shared.rest.response.BaseResponse;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * FieldCategory REST API
 */
public class FieldCategoryController extends BaseController {

  @Autowired
  private BaseEntityService<FieldCategory, FieldCategoryDto> _service;

  @RequestMapping(value = "/admin/field_cat", method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse create(@Valid @RequestBody FieldCategoryDto dto)
      throws WsSrvException {
    return _service.createEntity(dto);
  }

  @RequestMapping(value = "/field_cats", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse readAll() throws WsSrvException {
    return _service.readEntities();
  }

  @RequestMapping(value = "/field_cat/{id}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse read(@PathVariable Integer id) throws WsSrvException {
    return _service.readEntity(id);
  }

  @RequestMapping(value = "/admin/field_cat/{id}", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse update(@Valid @RequestBody FieldCategoryDto dto)
      throws WsSrvException {
    return _service.updateEntity(dto);
  }

  @RequestMapping(value = "/admin/field_cat/{id}",
      method = RequestMethod.DELETE)
  public BaseResponse delete(@PathVariable Integer id) throws WsSrvException {
    return _service.deleteEntity(id);
  }
}
