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

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.seerema.shared.rest.config.TestRestConstants;

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

  /**
   * Read App Version from WebApp version file
   * 
   * @throws IOException
   */
  public static String readAppVersion() throws IOException {
    File f = new File(TestRestConstants.WEBAPP_VERSION_PATH);
    if (!f.exists())
      fail("File '" + TestRestConstants.WEBAPP_VERSION_PATH + " not found");

    BufferedReader vf = new BufferedReader(new FileReader(f));
    String version = vf.readLine();
    vf.close();

    assertNotNull(version, "Version in " +
        TestRestConstants.WEBAPP_VERSION_PATH + " file is null");
    assertNotEquals("", version, "Version in " +
        TestRestConstants.WEBAPP_VERSION_PATH + " file is empty");

    return version;
  }
}
