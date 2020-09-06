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

package com.seerema.rest.auth.base;

/**
 * SharedRest Constants
 */
public class SharedRestConstants {

  public static final String DTS_PATTERN =
      "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{1,3}";

  public static final String CREATED_FIELD_PATTERN =
      "\"created\":\"" + SharedRestConstants.DTS_PATTERN + "\"";
}
