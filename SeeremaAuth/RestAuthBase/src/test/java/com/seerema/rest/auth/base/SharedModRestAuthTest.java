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

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

import com.seerema.shared.dto.EntityDto;
import com.seerema.shared.dto.EntityFieldDto;
import com.seerema.shared.dto.FieldCategoryDto;
import com.seerema.shared.dto.FieldDto;

/**
 * Test Configuration class for Base Rest API
 * 
 */

/**
 * Integration test for REST Auth service
 */
public abstract class SharedModRestAuthTest extends SharedRestTestUnits {

  private final static String[] API_LIST =
      new String[] { "/entities", "/field_cat", "/field" };

  @Test
  public void testEntity() throws Exception {
    // Protected entities required admin access
    prepAdminSecuritySession(10);
    HttpHeaders headers = prepHttpHeaders();

    // Create Field Category
    FieldCategoryDto fcat = new FieldCategoryDto();
    fcat.setName("LL_TEST");
    checkEntity(
        new RequestEntity<FieldCategoryDto>(fcat, headers, HttpMethod.PUT,
            new URI(getBaseUrl() + "/admin/field_cat")),
        "field_cat", getFieldCategoryJson());
    fcat.setId(1);

    // Create Field
    FieldDto field = new FieldDto();
    field.setName("Item");
    field.setFieldCat(fcat);
    checkEntity(new RequestEntity<FieldDto>(field, headers, HttpMethod.PUT,
        new URI(getBaseUrl() + "/admin/field")), "field", getFieldJson());
    field.setId(1);

    // Switch to user's access
    prepUserSecuritySession(10000);
    headers = prepHttpHeaders();

    // Create Entity with EntityField and status
    EntityFieldDto cfield = new EntityFieldDto();
    cfield.setValue("Test");
    cfield.setField(field);

    EntityDto entity = new EntityDto();
    entity.setName("Test");
    entity.setFieldCat(fcat);
    List<EntityFieldDto> list = new ArrayList<>();
    list.add(cfield);
    entity.setEntityFields(list);

    checkEntityRegex(new RequestEntity<EntityDto>(entity, headers,
        HttpMethod.PUT, new URI(getBaseUrl() + "/entity")), "entity",
        getEntityJson());
    entity.setId(1);

    // Read all entities
    String task = getAllEntities();
    checkGetRequestOk(headers, task, getBaseUrl() + "/entities");

    // Read all manager's entities
    prepMgrSecuritySession();
    headers = prepHttpHeaders();
    checkGetRequestOk(headers, task, getBaseUrl() + "/private/entities");

    // Drop task
    dropEntity("entity");
  }

  protected abstract String getAllEntities();

  protected abstract String getEntityJson();

  protected abstract String getFieldCategoryJson();

  protected abstract String getFieldJson();

  @Override
  protected String[] getAuthUrls() {
    return API_LIST;
  }

  @Override
  protected String getApiPrefix(String api) {
    return api.equals("field") || api.equals("field_cat") ? "/admin"
        : super.getApiPrefix(api);
  }
}
