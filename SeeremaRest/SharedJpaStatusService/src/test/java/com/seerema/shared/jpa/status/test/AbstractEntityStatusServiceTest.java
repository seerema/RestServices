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

package com.seerema.shared.jpa.status.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerema.base.WsSrvException;
import com.seerema.shared.Constants;
import com.seerema.shared.dto.EntityDto;
import com.seerema.shared.dto.EntityExDto;
import com.seerema.shared.dto.EntityFieldDto;
import com.seerema.shared.dto.EntityStatusHistoryDto;
import com.seerema.shared.dto.EntityUserHistoryDto;
import com.seerema.shared.dto.FieldCategoryDto;
import com.seerema.shared.dto.FieldDto;
import com.seerema.shared.dto.StatusDto;
import com.seerema.shared.dto.UserDto;
import com.seerema.shared.jpa.base.model.DbEntity;
import com.seerema.shared.jpa.base.model.Field;
import com.seerema.shared.jpa.base.model.FieldCategory;
import com.seerema.shared.jpa.base.service.BaseEntityService;
import com.seerema.shared.jpa.base.service.EntityService;
import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.shared.jpa.status.service.EntityStatusService;
import com.seerema.shared.jpa.status.service.StatusService;
import com.seerema.shared.jpa.status.utils.SharedJpaTestUtils;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Abstract EntityStatus Service Tests
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:/test.properties")
public abstract class AbstractEntityStatusServiceTest {

  // Test user name & id
  private static final int TEST_USER_ID = 2;
  private static final String TEST_USER_NAME = "user";

  @Autowired
  private EntityStatusService<EntityEx, EntityExDto> entityExSrv;

  @Autowired
  private BaseEntityService<FieldCategory, FieldCategoryDto> fieldCategorySrv;

  @Autowired
  private BaseEntityService<Field, FieldDto> fieldSrv;

  @Autowired
  private StatusService statusSrv;

  @Autowired
  private EntityService<DbEntity, EntityDto> entitySrv;

  protected abstract String getEntityFieldValue();

  protected abstract String getEntityName();

  protected abstract String getFieldName();

  protected abstract String getFieldCategoryName();

  protected abstract int getStatusNum();

  @Test
  void testStatusServiceRead() throws Exception {
    // Read FieldCategory
    FieldCategoryDto tfc = SharedJpaTestUtils.checkNonEmptyGoodResponse(
        fieldCategorySrv.readEntity(1), 1, FieldCategoryDto.class);
    assertEquals(getFieldCategoryName(), tfc.getName(),
        "Field Category name not found.");
    // Try Save it back
    try {
      tfc.setId(null);
      fieldCategorySrv.createEntity(tfc);
      fail("WsSrvException expected");
    } catch (WsSrvException e) {
      assertEquals(
          com.seerema.shared.jpa.base.shared.ErrorCodes.ERROR_CREATE_FIELD_CAT
              .name() +
              " - Exception type org.springframework.dao.DataIntegrityViolationException" +
              " [could not execute statement; SQL [n/a]; constraint [UK13PPBLXLC5RAX5WC61PN5DV0C];" +
              " nested exception is org.hibernate.exception.ConstraintViolationException:" +
              " could not execute statement]",
          e.getMessage());
    }

    // Read Field
    FieldDto field = SharedJpaTestUtils
        .checkNonEmptyGoodResponse(fieldSrv.readEntity(1), 1, FieldDto.class);
    assertEquals(getFieldName(), field.getName(),
        "Field Category name not found.");

    // Try Save it back
    try {
      field.setId(null);
      fieldSrv.createEntity(field);
      fail("WsSrvException expected");
    } catch (WsSrvException e) {
      assertEquals(
          com.seerema.shared.jpa.base.shared.ErrorCodes.ERROR_CREATE_FIELD
              .name() +
              " - Exception type org.springframework.dao.DataIntegrityViolationException" +
              " [could not execute statement; SQL [n/a]; constraint [UKKA1MV1RBJ19YN9WE33OQ1BJM6];" +
              " nested exception is org.hibernate.exception.ConstraintViolationException:" +
              " could not execute statement]",
          e.getMessage());
    }

    // Read Entity
    EntityExDto entity = SharedJpaTestUtils.checkNonEmptyGoodResponse(
        entityExSrv.readEntity(1), 1, EntityExDto.class);
    assertEquals(getEntityName(), entity.getName(), "Entity name not found.");
    assertNotNull(entity.getEntityFields(), "Entity Fields are empty.");
    assertEquals(1, entity.getEntityFields().size());

    assertEquals(getEntityFieldValue(),
        entity.getEntityFields().get(0).getValue(),
        "Entity field value not found.");

    // Try Save it back
    try {
      entity.setId(null);
      entityExSrv.createEntity(entity);
      fail("WsSrvException expected");
    } catch (WsSrvException e) {
      assertEquals("ERROR_CREATE_ENTITY" +
          " - Exception type org.springframework.dao.DataIntegrityViolationException" +
          " [could not execute statement; SQL [n/a]; constraint [UK_MHN0VA9IIHX5QN43YW3Y75HX1];" +
          " nested exception is org.hibernate.exception.ConstraintViolationException:" +
          " could not execute statement]", e.getMessage());
    }

    checkEntity();
  }

