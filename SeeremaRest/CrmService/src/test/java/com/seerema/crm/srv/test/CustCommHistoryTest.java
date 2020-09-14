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

package com.seerema.crm.srv.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.seerema.base.WsSrvException;
import com.seerema.crm.srv.dto.CustCommHistoryDto;
import com.seerema.crm.srv.dto.CustomerDto;
import com.seerema.crm.srv.jpa.model.CustCommHistory;
import com.seerema.shared.Constants;
import com.seerema.shared.dto.CommMediaDto;
import com.seerema.shared.dto.EntityExDto;
import com.seerema.shared.dto.UserDto;
import com.seerema.shared.jpa.base.service.BaseEntityUserService;
import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.shared.jpa.status.service.EntityStatusService;

/**
 * Test CustCommHistory Service
 */
public class CustCommHistoryTest
    extends AbstractEntityServiceTest<CustCommHistory, CustCommHistoryDto> {

  @Autowired
  private BaseEntityUserService<CustCommHistory, CustCommHistoryDto> _srv;

  @Autowired
  private EntityStatusService<EntityEx, EntityExDto> _ex;

  private static LocalDateTime dts = LocalDateTime.now();

  private static final String INIT_BODY = "Test Body";

  private static final String UPD_BODY = "Test Body Ex";

  @Override
  protected int getInitSize() {
    return 0;
  }

  @Override
  protected Class<CustCommHistoryDto> getEntityClass() {
    return CustCommHistoryDto.class;
  }

  @Override
  protected void updateEntityl(CustCommHistoryDto entity) {
    entity.setBody(UPD_BODY);
    entity.setCreated(dts);

    // EntityEx erased during mapping so set it again
    entity.setEntity(getTestEntity());
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
  protected void checkNewEntity(CustCommHistoryDto entity)
      throws WsSrvException {
    // Check directly && indirectly
    checkNewEntityEx(entity);
    checkNewEntityEx(getEntityCommHistory());
  }

  @Override
  protected void checkUpdatedEntity(CustCommHistoryDto entity)
      throws WsSrvException {
    // Check directly && indirectly
    checkUpdateEntityEx(entity);
    checkUpdateEntityEx(getEntityCommHistory());
  }

  private EntityExDto getTestEntity() {
    EntityExDto ex = new EntityExDto();
    ex.setId(1);

    return ex;
  }

  private void checkUpdateEntityEx(CustCommHistoryDto entity) {
    checkEntity(entity, UPD_BODY, dts.toString());
  }

  /**
   * Read ex entity and check communication histories
   * @return CustCommHistoryDto object
   * @throws WsSrvException 
   */
  private CustCommHistoryDto getEntityCommHistory() throws WsSrvException {
    List<?> list = checkResponse(_ex.readEntities(), 1);
    Object obj = list.get(0);
    assertEquals(CustomerDto.class, obj.getClass(),
        "Type of list item doesn't match.");
    CustomerDto dto = (CustomerDto) obj;
    List<CustCommHistoryDto> lst = dto.getCustCommHistories();
    assertNotNull(lst, "List of CustCommHistoryDto is NULL");
    assertEquals(1, lst.size(),
        "Size of list with CustCommHistoryDto doesn't match. ");

    return lst.get(0);
  }

  private void checkNewEntityEx(CustCommHistoryDto entity) {
    checkEntity(entity, INIT_BODY, "1970-01-01T00:00");
  }

  private void checkEntity(CustCommHistoryDto entity, String body, String dts) {
    assertEquals(body, entity.getBody(), "Body doesn't match.");
    assertEquals(1, entity.getCommMedia().getId(),
        "CommMedia Id doesn't match.");
    assertEquals(dts, entity.getCreated().toString(),
        "Created time doesn't match.");
    assertNull(entity.getEntity(), "Entity is not NULL");
    assertEquals(Constants.ANONYMOUS_USER, entity.getUser().getName(),
        "UserName doesn't match.");
  }

  @Override
  protected BaseEntityUserService<CustCommHistory, CustCommHistoryDto>
      getEntityService() {
    return _srv;
  }

  @Override
  protected boolean isDupError() {
    // Duplicated error are allowed
    return false;
  }
}
