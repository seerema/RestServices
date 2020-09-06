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

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.crm.srv.shared.CrmConstants;
import com.seerema.rest.entity.shared.controller.EntityFieldController;

/**
 * CRM Field REST API
 *
 */
@Validated
@RestController
@RequestMapping("/" + CrmConstants.MODULE_NAME)
public class ContactFieldController extends EntityFieldController {

}
