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

package com.seerema.rest.shared.base.rest;

import org.slf4j.Logger;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * Class used for Multi threading test
 * 
 */
public class SharedRestBaseMultiTestUnit extends SharedRestBaseTestUnit {

  private static TestRestTemplate REST_TEMPLATE;

  private static Logger LOGGER;

  private static int LOCAL_PORT;

  @Override
  protected TestRestTemplate getRestTemplate() {
    return REST_TEMPLATE;
  }

  protected int getPort() {
    return LOCAL_PORT;
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }
}
