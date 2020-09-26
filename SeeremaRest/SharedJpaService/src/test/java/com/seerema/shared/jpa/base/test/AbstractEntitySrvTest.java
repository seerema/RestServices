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

package com.seerema.shared.jpa.base.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerema.base.WsSrvException;
import com.seerema.shared.dto.AbstractEntityDto;
import com.seerema.shared.jpa.base.model.BaseEntity;
import com.seerema.shared.jpa.base.service.BaseEntityService;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Test Abstract Entity Service
 * 
 * TODO - moved to SharedJpaService test
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:/test.properties")
public abstract class AbstractEntitySrvTest<T1 extends BaseEntity, T2 extends AbstractEntityDto> {

  protected abstract void initEntity(T2 entity);

  protected abstract void checkNewEntity(T2 entity) throws WsSrvException;

  protected abstract void updateEntityl(T2 entity);

  protected abstract void checkUpdatedEntity(T2 entity) throws WsSrvException;

  protected abstract BaseEntityService<T1, T2> getEntityService();

  protected abstract Class<T2> getEntityDtoClass();

  protected abstract int getInitSize();

  @Test
  void testCustCommHistory() throws Exception {
    // Check initial user list
    checkResponse(getEntityService().readEntities(), getInitSize());
    T2 dto = getEntityDtoClass().newInstance();

    // Try save empty entity
    try {
      getEntityService().createEntity(dto);
      fail("Exception expected");
    } catch (WsSrvException e) {
      assertEquals(getCreateError(), e.getMessage());
    }

    initEntity(dto);

    // Save correct entity;
    T2 dto1 = getDataRec(getEntityService().createEntity(dto), 1);
    checkNewEntity(dto1);

    if (isDupError()) {
      // Try create same entity
      int id = dto1.getId();
      dto1.setId(null);
      try {
        getEntityService().createEntity(dto);
        fail("Exception expected");
      } catch (WsSrvException e) {
        assertEquals(getDupError(), e.getMessage());
      }

      dto1.setId(id);
    }

    updateEntityl(dto1);

    // Update as valid user
    T2 dto2 = getDataRec(getEntityService().updateEntity(dto1), 1);
    checkUpdatedEntity(dto2);

    // Delete user
    getEntityService().deleteEntity(dto1.getId());
    checkResponse(getEntityService().readEntities(), getInitSize());
  }

  protected String getDupError() {
    return "";
  }

  protected abstract String getCreateError();

  /**
   * Get flag either duplicated records are allowed
   * 
   * @return TRUE if duplicated records are not allowed and
   *    FALSE if are allowed
   */
  protected boolean isDupError() {
    // By default duplicated records are not allowed
    return true;
  }

  @SuppressWarnings("unchecked")
  private T2 getDataRec(DataGoodResponse resp, int size) {
    List<?> data = checkResponse(resp, size);

    Object res = data.get(0);
    assertEquals(res.getClass(), getEntityDtoClass(),
        "Type of response data doesn't match.");

    return (T2) res;
  }

  protected List<?> checkResponse(DataGoodResponse resp, int size) {
    assertTrue(resp.getResult(), "Failed response");

    List<?> data = resp.getData();
    assertNotNull(data, "Response Data is NULL");
    assertEquals(size, data.size(), "Data size doesn't match");

    return data;
  }
}
