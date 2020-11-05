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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.crm.srv.shared.CrmConstants;
import com.seerema.rest.entity.shared.controller.AbstractFieldCategoryController;
import com.seerema.shared.dto.FieldCategoryDto;
import com.seerema.shared.jpa.base.model.FieldCategory;
import com.seerema.shared.jpa.base.service.BaseEntityService;

/**
 * CRM FieldCategory REST API
 *
 */
@Validated
@RestController
@RequestMapping("/" + CrmConstants.MODULE_NAME)
public class CrmFieldCategoryController
    extends AbstractFieldCategoryController {

  @Autowired
  @Qualifier("field_cat_" + CrmConstants.MODULE_NAME)
  private BaseEntityService<FieldCategory, FieldCategoryDto> _service;

  @Override
  protected BaseEntityService<FieldCategory, FieldCategoryDto>
      getFieldCatService() {
    return _service;
  }
}
