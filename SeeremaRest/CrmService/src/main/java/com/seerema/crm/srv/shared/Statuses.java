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

package com.seerema.crm.srv.shared;

/**
 * List of statuses. 
 * Must correspond defined in status table for "task" module
 * First value, i.e LL_NEW, must correspond the status for new Contact
 *
 */
public enum Statuses {

  LL_LEAD, LL_CUSTOMER, LL_INACTIVE
}
