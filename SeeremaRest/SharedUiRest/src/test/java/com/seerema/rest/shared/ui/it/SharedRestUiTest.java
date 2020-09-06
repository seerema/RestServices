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

package com.seerema.rest.shared.ui.it;

import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerema.rest.shared.ui.rest.SharedRestUiTestUnit;

/**
 * Top launcher for single threaded test
 * 
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
    value = { "spring.config.name=test" })
public class SharedRestUiTest extends SharedRestUiTestUnit {

  static final String[][] GONFIG_TEST =
      new String[][] { new String[] { "param1", "{\"param1\":\"one\"}" },
          new String[] { "param1,param2",
              "{\"param1\":\"one\",\"param2\":\"two\"}" },
          new String[] { "param1,param2,paramxx",
              "{\"param1\":\"one\",\"param2\":\"two\"}" } };

  @Autowired
  private TestRestTemplate _rest;

  @Autowired
  private Logger _log;

  @LocalServerPort
  private int _port;

  @Override
  protected TestRestTemplate getRestTemplate() {
    return _rest;
  }

  @Override
  protected int getPort() {
    return _port;
  }

  @Override
  protected Logger getLogger() {
    return _log;
  }

  @Override
  protected String[][] getConfigTest() {
    return GONFIG_TEST;
  }
}
