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

package com.seerema.rest.shared.base.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Shared utilities
 * 
 */
public abstract class CommonWebTestUtils {

  // Special static version for HttpMethod.GET
  public static String readGetEx(String url)
      throws MalformedURLException, IOException {
    String res = "";
    BufferedReader reader = null;

    try {
      HttpURLConnection conn;
      conn = (HttpURLConnection) (new URL(url)).openConnection();

      reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

      String line;

      while ((line = reader.readLine()) != null)
        res += line;

    } finally {
      if (reader != null)
        reader.close();
    }

    return res;
  }
}
