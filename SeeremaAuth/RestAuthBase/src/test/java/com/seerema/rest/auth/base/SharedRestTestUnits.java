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

package com.seerema.rest.auth.base;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.seerema.rest.auth.shared.common.AuthSharedTestUtils;
import com.seerema.rest.auth.shared.test.SpringSessionSecurityTest;

/**
 * Test Configuration class for Base Rest API
 * 
 */

public abstract class SharedRestTestUnits extends SpringSessionSecurityTest {

  private final static String[] NON_AUTH_URL =
      new String[] { "/actuator/health", "/actuator/info" };

  protected abstract String getBaseUrl();

  protected abstract String[] getAuthUrls();

  /**
   * Context Load
   */
  @Test
  public void testNonAuthUrl() {
    for (String url : NON_AUTH_URL) {
      ResponseEntity<String> response =
          getRestTemplate().getForEntity(url, String.class);

      assertEquals(HttpStatus.OK, response.getStatusCode(),
          "No access to public url " + url);
      assertNotNull(response.getBody());
    }
  }

  @Test
  public void testAccessDenied() {
    for (String url : getAuthUrls()) {
      ResponseEntity<String> response =
          getRestTemplate().getForEntity(getBaseUrl() + url, String.class);

      assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
      assertNotNull(response.getBody());
    }
  }

  protected void checkEntity(RequestEntity<?> request, String name,
      String record) throws URISyntaxException {
    // Create entity
    String response = checkRestResponse(request);
    assertEquals("{\"result\":true,\"data\":[" + record + "]}", response,
        "REST response doesn't match with expected.");

    checkEntityEx(request, name);
  }

  protected void checkEntityRegex(RequestEntity<?> request, String name,
      String regex) throws URISyntaxException {
    // Create entity
    String response = checkRestResponse(request);
    String ptrn = "\\{\"result\":true,\"data\":\\[" + regex + "\\]\\}";
    Pattern p = Pattern.compile(ptrn);

    if (!p.matcher(response).matches()) {
      System.out.println(AuthSharedTestUtils.clearJsonPattern(ptrn));
      System.out.println(response);
      fail("REST response doesn't match with expected. pattern");
    }

    checkEntityEx(request, name);
  }

  protected void checkEntityEx(RequestEntity<?> request, String name)
      throws URISyntaxException {
    // Expected ID for new record
    final int id = 1;

    // Try create same entity
    String response = checkRestResponse(request);
    Pattern p = Pattern.compile("\\{\"result\":false,\"error\":" +
        "\\{\"id\":\"ERROR_CREATE_" + name.toUpperCase() + "\"," +
        "\"info\":\"Exception type org.springframework.dao." +
        "DataIntegrityViolationException\",\"request_id\":\\d*\\}\\}");

    if (!p.matcher(response).matches()) {
      System.out.println(
          AuthSharedTestUtils.clearJsonPattern(p.pattern().toString()));
      System.out.println(response);
      fail("REST response doesn't match expected pattern.");
    }

    // Try Delete entity as user
    // Switch to user's access
    prepUserSecuritySession(10);
    checkDeleteForbidden(
        getBaseUrl() + getApiPrefix(name) + "/" + name + "/" + id,
        prepHttpHeaders());
  }

  protected String getApiPrefix(String api) {
    return "";
  }

  protected void checkDeleteForbidden(String url, HttpHeaders headers)
      throws URISyntaxException {
    RequestEntity<?> drequest =
        new RequestEntity<>(headers, HttpMethod.DELETE, new URI(url));
    ResponseEntity<String> rdenied =
        getRestTemplate().exchange(drequest, String.class);
    assertEquals(HttpStatus.FORBIDDEN, rdenied.getStatusCode());
  }

  protected void dropEntity(String name) throws URISyntaxException {
    // Delete entity as admin
    prepAdminSecuritySession();
    HttpHeaders headers = prepHttpHeaders();
    dropEntity(getBaseUrl() + getApiPrefix(name) + "/" + name + "/1", headers);
  }

  protected void dropEntity(String url, HttpHeaders headers)
      throws URISyntaxException {
    RequestEntity<?> drequest =
        new RequestEntity<>(headers, HttpMethod.DELETE, new URI(url));
    ResponseEntity<String> rok =
        getRestTemplate().exchange(drequest, String.class);
    assertEquals(HttpStatus.OK, rok.getStatusCode());
    testResponse(rok, HttpStatus.OK, "{\"result\":true}");
  }

  private String checkRestResponse(RequestEntity<?> request) {
    ResponseEntity<String> response =
        getRestTemplate().exchange(request, String.class);

    // Check response from success connection
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody(), "REST response is NULL");

    return response.getBody();
  }

  protected void checkAuthUrlAccessOk(String url, String result) {
    prepUserSecuritySession();
    HttpHeaders headers = prepHttpHeaders();
    checkGetRequestOk(headers, result, url);
  }
}
