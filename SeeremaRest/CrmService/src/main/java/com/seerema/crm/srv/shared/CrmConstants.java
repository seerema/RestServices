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

import java.util.ArrayList;
import java.util.List;

/**
 * Task Constants
 *
 */
public class CrmConstants {

  // Module name
  public static final String MODULE_NAME = "crm";

  public static String CRM_NEW_STATUS = Statuses.LL_NEW.name();

  public static final List<String> CRM_CONTACT_STATUSES =
      new ArrayList<String>();

  static {
    CRM_CONTACT_STATUSES.add(Statuses.LL_NEW.name());
  }
}
