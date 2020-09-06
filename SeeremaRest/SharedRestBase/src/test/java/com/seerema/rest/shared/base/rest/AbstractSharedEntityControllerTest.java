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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.seerema.shared.common.TestConstants;
import com.seerema.shared.rest.response.BaseResponse;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Shared Integration test for Entity Controller
 *
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:/test.properties")
public abstract class AbstractSharedEntityControllerTest<T> {

  @Autowired
  private TestRestTemplate _rest;

  @LocalServerPort
  private void setPort(int port) {
    TestConstants.LOG.info("Starting on port " + port);
  }

  protected abstract T getEntity();

  protected abstract void initEntity(T entity);

  protected abstract void setEntityPartial(T entity);

  protected abstract void setEntityInvalid(T entity);

  protected abstract String getEntityUrl();

  protected abstract String getEntitiesUrl();

  protected abstract void updateEntity(T entity);

  protected abstract int getEntityIdx();

  protected abstract String getUpdateValue();

  protected abstract String getModuleName();

  @Test
  void testReadAll() {
    int idx = getEntityIdx() - 1;
    checkDataResponse(getBaseTestUrl() + getEntitiesUrl(), idx);
    checkDataResponse(getBaseTestUrl() + getEntityUrl() + "/" + idx, 1);
  }

  @Test
  void testEntityCatalog() throws URISyntaxException {
    // Try update empty DTO
    T entity = getEntity();
    RequestEntity<T> request = new RequestEntity<T>(entity, HttpMethod.PUT,
        new URI(getBaseTestUrl() + getEntityUrl()));

    checkBadResponse(request);

    // Correct name
    setEntityPartial(entity);
    checkBadResponse(request);

    // Correct phone but invalid name
    setEntityInvalid(entity);
    checkBadResponse(request);

    // Trying create valid DTO
    initEntity(entity);
    LinkedHashMap<?, ?> map = checkDataGooResponse(request, getEntityIdx());
    checkAfterCreate(entity);

    // Try create same entity
    ResponseEntity<String> resp = _rest.exchange(request, String.class);
    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertNotNull(resp.getBody());

    Pattern p = Pattern.compile(
        "\\{\"result\":false,\"error\":\\{" + "\"id\":\"" + "ERROR_CREATE_" +
            getEntityUrl().toUpperCase() + "\"," + "\"info\":\"Exception type" +
            " org.springframework.dao.DataIntegrityViolationException\"," +
            "\"request_id\":\\d*\\}\\}");

    if (!p.matcher(resp.getBody()).matches()) {
      System.out.println(
          p.toString().replaceAll("\\\\\\{", "{").replaceAll("\\\\\\[", "[")
              .replaceAll("\\\\\\]", "]").replaceAll("\\\\\\}", "}"));
      System.out.println(resp.getBody());
      fail("Duplicate Entity create error doesn't match.");
    }

    // Update entity
    updateEntity(entity, map);

    checkGoodResponse(new RequestEntity<T>(entity, HttpMethod.POST,
        new URI(getBaseTestUrl() + getEntityUrl() + "/" + getEntityIdx())));
    checkAfterUpdate(entity);

    // Read new name
    @SuppressWarnings("unchecked")
    LinkedHashMap<String, String> record = checkDataResponse(
        getBaseTestUrl() + getEntityUrl() + "/" + getEntityIdx(),
        LinkedHashMap.class);
    assertEquals(getUpdateValue(), record.get(getUpdateIdx()),
        "Updated " + getUpdateIdx() + " doesn't match.");

    // Delete entry
    checkDelete(getBaseTestUrl() + getEntityUrl() + "/" + getEntityIdx());
  }

  protected void updateEntity(T entity, LinkedHashMap<?, ?> map) {
    updateEntity(entity);
  }

  protected void checkAfterCreate(T entity) throws URISyntaxException {
    // Do nothing here
  }

  protected void checkAfterUpdate(T entity) {
    // Do nothing here
  }

  @SuppressWarnings({ "hiding", "unchecked" })
  protected <T> T checkDataResponse(String url, Class<T> clazz) {
    List<?> list = checkDataResponse(url, 1);
    return (T) list.get(0);
  }

  private List<?> checkDataResponse(String url, int size) {
    DataGoodResponse data = getResponseBody(url);

    return simpleDataResponseCheck(data, size);
  }

  private List<?> simpleDataResponseCheck(DataGoodResponse data, int size) {

    assertNotNull(data, "List is empty");
    assertTrue(data.getResult(), "Result is false");

    List<?> list = data.getData();
    assertNotNull(list, "Empty data.");
    assertEquals(size, list.size(),
        "Number of data items doesn't match expected.");

    return list;
  }

  private void checkBadResponse(RequestEntity<?> request) {
    ResponseEntity<String> resp = _rest.exchange(request, String.class);

    // Expecting fail
    assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
  }

  protected LinkedHashMap<?, ?> checkDataGooResponse(RequestEntity<?> request,
      int expected) {
    List<?> list = simpleDataResponseCheck(getResponseBody(request), 1);
    LinkedHashMap<?, ?> record = (LinkedHashMap<?, ?>) list.get(0);

    assertEquals(new Integer(expected), record.get("id"));

    return record;
  }

  protected void checkDataGooResponse(String url, int expected) {
    simpleDataResponseCheck(getResponseBody(url), expected);
  }

  private DataGoodResponse getResponseBody(RequestEntity<?> request) {
    return getResponseBody(_rest.exchange(request, DataGoodResponse.class));
  }

  protected DataGoodResponse getResponseBody(String url) {
    return getResponseBody(_rest.getForEntity(url, DataGoodResponse.class));
  }

  private DataGoodResponse
      getResponseBody(ResponseEntity<DataGoodResponse> resp) {
    assertEquals(HttpStatus.OK, resp.getStatusCode(),
        "HTTP Status Code doesn't match.");
    assertNotNull(resp.getBody());

    return resp.getBody();
  }

  private void checkDelete(String url) throws URISyntaxException {
    RequestEntity<?> request =
        new RequestEntity<Object>(HttpMethod.DELETE, new URI(url));

    checkGoodResponse(request);
  }

  private void checkGoodResponse(RequestEntity<?> request) {
    ResponseEntity<BaseResponse> resp =
        _rest.exchange(request, BaseResponse.class);

    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertNotNull(resp.getBody());
    assertTrue(resp.getBody().getResult());
  }

  protected String getUpdateIdx() {
    return "name";
  }

  protected String getTestString(int size) {
    String res = "";
    for (int i = 0; i < size; i++) {
      int rnd = (int) (Math.random() * 52); // or use Random or whatever
      char base = (rnd < 26) ? 'A' : 'a';
      res += (char) (base + rnd % 26);
    }

    return res;
  }

  public RestTemplate getRestTemplate() {
    return _rest.getRestTemplate();
  }

  protected String getBaseTestUrl() {
    return "/" + getModuleName() + "/";
  }
}
