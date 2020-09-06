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

package com.seerema.rest.shared.ui.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.seerema.rest.shared.base.rest.SharedRestBaseTestUnit;

/**
 * Base class for web test unit
 * 
 */
public abstract class AbstractSharedRestUiTestUnit
    extends SharedRestBaseTestUnit {

  public static final String CONFIG_APP_NAME = "cfg";

  public static String CFG_PATH = "/" + CONFIG_APP_NAME;

  /**
   * Get data for public configuration test
   * 
   * @return 2D Array
   */
  protected abstract String[][] getConfigTest();

  @Test
  public void testConfigBadEx() throws Exception {
    // Test same but login

    assertEquals(HttpStatus.BAD_REQUEST.value(), getRestTemplate()
        .getForEntity(CFG_PATH, String.class).getStatusCodeValue());
  }

  @Test
  public void testConfigGood() throws Exception {
    for (String[] ctest : getConfigTest())
      checkWebResponse(CFG_PATH + "?lst=" + ctest[0], ctest[1]);
  }
}
