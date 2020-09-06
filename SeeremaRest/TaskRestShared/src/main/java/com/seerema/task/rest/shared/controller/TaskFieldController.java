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

package com.seerema.task.rest.shared.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.rest.entity.shared.controller.FieldController;
import com.seerema.task.srv.shared.TaskConstants;

/**
 * Task Rield REST API
 *
 */
@Validated
@RestController
@RequestMapping("/" + TaskConstants.MODULE_NAME)
public class TaskFieldController extends FieldController {

}
