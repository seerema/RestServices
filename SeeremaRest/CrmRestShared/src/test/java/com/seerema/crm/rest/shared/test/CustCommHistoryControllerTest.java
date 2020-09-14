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

package com.seerema.crm.rest.shared.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.seerema.crm.srv.dto.CustCommHistoryDto;
import com.seerema.crm.srv.shared.CrmConstants;
import com.seerema.rest.shared.base.rest.AbstractSharedEntityControllerTest;
import com.seerema.shared.dto.CommMediaDto;
import com.seerema.shared.dto.EntityExDto;
import com.seerema.shared.dto.UserDto;

/**
 * Integration test for BCustCommHistoryController
 *
 */

public class CustCommHistoryControllerTest
    extends AbstractSharedEntityControllerTest<CustCommHistoryDto> {

  private static LocalDateTime dts = LocalDateTime.now();

  private static final String INIT_BODY = "Demo Body";

  private static final String UPD_BODY = "Demo Body Ex";

  @Override
  protected CustCommHistoryDto getEntity() {
    return new CustCommHistoryDto();
  }

  @Override
  protected void initEntity(CustCommHistoryDto entity) {
    CommMediaDto cm = new CommMediaDto();
    cm.setId(1);

    UserDto user = new UserDto();
    user.setId(1);

    entity.setBody(INIT_BODY);
    entity.setCommMedia(cm);
    entity.setCreated(
        LocalDateTime.ofInstant(new Date(0).toInstant(), ZoneOffset.UTC));
    entity.setEntity(getTestEntity());
  }

  @Override
  protected void setEntityPartial(CustCommHistoryDto entity) {
    entity.setBody(INIT_BODY);
  }

  @Override
  protected void setEntityInvalid(CustCommHistoryDto entity) {
    entity.setBody("");
  }

  @Override
  protected String getEntityUrl() {
    return "cust_comm_history";
  }

  @Override
  protected String getEntitiesUrl() {
    return "cust_comm_histories/1";
  }

  @Override
  protected void updateEntity(CustCommHistoryDto entity) {
    entity.setBody(UPD_BODY);
    entity.setCreated(dts);
    entity.setEntity(getTestEntity());
    entity.setId(getEntityIdx());
  }

  @Override
  protected int getEntityIdx() {
    return 2;
  }

  @Override
  protected String getUpdateValue() {
    return UPD_BODY;
  }

  @Override
  protected String getUpdateIdx() {
    return "body";
  }

  @Override
  protected void checkAfterCreate(CustCommHistoryDto region)
      throws URISyntaxException {
    LinkedHashMap<?, ?> entity =
        checkDataResponse("/crm/entities", LinkedHashMap.class);

    List<?> list = (List<?>) entity.get("cust_comm_histories");
    assertNotNull(list, "cust_comm_histories list is NULL");
    assertEquals(2, list.size(), "Size of cust_comm_histories doesn't match.");

    // Check if sorting correct. Test record has date from 1970 and should be last
    LinkedHashMap<?, ?> item = (LinkedHashMap<?, ?>) list.get(1);
    assertEquals(INIT_BODY, item.get("body").toString(),
        "Body text doesn't match.");
    assertEquals("1970-01-01T00:00:00", item.get("created").toString(),
        "Created date doesn't match.");

  }

  private EntityExDto getTestEntity() {
    EntityExDto ex = new EntityExDto();
    ex.setId(1);

    return ex;
  }

  @Override
  protected String getModuleName() {
    return CrmConstants.MODULE_NAME;
  }

  @Override
  protected boolean isDupError() {
    // Duplicated records are allowed for CustCommHistory
    return false;
  }
}