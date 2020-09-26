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

package com.seerema.shared.rest.config;

import java.util.concurrent.CountDownLatch;

/**
 * Constants used solely by unit and/or integration test
 * 
 */
public class TestRestConstants {

  // Sync lock
  public static Object lock = new Object();

  // Synchronization counters
  public static final CountDownLatch start = new CountDownLatch(1);

  // Test entity name
  public static final Object TEST_ENTITY_NAME = "qwe.rty";

  public static CountDownLatch done;

  // Number of errors
  public static int errCount = 0;

}
