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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.base.WsSrvException;
import com.seerema.rest.entity_ex.shared.controller.AbstractEntityExController;
import com.seerema.shared.dto.EntityExDto;
import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.shared.jpa.status.service.EntityStatusService;
import com.seerema.shared.rest.response.DataGoodResponse;
import com.seerema.task.srv.service.QuestService;
import com.seerema.task.srv.shared.TaskConstants;

/**
 * Quest REST API
 *
 */
@Validated
@RestController
@RequestMapping("/" + TaskConstants.MODULE_NAME)
public class QuestController extends AbstractEntityExController {

  @Autowired
  private QuestService _quest_service;

  /****************** USER API ******************/

  @RequestMapping(value = "/entities/active", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse readActiveUser(HttpServletRequest req)
      throws WsSrvException {
    return _quest_service.readUserActiveQuests(getUserName(req));
  }

  /****************** PRIVATE API ******************/

  @RequestMapping(value = "/private/entities/active",
      method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public DataGoodResponse readActive(HttpServletRequest req)
      throws WsSrvException {
    return _quest_service.readAllActiveQuests();
  }

  @Override
  protected EntityStatusService<EntityEx, EntityExDto>
      getEntityStatusService() {
    return _quest_service;
  }
}
