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
 * Task Error Codes
 */
public enum ErrorCodes {

// @formatter:off

  ERROR_READ_USER_CONTACTS, ERROR_READ_ALL_CONTACTS,
  ERROR_READ_USER_CLIENTS, ERROR_READ_ALL_CLIENTS, 
 
  ERROR_CREATE_CUST_COMM_HISTORY, ERROR_READ_CUST_COMM_HISTORY, 
  ERROR_READ_CUST_COMM_HISTORIES, ERROR_UPDATE_CUST_COMM_HISTORY,
  ERROR_DELETE_CUST_COMM_HISTORY, ERROR_READ_CUST_ID_COMM_HISTORIES
 
// @formatter:on

}
