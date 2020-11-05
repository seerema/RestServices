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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.seerema.base.WsSrvException;
import com.seerema.shared.dto.EntityDto;
import com.seerema.shared.dto.ModuleDto;
import com.seerema.shared.jpa.base.model.DbEntity;
import com.seerema.shared.jpa.base.service.EntityService;
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

  @Autowired
  @Qualifier("mod_" + TaskConstants.MODULE_NAME)
  private ModuleDto _mod;

  @Autowired
  @Qualifier("entity_" + TaskConstants.MODULE_NAME)
  private EntityService<DbEntity, EntityDto> _srv;

  @Override
  protected int getModuleId() {
    return _mod.getId();
  }

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

  @Override
  protected EntityService<DbEntity, EntityDto> getEntityService() {
    return _srv;
  }
}
