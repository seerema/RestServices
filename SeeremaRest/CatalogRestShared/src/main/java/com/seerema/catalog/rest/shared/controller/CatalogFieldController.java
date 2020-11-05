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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.catalog.srv.shared.CatalogConstants;
import com.seerema.rest.entity.shared.controller.AbstractFieldController;
import com.seerema.shared.dto.FieldDto;
import com.seerema.shared.jpa.base.model.Field;
import com.seerema.shared.jpa.base.service.BaseEntityService;

/**
 * Field REST API
 *
 */
@Validated
@RestController
@RequestMapping("/" + CatalogConstants.MODULE_NAME)
public class CatalogFieldController extends AbstractFieldController {

  @Autowired
  @Qualifier("field_" + CatalogConstants.MODULE_NAME)
  private BaseEntityService<Field, FieldDto> _service;

  @Override
  protected BaseEntityService<Field, FieldDto> getFieldService() {
    return _service;
  }
}
