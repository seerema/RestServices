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
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.seerema.shared.common.TestConstants;

/**
 * Shared Integration test for Entity Controller
 *
 */

public abstract class AbstractSharedEntityControllerTest<T>
    extends SharedRestTestUtils {

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

    if (isDupError()) {
      // Try create same entity
      ResponseEntity<String> resp = _rest.exchange(request, String.class);
      assertEquals(HttpStatus.OK, resp.getStatusCode());
      assertNotNull(resp.getBody());

      Pattern p = Pattern.compile("\\{\"result\":false,\"error\":\\{" +
          "\"id\":\"" + "ERROR_CREATE_" + getEntityUrl().toUpperCase() + "\"," +
          "\"info\":\"Exception type" +
          " org.springframework.dao.DataIntegrityViolationException\"," +
          "\"request_id\":\\d*\\}\\}");

      if (!p.matcher(resp.getBody()).matches()) {
        System.out.println(
            p.toString().replaceAll("\\\\\\{", "{").replaceAll("\\\\\\[", "[")
                .replaceAll("\\\\\\]", "]").replaceAll("\\\\\\}", "}"));
        System.out.println(resp.getBody());
        fail("Duplicate Entity create error doesn't match.");
      }
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

  protected boolean isDupError() {
    // By default duplicated records are not allowed
    return true;
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

  protected String getBaseTestUrl() {
    return "/" + getModuleName() + "/";
  }
}
