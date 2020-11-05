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

import com.seerema.shared.dto.FieldCategoryDto;
import com.seerema.shared.jpa.base.model.FieldCategory;
import com.seerema.shared.jpa.base.service.BaseEntityService;

@Validated
@RestController
@RequestMapping("/test")
public class TestFieldCategoryController
    extends AbstractFieldCategoryController {

  @Autowired
  private BaseEntityService<FieldCategory, FieldCategoryDto> _service;

  @Override
  protected BaseEntityService<FieldCategory, FieldCategoryDto>
      getFieldCatService() {
    return _service;
  }
}
