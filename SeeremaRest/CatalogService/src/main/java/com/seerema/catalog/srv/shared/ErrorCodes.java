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

package com.seerema.catalog.srv.shared;

/**
 * Catalog Error Codes
 */
public enum ErrorCodes {

// @formatter:off

  INVALID_KEY, ERROR_INIT_STATE, 
  ERROR_FIND_ALL_REGIONS, ERROR_FIND_ALL_COUNTRIES, ERROR_FIND_ALL_CITIES,
  
  ERROR_CREATE_COUNTRY, ERROR_READ_COUNTRY, ERROR_READ_COUNTRIES, ERROR_UPDATE_COUNTRY, ERROR_DELETE_COUNTRY, 
  ERROR_CREATE_REGION, ERROR_READ_REGION, ERROR_READ_REGIONS, ERROR_UPDATE_REGION, ERROR_DELETE_REGION, 
  ERROR_CREATE_CITY, ERROR_READ_CITY, ERROR_READ_CITIES, ERROR_UPDATE_CITY, ERROR_DELETE_CITY, 
  ERROR_CREATE_ADDRESS, ERROR_READ_ADDRESS, ERROR_READ_ADDRESSES, ERROR_UPDATE_ADDRESS, ERROR_DELETE_ADDRESS
 
// @formatter:on

}
