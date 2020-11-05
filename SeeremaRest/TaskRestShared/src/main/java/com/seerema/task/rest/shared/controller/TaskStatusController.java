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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.rest.entity_ex.shared.controller.AbstractStatusController;
import com.seerema.shared.jpa.status.service.StatusService;
import com.seerema.task.srv.shared.TaskConstants;

/**
 * Task Field REST API
 *
 */

@Validated
@RestController
@RequestMapping("/" + TaskConstants.MODULE_NAME)
public class TaskStatusController extends AbstractStatusController {

  @Autowired
  @Qualifier("status_" + TaskConstants.MODULE_NAME)
  private StatusService _service;

  @Override
  protected StatusService getStatusService() {
    return _service;
  }
}
