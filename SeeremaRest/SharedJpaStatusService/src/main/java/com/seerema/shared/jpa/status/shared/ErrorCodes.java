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

package com.seerema.shared.jpa.status.shared;

public enum ErrorCodes {

// @formatter:off
  
  ERROR_CREATE_STATUS_HISTORY, ERROR_READ_STATUS, USER_ACCESS_DENIED,

  ERROR_INIT_ENTITY_EX, ERROR_CREATE_ENTITY_EX, ERROR_CREATE_ENTITY_EX_USER, ERROR_READ_ENTITY_EX, ERROR_READ_ENTITIES_EX, 
  ERROR_READ_ENTITIES_EX_USER, ERROR_UPDATE_ENTITY_EX, ERROR_UPDATE_ENTITY_EX_USER, ERROR_DELETE_ENTITY_EX, INVALID_UPDATE_ENTITY_EX,
  ERROR_FIND_USER, ENTITY_ID_NOT_FOUND, USER_ID_NOT_FOUND, ERROR_FIND_OWNER_ID

// @formatter:on

}
