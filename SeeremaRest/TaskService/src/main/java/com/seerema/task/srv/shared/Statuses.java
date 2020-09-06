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

/**
 * List of statuses. 
 * Must correspond defined in status table for "task" module
 * First value, i.e LL_NEW, must correspond the status for new Quest
 *
 */
public enum Statuses {

  LL_NEW_F, LL_STARTED, LL_ON_HOLD, LL_COMPLETED, LL_CANCEL
}
