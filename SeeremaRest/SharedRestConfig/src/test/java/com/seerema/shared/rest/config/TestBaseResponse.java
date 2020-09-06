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

package com.seerema.shared.rest.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.seerema.base.WsSrvException;
import com.seerema.shared.rest.response.BadResponse;

/**
 * Test Base Response
 *
 */
public class TestBaseResponse {

  private static final String ERR_CODE = "SMTHING_GOES_WRONG";

  private static final String ERR_INFO = "Something goes wrong";

  private static final String ERR_DETAILS = "Hidden details";

  @Test
  void testSimpleBadResponse() {
    BadResponse resp =
        new BadResponse(new WsSrvException(ERR_CODE, ERR_INFO), false);

    checkSimpleBadResponse(resp);
    assertNull(resp.getError().getDetails(), "Err Info is not null");
  }

  @Test
  void testComplexBadResponse() {
    BadResponse resp = new BadResponse(
        new WsSrvException(ERR_CODE, ERR_INFO, ERR_DETAILS), true);

    checkSimpleBadResponse(resp);
    assertNotNull(resp.getError().getDetails(), "Missing error details.");
    assertEquals(1, resp.getError().getDetails().length,
        "List of details doesn't match.");
    assertEquals(ERR_DETAILS, resp.getError().getDetails()[0],
        "Error Details doesn't match.");
  }

  @Test
  void testArrayBadResponse() {
    BadResponse resp = new BadResponse(new WsSrvException(ERR_CODE, ERR_INFO,
        new String[] { ERR_DETAILS, ERR_DETAILS }), true);

    checkSimpleBadResponse(resp);

    assertNotNull(resp.getError().getDetails(), "Missing error details.");
    assertEquals(2, resp.getError().getDetails().length,
        "List of details doesn't match.");

    for (String err : resp.getError().getDetails())
      assertEquals(ERR_DETAILS, err, "Error Details doesn't match.");
  }

  private void checkSimpleBadResponse(BadResponse resp) {
    assertEquals(ERR_CODE, resp.getError().getId(),
        "Error Code doesn't match.");
    assertEquals(ERR_INFO, resp.getError().getInfo(),
        "Error Msg doesn't match.");
  }
}
