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

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seerema.shared.Constants;

/**
 * Constants used solely by unit and/or integration test
 * 
 */
public class TestConstants {

  // - target
  public static final String TARGET_DIR = "target";

  // - target/
  public static final String TARGET_PATH = TARGET_DIR + File.separator;

  // - src/
  public static final String SRC_DIR = "src" + File.separator;

  // - src/test/
  public static final String SRC_TEST_DIR = SRC_DIR + "test" + File.separator;

  // - config
  public static final String CONFIG_DNAME = "config";

  // - config/
  public static final String CONFIG_DIR = CONFIG_DNAME + File.separator;

  public static final String SBS_SHARED = "sbs_shared";

  // - target/sbs_shared
  public static final String WORK_SBS_SHARED_DIR = TARGET_PATH + SBS_SHARED;

  // - target/sbs_shared/
  public static final String WORK_SBS_SHARED_PATH =
      WORK_SBS_SHARED_DIR + File.separator;

  //- target/sbs_shared/config.properties
  public static final String WORK_WS_CONFIG_FILE =
      TestConstants.WORK_SBS_SHARED_PATH + Constants.WS_CONFIG_FILE_NAME;

  // - /target/logs
  public static final String LOG_DIR = TARGET_PATH + "logs";

  // - target/rest
  public static final String WORK_WEBAPP_PATH =
      TestConstants.TARGET_PATH + "rest";

  // - target/rest/
  public static final String WORK_WEBAPP_DIR =
      WORK_WEBAPP_PATH + File.separator;

  // - version
  public static final String WEBAPP_VERSION_FNAME = "version";

  // - target/rest/version
  public static final String WEBAPP_VERSION_PATH =
      WORK_WEBAPP_DIR + WEBAPP_VERSION_FNAME;

  public static final Logger LOG = LoggerFactory.getLogger("Test");

  // Multi-thread parameters

  public static final int RESULT_CHECK_TIME = 10; // msec

  // Default wait time
  public static final int WAIT_TIME = 5000;

  // Doubled wait time
  public static final int WAIT_TIME2 = WAIT_TIME * 2;

  public static final int THREAD_NUM = 100;

}
