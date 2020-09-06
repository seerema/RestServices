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

package com.seerema.rest.shared.base.common;

import com.seerema.shared.Constants;

/**
 * Constants used solely by REST Web Services
 * 
 */

public class RestBaseConstants {

  // Entity Name in path parameter
  public static final String REQ_ENTITY_PARAM_NAME = "name";

  // Full entity name
  // Path name variable
  public static final String ENTITY_FULL_NAME_REGEX =
      Constants.ID_REGEX + "\\." + Constants.ID_REGEX;

  // Short entity name for url path name variable
  public static final String PATH_SHORT_NAME_PARAM =
      "/{" + REQ_ENTITY_PARAM_NAME + ":" + Constants.ID_REGEX + "}";

  // Long (default) path name variable
  public static final String PATH_NAME_PARAM =
      "/{" + REQ_ENTITY_PARAM_NAME + ":" + ENTITY_FULL_NAME_REGEX + "}";

  // Name of rest template field in multi-test classes
  public static final String REST_TEMPLATE_FIELD_NAME = "REST_TEMPLATE";

  // Name of log field in multi-test classes
  public static final String LOG_FIELD_NAME = "LOGGER";

  // Name of local port field in multi-test classes
  public static final String LOCAL_PORT_FIELD_NAME = "LOCAL_PORT";

  // Session parameter name that that was set by authentication server. It holds
  // user name used during login
  public static final String USER_NAME_KEY = "userName";
}
