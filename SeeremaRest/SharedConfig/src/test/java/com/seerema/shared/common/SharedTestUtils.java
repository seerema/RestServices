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

package com.seerema.shared.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Shared utilities
 * 
 */
public abstract class SharedTestUtils {

  public static void testMsg(String msg, String expected, String actual) {
    if (expected != null && !expected.isEmpty() && expected.charAt(0) == 64 &&
        expected.charAt(expected.length() - 1) == 64) {
      // Process regular expression
      Pattern p = Pattern.compile(expected.substring(1, expected.length() - 1));
      assertTrue(p.matcher(actual).matches(),
          "Checking regex " + expected + " -> " + actual);
    } else {
      assertEquals(expected, actual, msg);
    }
  }

  /**
   * Read App Version from WebApp version file
   * 
   * @throws IOException
   */
  public static String readAppVersion() throws IOException {
    return readAppVersion(TestConstants.WORK_WEBAPP_DIR);
  }

  /**
   * Read App Version from WebApp version file
   * 
   * @param dir Directory where version file is located
   * @throws IOException
   */
  public static String readAppVersion(String dir) throws IOException {
    File f = new File(dir + TestConstants.WEBAPP_VERSION_FNAME);
    if (!f.exists())
      fail("File '" + TestConstants.WEBAPP_VERSION_PATH + " not found");

    BufferedReader vf = new BufferedReader(new FileReader(f));
    String version = vf.readLine();
    vf.close();

    assertNotNull(version,
        "Version in " + TestConstants.WEBAPP_VERSION_PATH + " file is null");
    assertNotEquals("", version,
        "Version in " + TestConstants.WEBAPP_VERSION_PATH + " file is empty");

    return version;
  }
}
