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

package com.seerema.rest.shared.base.it;

import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerema.rest.shared.base.rest.SharedRestBaseTestUnit;

/**
 * Top launcher for single threaded test
 * 
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
    value = { "spring.config.name=test" })
public class SharedRestBaseTest extends SharedRestBaseTestUnit {

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
}
