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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;

import com.seerema.catalog.rest.shared.CatalogRestTestConstants;
import com.seerema.rest.shared.base.common.RestBaseConstants;
import com.seerema.rest.entity.shared.test.AbstractEntityControllerTest;
import com.seerema.shared.dto.EntityDto;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * integration test for Base BusinessInfo Controller
 *
 */

public class BusinessInfoControllerTest
    extends AbstractEntityControllerTest<EntityDto> {

  @Override
  protected void checkAfterCreate(EntityDto company) throws URISyntaxException {
    // Check total data
    checkDataGooResponse(CatalogRestTestConstants.BASE_TEST_URL + "entities",
        2);

    // Read entity as object
    DataGoodResponse response = getRestTemplate().getForObject(
        CatalogRestTestConstants.BASE_TEST_URL + "entity/2",
        DataGoodResponse.class);

    @SuppressWarnings("unchecked")
    LinkedHashMap<String, Object> map =
        (LinkedHashMap<String, Object>) response.getData().get(0);

    @SuppressWarnings("unchecked")
    List<LinkedHashMap<String, Object>> fields =
        (List<LinkedHashMap<String, Object>>) map.get("entity_fields");

    assertEquals(company.getEntityFields().size(), fields.size(),
        "Number of business_info fields doesn't match.");
    // Update business_info field id
    for (int i = 0; i < company.getEntityFields().size(); i++) {
      Integer id =
          (Integer) ((LinkedHashMap<String, Object>) fields.get(i)).get("id");

      company.getEntityFields().get(i).setId(id);
    }
  }

  @Override
  protected EntityDto getEntity() {
    return new EntityDto();
  }

  @Override
  protected String getSecurityPrefix() {
    return RestBaseConstants.SECURITY_PREFIX_URL;
  }
}