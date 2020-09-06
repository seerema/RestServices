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

package com.seerema.task.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.seerema.base.WsSrvException;
import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.shared.jpa.status.service.impl.AbstractEntityStatusServiceImpl;
import com.seerema.shared.rest.response.DataGoodResponse;
import com.seerema.task.srv.jpa.repo.QuestRepo;
import com.seerema.task.srv.service.QuestService;
import com.seerema.task.srv.shared.ErrorCodes;
import com.seerema.task.srv.shared.TaskConstants;

/**
 * Implementation for Quest Service
 */
@Service
public class QuestServiceImpl extends AbstractEntityStatusServiceImpl
    implements QuestService {

  @Autowired
  private QuestRepo _repo;

  @Override
  public DataGoodResponse readUserActiveQuests(String username)
      throws WsSrvException {
    Iterable<EntityEx> quests;

    try {
      quests = _repo.findUserActiveQuests(TaskConstants.TASK_ACTIVE_STATUSES,
          username);
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_READ_USER_ACTIVE_QUESTS.name(), e);
    }

    return new DataGoodResponse(
        getEntityMapper().map(quests, getEntityDtoClass()));
  }

  @Override
  public DataGoodResponse readAllActiveQuests() throws WsSrvException {
    Iterable<EntityEx> quests;

    try {
      quests = _repo.findAllActiveQuests(TaskConstants.TASK_ACTIVE_STATUSES);
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_READ_ALL_ACTIVE_QUESTS.name(), e);
    }

    return new DataGoodResponse(
        getEntityMapper().map(quests, getEntityDtoClass()));
  }

  @Override
  protected String getNewStatus() {
    return TaskConstants.TASK_NEW_STATUS;
  }

}
