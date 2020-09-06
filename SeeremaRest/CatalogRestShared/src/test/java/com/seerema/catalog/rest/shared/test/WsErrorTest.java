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

package com.seerema.catalog.rest.shared.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * protected integration test for Base Country Controller
 *
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:/test.properties")
public class WsErrorTest {

  @Autowired
  private TestRestTemplate _rest;

  @Test
  protected void testWsError() {
    String response = _rest.getForObject("/ws_error", String.class);

    Pattern p = Pattern.compile(
        "\\{\"result\":false,\"error\":\\{\"id\":\"SMTHING_GOES_WRONG\"" +
            ",\"info\":\"Something goes wrong\",\"request_id\":\\d*\\}\\}");

    assertTrue(p.matcher(response).matches(), "Error response doesn't match.");
  }
}
