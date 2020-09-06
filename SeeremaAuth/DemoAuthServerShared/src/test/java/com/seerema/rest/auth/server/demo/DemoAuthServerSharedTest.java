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

package com.seerema.rest.auth.server.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerema.rest.auth.server.shared.utils.AbstractAuthServerSharedTest;

/**
 * Test Configuration class with authentication manager
 * 
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
    value = { "spring.config.name=test" })
public class DemoAuthServerSharedTest extends AbstractAuthServerSharedTest {

  // Login page resources
  private static final String[] LOGIN_PAGE_RESOURCES =
      new String[] { "/login.html", "/js/main.js", "/favicon.ico" };

  /**
   * Test login.html is available and accessible
   */
  @Test
  public void testLoginPage() {
    for (String url : LOGIN_PAGE_RESOURCES)
      checkLoginResource(url);
  }

  private void checkLoginResource(String url) {
    ResponseEntity<String> resp =
        getRestTemplate().getForEntity(url, String.class);

    assertEquals(HttpStatus.OK, resp.getStatusCode(),
        "URL " + url + " not accessible.");
    assertNotNull(resp.getBody(), "URL " + url + " has no body.");
    assertTrue(resp.getBody().length() > 0, "URL " + url + " has empty body.");
  }

  @Override
  protected String[] getCredentials() {
    return new String[] { DemoAuthConstants.USER_OPERATOR_NAME,
        DemoAuthConstants.USER_OPERATOR_PWD };
  }

  @Override
  protected String getTestUser() {
    return DemoAuthConstants.USER_OPERATOR_NAME;
  }

  @Override
  protected String getTestUserRole() {
    return "ROLE_" + DemoAuthConstants.USER_OPERATOR_NAME.toUpperCase();
  }

  @Override
  protected List<String> getTestUsers() {
    return Arrays.asList(new String[] { DemoAuthConstants.USER_ADMIN_NAME,
        DemoAuthConstants.USER_OPERATOR_NAME });
  }
}
