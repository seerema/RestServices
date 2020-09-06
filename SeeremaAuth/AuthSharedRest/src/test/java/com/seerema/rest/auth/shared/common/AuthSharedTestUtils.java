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

package com.seerema.rest.auth.shared.common;

/**
 * Test Utilities
 */
public class AuthSharedTestUtils {

  public static String clearJsonPattern(String pattern) {
    String res = pattern.replaceAll("\\\\\\{", "{").replaceAll("\\\\\\[", "[")
        .replaceAll("\\\\\\]", "]").replaceAll("\\\\\\}", "}");

    if (res.charAt(0) == '^')
      res = res.substring(1);

    if (res.charAt(res.length() - 1) == '$')
      res = res.substring(0, res.length() - 1);

    return res;
  }
}