  private void checkEntity() throws WsSrvException, IOException {
    // Create entity dto as Anonymous user
    EntityExDto dto = new EntityExDto();
    dto.setName("Temp");

    List<EntityFieldDto> fields = new ArrayList<>();
    fields.add(getTestEntityFieldDto(1, "Temp Description"));

    FieldCategoryDto fcat = new FieldCategoryDto();
    fcat.setId(1);

    dto.setEntityFields(fields);
    dto.setStatus(new StatusDto(1));
    dto.setFieldCat(fcat);

    /******** DTO 1 ********/
    EntityExDto dto1 =
        (EntityExDto) entityExSrv.createEntity(dto).getData().get(0);

    // Test values
    assertEquals(Constants.ANONYMOUS_USER, dto1.getUser().getName(),
        "Entity UserName doesn't match.");
    assertEquals(dto.getName(), dto1.getName(), "Entity name doesn't match.");
    List<?> nfields = dto1.getEntityFields();
    assertNotNull(nfields, "Entity Fields are null");
    assertEquals(1, nfields.size(), "Size of Entity Fields doesn't match.");

    for (int i = 0; i < nfields.size(); i++) {
      EntityFieldDto nfield = (EntityFieldDto) nfields.get(i);
      assertEquals(fields.get(i).getValue(), nfield.getValue());
      assertEquals(fields.get(i).getField().getId(), nfield.getField().getId());

      // Entity should not be set
      assertNull(nfield.getEntity());
    }

    // Check initial status
    checkEntityStatus(dto1, 1, Constants.ANONYMOUS_USER);

    // Check initial owner
    checkEntityOwner(dto1, 1, Constants.ANONYMOUS_USER);

    // Check list of all entities
    checkAllEntitiesList(2, "Entities");

    runCheck(1);

    /******** DTO 2 ********/

    // Update entity info
    final String name = "Tempo";
    dto1.setName(name);

    // Update old entity field
    dto1.getEntityFields().get(0).setValue("www");

    // add new entity field
    final String nvalue = "zzz";
    dto1.getEntityFields().add(getTestEntityFieldDto(2, nvalue));

    // Update entity with different user
    try {
      updateEntityUser(dto1, false);
      fail("USER_ACCESS_DENIED Exception expected");
    } catch (WsSrvException e) {
      assertEquals(
          com.seerema.shared.jpa.base.shared.ErrorCodes.USER_ACCESS_DENIED
              .name(),
          e.getErrorCode());
    }

    // Update entity with different user but manager role
    EntityExDto dto2 = updateEntityUser(dto1);
    assertEquals(name, dto2.getName());

    // Check for new field
    List<EntityFieldDto> ufields = dto2.getEntityFields();
    assertNotNull(ufields, "Entity Fields are null");
    assertEquals(2, dto2.getEntityFields().size(),
        "Size of Entity Fields doesn't match.");

    // Check new field
    assertEquals("www", ufields.get(1).getValue());

    // Check entity status history is the same
    checkEntityStatus(dto2, 1, Constants.ANONYMOUS_USER);

    runCheck(2);

    /******** DTO 3 ********/

    // remove entity field
    dto2.getEntityFields().remove(0);
    // Update entity with user owner
    EntityExDto dto3 = updateEntity(dto2);

    // Check for new field set
    ufields = dto3.getEntityFields();
    assertNotNull(ufields, "Entity Fields are null");
    assertEquals(1, dto3.getEntityFields().size(),
        "Size of Entity Fields doesn't match.");

    runCheck(3);

    /******** DTO 4 ********/

    // Change entity status
    DataGoodResponse statuses = statusSrv.readStatuses();
    assertTrue(statuses.getResult(), "Entity Statuses list request failed.");
    @SuppressWarnings("unchecked")
    List<StatusDto> slist = (List<StatusDto>) statuses.getData();
    assertEquals(getStatusNum(), slist.size(),
        "Number of entity statuses doesn't match.");

    dto3.getStatus().setId(slist.get(slist.size() - 1).getId());

    // Update entity with new status
    EntityExDto dto4 = updateEntityUser(dto3);

    // Check entity ownership didn't change but status registered for different user
    assertEquals(dto4.getUser().getName(), Constants.ANONYMOUS_USER);
    checkEntityStatus(dto4, 2, TEST_USER_NAME);

    runCheck(4);

    /******** DTO 5 ********/
    // Change existing entity field
    EntityFieldDto qfd = dto4.getEntityFields().get(0);
    String value = "==" + qfd.getValue();
    qfd.setValue(value);

    // Set new entity ownership
    UserDto udto = new UserDto();
    udto.setId(TEST_USER_ID);
    udto.setName(TEST_USER_NAME);
    dto4.setUser(udto);
    EntityExDto dto5 = updateEntityUser(dto4);

    // Check entity ownership changed
    assertEquals(TEST_USER_NAME, dto5.getUser().getName(),
        "New entity ownership doesn't match");

    // Check ownership history
    checkEntityOwner(dto5, 2, TEST_USER_NAME);

    assertEquals(value, dto5.getEntityFields().get(0).getValue(),
        "DTO4 EntityField value #0 doesn't match.");
    checkEntityStatus(dto5, 2, TEST_USER_NAME);

    runCheck(5);

    /******** FINAL ********/
    // Check the number of entities and entity_ex same
    checkEntityExNum();

    // Delete entity
    assertEquals(dto5.getId(), dto5.getEntity().getId(),
        "EntityEx and Entity id's doesn't match");
    entityExSrv.deleteEntity(dto5.getId());
    checkEntityExNum();
  }

