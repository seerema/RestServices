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

package com.seerema.rest.entity_ex.shared.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.LinkedHashMap;
import java.util.List;

import com.seerema.rest.shared.base.common.RestBaseConstants;
import com.seerema.rest.entity.shared.test.AbstractEntityControllerTest;
import com.seerema.shared.dto.EntityExDto;
import com.seerema.shared.dto.EntityFieldDto;
import com.seerema.shared.dto.StatusDto;

/**
 * Integration test for Base Entity Controller
 */

public class EntityExControllerTest
    extends AbstractEntityControllerTest<EntityExDto> {

  private static final int NEW_STATUS_ID = 2;

  @Override
  protected EntityExDto getEntity() {
    return new EntityExDto();
  }

  @Override
  protected void updateEntity(EntityExDto entity) {
    super.updateEntity(entity);

    StatusDto status = new StatusDto();
    status.setId(1);
    entity.setStatus(status);
  }

  @Override
  protected void updateEntity(EntityExDto entity, LinkedHashMap<?, ?> map) {
    super.updateEntity(entity, map);
    entity.setId(Integer.parseInt(map.get("id").toString()));

    List<EntityFieldDto> fields = entity.getEntityFields();
    for (int i = 0; i < fields.size(); i++)
      fields.get(0).setId(Integer.parseInt(
          ((LinkedHashMap<?, ?>) ((List<?>) map.get("entity_fields")).get(i))
              .get("id").toString()));

    // Change entity status
    entity.getStatus().setId(NEW_STATUS_ID);
  }

  @Override
  protected void checkAfterUpdate(EntityExDto entity) {
    LinkedHashMap<?, ?> ent = checkDataResponse(
        getBaseTestUrl() + getEntityUrl() + "/" + getEntityIdx(),
        LinkedHashMap.class);

    assertEquals(NEW_STATUS_ID,
        ((LinkedHashMap<?, ?>) ent.get("status")).get("id"));

    // Check list of status histories
    List<LinkedHashMap<?, ?>> list = getLinkedMap(ent, "status_histories", 2);

    for (int i = 0; i < 2; i++)
      assertEquals(i + 1,
          ((LinkedHashMap<?, ?>) ((LinkedHashMap<?, ?>) list.get(i))
              .get("status")).get("id"));

    // Check ownership history
    List<LinkedHashMap<?, ?>> olst = getLinkedMap(ent, "owner_histories", 1);
    assertEquals(1,
        ((LinkedHashMap<?, ?>) ((LinkedHashMap<?, ?>) olst.get(0)).get("owner"))
            .get("id"));
  }

  private List<LinkedHashMap<?, ?>> getLinkedMap(LinkedHashMap<?, ?> ent,
      String key, int size) {
    @SuppressWarnings("unchecked")
    List<LinkedHashMap<?, ?>> list = (List<LinkedHashMap<?, ?>>) ent.get(key);
    assertNotNull(list);
    assertEquals(size, list.size());

    return list;
  }

  @Override
  protected String getSecurityPrefix() {
    return RestBaseConstants.SECURITY_PREFIX_URL;
  }
}