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

package com.seerema.task.srv.shared;

import java.util.ArrayList;
import java.util.List;

/**
 * Task Constants
 *
 */
public class TaskConstants {

  // Module ID
  // public static final int MODULE_ID = 1;

  // Module name
  public static final String MODULE_NAME = "task";

  public static String TASK_NEW_STATUS = Statuses.LL_NEW_F.name();

  public static final List<String> TASK_ACTIVE_STATUSES =
      new ArrayList<String>();

  static {
    TASK_ACTIVE_STATUSES.add(Statuses.LL_NEW_F.name());
    TASK_ACTIVE_STATUSES.add(Statuses.LL_STARTED.name());
  }
}
