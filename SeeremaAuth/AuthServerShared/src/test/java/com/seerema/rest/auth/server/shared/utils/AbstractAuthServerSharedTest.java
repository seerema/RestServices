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
package com.seerema.rest.auth.server.shared.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.net.HttpCookie;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.seerema.rest.auth.common.Constants;
import com.seerema.rest.auth.server.shared.common.AuthServerTestConstants;

/**
 * Integration tests
 * 
 */

public abstract class AbstractAuthServerSharedTest {

  // Authentication URL
  private final String AUTH_URL = "/login";

  @Autowired
  private TestRestTemplate _rest;

  protected abstract String[] getCredentials();

  protected abstract String getTestUser();

  protected abstract String getTestUserRole();

  protected abstract List<String> getTestUsers();

  /**
   * Test Non-Authenticated access
   */
  @Test
  public void testNonAuth() {
    // Test open accees for Spring Actuator URLs
    for (String url : new String[] { "/actuator//info", "/actuator//health" })
      testOk(url);

    for (String url : new String[] { "/login", "/zzz", "/user_info", "/users" })
      testUnauthorized(url);

    ResponseEntity<String> resp;
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    // Send invalid username
    MultiValueMap<String, String> map =
        new LinkedMultiValueMap<String, String>();
    map.add(Constants.USERNAME_PARAM, "zzz");
    map.add(Constants.PASSWORD_PARAM, AuthServerTestConstants.TEST_USER_PWD);

    resp = _rest.postForEntity(AUTH_URL,
        new HttpEntity<MultiValueMap<String, String>>(map, headers),
        String.class);
    assertEquals(HttpStatus.UNAUTHORIZED, resp.getStatusCode());
    assertNull(resp.getBody());

    // Send correct username but invalid password
    map.set(Constants.USERNAME_PARAM, AuthServerTestConstants.TEST_USER_PWD);
    map.set(Constants.PASSWORD_PARAM, "zzz");

    resp = _rest.postForEntity(AUTH_URL,
        new HttpEntity<MultiValueMap<String, String>>(map, headers),
        String.class);
    assertEquals(HttpStatus.UNAUTHORIZED, resp.getStatusCode());
    assertNull(resp.getBody());

    // Send empty /login
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    resp = _rest.postForEntity(AUTH_URL, headers, String.class);
    assertEquals(HttpStatus.UNAUTHORIZED, resp.getStatusCode());
    assertNull(resp.getBody());
  }

  /**
   * Test authenticated access
   */
  @Test
  public void testAuth() {
    HttpHeaders headers;
    ResponseEntity<String> resp;

    headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    String[] credentials = getCredentials();
    MultiValueMap<String, String> map =
        new LinkedMultiValueMap<String, String>();
    map.add(Constants.USERNAME_PARAM, credentials[0]);
    map.add(Constants.PASSWORD_PARAM, credentials[1]);

    HttpEntity<MultiValueMap<String, String>> request =
        new HttpEntity<MultiValueMap<String, String>>(map, headers);
    resp = _rest.postForEntity(AUTH_URL, request, String.class);
    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertNotNull(resp.getBody());
    assertEquals("{\"roles\":[\"ROLE_USER\"]}", resp.getBody(),
        "List of users doesn't match.");

    // Extract session id and inject into the headers for new request
    List<String> cookies = resp.getHeaders().get(HttpHeaders.SET_COOKIE);
    assertNotNull(cookies);
    assertEquals(1, cookies.size());

    List<HttpCookie> hcookies = HttpCookie.parse(cookies.get(0));
    assertNotNull(hcookies);
    assertEquals(1, hcookies.size());

    HttpCookie cookie = hcookies.get(0);
    assertEquals(Constants.COOKIE_SESSION_NAME, cookie.getName());
    headers = new HttpHeaders();
    headers.add(HttpHeaders.COOKIE, cookie.toString());

    // Test 404 for non-existing url
    resp = _rest.exchange("/zzz", HttpMethod.GET, new HttpEntity<>(headers),
        String.class);
    assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());

    // Test 200
    testRequestOk("/user_info", headers,
        "userName:" + getTestUser() + "|authenticationName:" + getTestUser() +
            "|roles:" + getTestUserRole() + "|sessionId:" + cookie.getValue());

    testRequestOk("/users", headers, "[\"" +
        getTestUsers().stream().collect(Collectors.joining("\",\"")) + "\"]");

    // Logout
    resp = _rest.exchange("/logout", HttpMethod.POST, new HttpEntity<>(headers),
        String.class);
    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertNull(resp.getBody());
  }

  private void testRequestOk(String url, HttpHeaders headers, String response) {
    ResponseEntity<String> resp = _rest.exchange(url, HttpMethod.GET,
        new HttpEntity<>(headers), String.class);
    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertEquals(response, resp.getBody());
  }

  private void testUnauthorized(String url) {
    ResponseEntity<String> resp = _rest.getForEntity(url, String.class);
    assertEquals(HttpStatus.UNAUTHORIZED, resp.getStatusCode());
    assertNull(resp.getBody());
  }

  private void testOk(String url) {
    ResponseEntity<String> resp = _rest.getForEntity(url, String.class);
    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertNotNull(resp.getBody());
  }

  public TestRestTemplate getRestTemplate() {
    return _rest;
  }
}
