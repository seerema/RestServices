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

package com.seerema.task.srv.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.seerema.base.WsSrvException;
import com.seerema.shared.Constants;
import com.seerema.shared.jpa.status.test.AbstractEntityStatusServiceTest;
import com.seerema.task.srv.service.QuestService;

/**
 * Task Service Tests
 */
public class TaskServiceTest extends AbstractEntityStatusServiceTest {

  @Autowired
  private QuestService questSrv;

  @Override
  protected String getFieldCategoryName() {
    return "LL_REGULAR_TASK";
  }

  @Override
  protected String getEntityName() {
    return "TO-DO";
  }

  @Override
  protected String getFieldName() {
    return "LL_NOTES";
  }

  @Override
  protected String getEntityFieldValue() {
    return "Sample TO_DO task";
  }

  @Override
  protected int getStatusNum() {
    return 4;
  }

  @Override
  protected void runCheck(int idx) throws WsSrvException {
    switch (idx) {
    case 1:
      // Get list of user's active tasks
      checkEntitiesList(questSrv.readUserActiveQuests(Constants.ANONYMOUS_USER),
          2, "UserActiveQuests");

      // Get list of user's all tasks
      checkEntitiesList(questSrv.readAllActiveQuests(), 2, "AllActiveQuests");

      break;

    case 2:
      // Expects no user's tasks for TEST_USER_NAME since 
      // task update doesn't change username
      checkEntitiesList(questSrv.readEntities(getTestUserName()), 0,
          "UserQuests");

      // Expects no active user's tasks for TEST_USER_NAME since 
      // task update doesn't change username
      checkEntitiesList(questSrv.readUserActiveQuests(getTestUserName()), 0,
          "UserActiveQuests");

      // Get list of all quests
      checkAllEntitiesList(2, "AllQuests");

      // Get list of all active quests
      checkEntitiesList(questSrv.readAllActiveQuests(), 2, "UserActiveQuests");

      break;

    case 3:
      // Get list of user's active tasks
      checkEntitiesList(questSrv.readUserActiveQuests(Constants.ANONYMOUS_USER),
          2, "UserActiveQuests");
      break;

    // Status changed
    case 4:
      // Get list of user's active tasks
      checkEntitiesList(questSrv.readUserActiveQuests(Constants.ANONYMOUS_USER),
          1, "UserActiveQuests");

      checkEntitiesList(questSrv.readAllActiveQuests(), 1, "AllUserQuests");

      // Get list of user's all tasks
      checkEntitiesList(questSrv.readEntities(Constants.ANONYMOUS_USER), 2,
          "AllUserQuests");
      break;

    default:
      // Do nothing
    }
  }
}
