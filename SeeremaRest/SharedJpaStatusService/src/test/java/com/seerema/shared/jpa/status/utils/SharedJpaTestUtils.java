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

package com.seerema.shared.jpa.status.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.seerema.shared.dto.AbstractEntityDto;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Shared JPA Test Utils
 */
public class SharedJpaTestUtils {

  @SuppressWarnings("unchecked")
  public static <T> T checkNonEmptyGoodResponse(DataGoodResponse response,
      int size, Class<T> clazz) {
    assertTrue(response.getResult(), "Response status is not true");

    List<?> data = response.getData();
    assertNotNull(data, "Data array is null");
    assertEquals(size, data.size(), "Data array doesn't match expected.");

    if (size > 0) {
      Object item = data.get(0);
      assertEquals(clazz, item.getClass(),
          "Type of list element doesn't match");
      return (T) item;
    } else {
      return null;
    }
  }

  public static Integer checkResponse(DataGoodResponse response) {
    assertTrue(response.getResult());
    AbstractEntityDto dto = (AbstractEntityDto) response.getData().get(0);
    return dto.getId();
  }
}