  private void checkEntityExNum() throws WsSrvException {
    DataGoodResponse exEntResp = entityExSrv.readEntities();
    assertTrue(exEntResp.getResult());
    assertNotNull(exEntResp.getData());

    DataGoodResponse entResp = entitySrv.readEntities();
    assertTrue(entResp.getResult());
    assertNotNull(entResp.getData());

    assertEquals(exEntResp.getData().size(), entResp.getData().size(),
        "Number of entity_ex doesn't match the number of entity");
  }

  private EntityExDto updateEntity(EntityExDto entity) throws WsSrvException {
    return (EntityExDto) entityExSrv.updateEntity(entity).getData().get(0);
  }

  private EntityExDto updateEntityUser(EntityExDto entity)
      throws WsSrvException {
    return updateEntityUser(entity, true);
  }

  private EntityExDto updateEntityUser(EntityExDto entity,
      boolean allowOverride) throws WsSrvException {
    return (EntityExDto) entityExSrv
        .updateEntity(entity, TEST_USER_NAME, allowOverride).getData().get(0);
  }

  private EntityFieldDto getTestEntityFieldDto(int id, String value) {
    EntityFieldDto cfield = new EntityFieldDto();
    FieldDto field = new FieldDto();
    field.setId(id);
    cfield.setField(field);
    cfield.setValue(value);

    return cfield;
  }

  private void checkEntityStatus(EntityExDto entity, int size,
      String username) {
    List<?> list = entity.getStatusHistories();
    assertNotNull(list, "Entity Status History is empty");

    assertEquals(size, list.size(),
        "Entity Status History size doesn't match expected");

    checkEntityLastStatus(entity, username);
  }

  private void checkEntityLastStatus(EntityExDto entity, String username) {
    StatusDto status = entity.getStatus();

    List<EntityStatusHistoryDto> list = entity.getStatusHistories();
    assertNotNull(list, "Entity Status History is empty");

    EntityStatusHistoryDto sh = list.get(list.size() - 1);
    assertNotNull(sh.getCreated(), "Created time for status history is NULL");
    assertEquals(status.getName(), sh.getStatus().getName(),
        "Entity Status History last status doesn't match expected");

    if (username != null)
      assertEquals(username, sh.getUser().getName(),
          "Entity Status History username last status doesn't match expected");

  }

  private void checkEntityOwner(EntityExDto entity, int size, String username) {
    List<?> list = entity.getOwnerHistories();
    assertNotNull(list, "Entity Owner History is empty");

    assertEquals(size, list.size(),
        "Entity Owner History size doesn't match expected");

    checkEntityLastOwner(entity, username);
  }

  private void checkEntityLastOwner(EntityExDto entity, String username) {
    UserDto owner = entity.getUser();

    List<EntityUserHistoryDto> list = entity.getOwnerHistories();
    assertNotNull(list, "Entity User History is empty");

    EntityUserHistoryDto oh = list.get(list.size() - 1);
    assertNotNull(oh.getCreated(), "Created time for owner history is NULL");
    assertEquals(owner.getName(), oh.getOwner().getName(),
        "Entity User History last status doesn't match expected");

    if (username != null)
      assertEquals(username, oh.getUser().getName(),
          "Entity User History username last status doesn't match expected");

  }

  protected static String getTestUserName() {
    return TEST_USER_NAME;
  }

  protected void checkAllEntitiesList(int size, String msg)
      throws WsSrvException {
    checkEntitiesList(entityExSrv.readEntities(), size, msg);
  }

  protected void checkEntitiesList(DataGoodResponse entities, int size,
      String msg) throws WsSrvException {
    assertTrue(entities.getResult(), msg + " result failed.");
    assertNotNull(entities.getData(), msg + " list is NULL");
    @SuppressWarnings("unchecked")
    List<EntityExDto> data = (List<EntityExDto>) entities.getData();
    assertEquals(size, data.size(), "List of all " + msg + " doesn't match.");

    for (EntityExDto entity : data) {
      checkEntityLastStatus(entity, null);
    }
  }

  protected void runCheck(int i) throws WsSrvException {

  }
}
