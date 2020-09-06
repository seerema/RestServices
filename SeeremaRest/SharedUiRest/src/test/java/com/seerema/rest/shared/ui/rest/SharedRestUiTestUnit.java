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

package com.seerema.rest.shared.ui.rest;

import java.util.HashMap;

import com.seerema.rest.shared.base.utils.GenericRestTestUnit;
import com.seerema.rest.shared.ui.utils.AbstractSharedRestUiTestUnit;

/**
 * Test Units
 * 
 */
public abstract class SharedRestUiTestUnit
    extends AbstractSharedRestUiTestUnit {

  @Override
  protected String[] getWepAppPath() {
    String[] list = super.getWepAppPath();

    // Copy super list and add extra
    String[] res = new String[list.length + 1];
    for (int i = 0; i < list.length; i++)
      res[i] = list[i];

    res[list.length] = "cfg";
    return res;
  }

  @Override
  protected boolean hasPenTestQueryParam(String url, String method) {
    return super.hasPenTestQueryParam(url, method) ||
        method.equals("get") && url.endsWith("cfg");
  }

  @Override
  protected HashMap<String, Integer> getExpectedAuthPenTestRes(String url) {
    HashMap<String, Integer> map = super.getExpectedAuthPenTestRes(url);
    return map != null ? map : url.endsWith("cfg")
        ? GenericRestTestUnit.ONLY_GET_ALLOW_TEST_RES : null;
  }

}
