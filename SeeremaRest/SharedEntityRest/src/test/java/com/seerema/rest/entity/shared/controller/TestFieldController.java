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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.shared.dto.FieldDto;
import com.seerema.shared.jpa.base.model.Field;
import com.seerema.shared.jpa.base.service.BaseEntityService;

@Validated
@RestController
@RequestMapping("/test")
public class TestFieldController extends AbstractFieldController {

  @Autowired
  private BaseEntityService<Field, FieldDto> _service;

  @Override
  protected BaseEntityService<Field, FieldDto> getFieldService() {
    return _service;
  }

}
