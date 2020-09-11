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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;

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
public class SharedRestTestUtils {

  @Autowired
  private TestRestTemplate _rest;

  @LocalServerPort
  private void setPort(int port) {
    TestConstants.LOG.info("Starting on port " + port);
  }

  @SuppressWarnings({ "hiding", "unchecked" })
  protected <T> T checkDataResponse(String url, Class<T> clazz) {
    List<?> list = checkDataResponse(url, 1);
    return (T) list.get(0);
  }

  protected List<?> checkDataResponse(String url, int size) {
    DataGoodResponse data = getResponseBody(url);

    return simpleDataResponseCheck(data, size);
  }

  protected List<?> simpleDataResponseCheck(DataGoodResponse data, int size) {

    assertNotNull(data, "List is empty");
    assertTrue(data.getResult(), "Result is false");

    List<?> list = data.getData();
    assertNotNull(list, "Empty data.");
    assertEquals(size, list.size(),
        "Number of data items doesn't match expected.");

    return list;
  }

  protected void checkBadResponse(RequestEntity<?> request) {
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

  protected void checkDelete(String url) throws URISyntaxException {
    RequestEntity<?> request =
        new RequestEntity<Object>(HttpMethod.DELETE, new URI(url));

    checkGoodResponse(request);
  }

  protected void checkGoodResponse(RequestEntity<?> request) {
    ResponseEntity<BaseResponse> resp =
        _rest.exchange(request, BaseResponse.class);

    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertNotNull(resp.getBody());
    assertTrue(resp.getBody().getResult());
  }

  protected String getUpdateIdx() {
    return "name";
  }

  /**
   * Get Random String
   * 
   * @param size The size of random string
   * @return Random string of given size
   */
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

}
