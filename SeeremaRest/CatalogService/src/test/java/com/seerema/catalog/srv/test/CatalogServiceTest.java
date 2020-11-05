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

package com.seerema.catalog.srv.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.seerema.base.WsSrvException;
import com.seerema.catalog.srv.dto.AddressDto;
import com.seerema.catalog.srv.dto.CityDto;
import com.seerema.catalog.srv.dto.CountryDto;
import com.seerema.catalog.srv.dto.RegionDto;
import com.seerema.catalog.srv.shared.ErrorCodes;
import com.seerema.shared.dto.CommMediaDto;
import com.seerema.shared.dto.EntityDto;
import com.seerema.shared.dto.EntityFieldDto;
import com.seerema.shared.dto.FieldCategoryDto;
import com.seerema.shared.dto.FieldDto;
import com.seerema.shared.jpa.base.model.CommMedia;
import com.seerema.shared.jpa.base.model.Field;
import com.seerema.shared.jpa.base.model.FieldCategory;
import com.seerema.shared.jpa.base.service.BaseEntityService;
import com.seerema.shared.jpa.base.utils.SharedJpaTestUtils;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Catalog Service Tests
 */
public class CatalogServiceTest extends SharedCatalogServiceTestUnits {

  @Autowired
  private DataSource _ds;

  @Autowired
  private BaseEntityService<FieldCategory, FieldCategoryDto> fieldCatSrv;

  @Autowired
  private BaseEntityService<Field, FieldDto> fieldSrv;

  @Autowired
  private BaseEntityService<CommMedia, CommMediaDto> commMediaSrv;

  private boolean initialized;

  @Test
  void EmptyReadTest() throws WsSrvException {
    testEmptyDataResponse(getCountrySrv().readEntities());
    testEmptyDataResponse(getRegionSrv().readEntities());
    testEmptyDataResponse(getCitySrv().readEntities());
    testEmptyDataResponse(getAddressSrv().readEntities());
    testEmptyDataResponse(getEntitySrv().readEntities());
  }

  @Test
  void testServiceRead() throws Exception {
    loadDbScript();

    testCountrySrv();

    testRegionSrv();

    testCitySrv();

    AddressDto address = testAddress();

    testFieldCategory();

    testField();

    testCompany();

    // Test all chain
    assertEquals("Canada", address.getCity().getRegion().getCountry().getName(),
        "Canada  doesn't match.");

    testBusinessInfo();

    testCommMedia();
  }

  private void testCompany() throws WsSrvException {
    // Read company
    EntityDto company = SharedJpaTestUtils.checkNonEmptyGoodResponse(
        getEntitySrv().readEntity(1), 1, EntityDto.class);
    assertEquals("Example", company.getName(), "BusinessInfo name not found.");
    assertNotNull(company.getEntityFields(), "BusinessInfo Fields are empty.");
    assertEquals(4, company.getEntityFields().size());

    assertEquals("http://www.example.com/",
        company.getEntityFields().get(0).getValue(),
        "BusinessInfo site not found.");
    assertEquals("1-888-1234567", company.getEntityFields().get(1).getValue(),
        "BusinessInfo phone not found.");
    assertEquals("info@example.com",
        company.getEntityFields().get(2).getValue(),
        "BusinessInfo email not found.");
    int addrId = Integer.parseInt(company.getEntityFields().get(3).getValue());
    assertEquals(1, addrId, "BusinessInfo address not found.");

    // Try Save it back
    try {
      company.setId(null);
      getEntitySrv().createEntity(company);
      fail("WsSrvException expected");
    } catch (WsSrvException e) {
      assertEquals("ERROR_CREATE_ENTITY" +
          " - Exception type org.springframework.dao.DataIntegrityViolationException" +
          " [could not execute statement; SQL [n/a]; constraint [UK_MHN0VA9IIHX5QN43YW3Y75HX1];" +
          " nested exception is org.hibernate.exception.ConstraintViolationException:" +
          " could not execute statement]", e.getMessage());
    }
  }

  private void testCommMedia() throws WsSrvException {
    // Read CommMedia
    CommMediaDto cm = SharedJpaTestUtils.checkNonEmptyGoodResponse(
        commMediaSrv.readEntities(), 4, CommMediaDto.class);
    assertEquals("LL_EMAIL", cm.getName(), "Field Category name not found.");

    // Try Save it back
    try {
      cm.setId(null);
      commMediaSrv.createEntity(cm);
      fail("WsSrvException expected");
    } catch (WsSrvException e) {
      assertEquals("ERROR_CREATE_COMM_MEDIA" +
          " - Exception type org.springframework.dao.DataIntegrityViolationException" +
          " [could not execute statement; SQL [n/a]; constraint [UK_IRHW2RVS1BM77D2SNAEENE85L];" +
          " nested exception is org.hibernate.exception.ConstraintViolationException:" +
          " could not execute statement]", e.getMessage());
    }
  }

  private void testField() throws WsSrvException {
    // Read Field
    FieldDto field = SharedJpaTestUtils
        .checkNonEmptyGoodResponse(fieldSrv.readEntity(1), 1, FieldDto.class);
    assertEquals("LL_SITE", field.getName(), "Field Category name not found.");

    // Try Save it back
    try {
      field.setId(null);
      fieldSrv.createEntity(field);
      fail("WsSrvException expected");
    } catch (WsSrvException e) {
      assertEquals("ERROR_CREATE_FIELD" +
          " - Exception type org.springframework.dao.DataIntegrityViolationException" +
          " [could not execute statement; SQL [n/a]; constraint [UKKA1MV1RBJ19YN9WE33OQ1BJM6];" +
          " nested exception is org.hibernate.exception.ConstraintViolationException:" +
          " could not execute statement]", e.getMessage());
    }
  }

  private void testFieldCategory() throws WsSrvException {
    // Read FieldCategory
    FieldCategoryDto fc = SharedJpaTestUtils.checkNonEmptyGoodResponse(
        fieldCatSrv.readEntity(1), 1, FieldCategoryDto.class);
    assertEquals("LL_COMPANY", fc.getName(), "Field Category name not found.");

    // Try Save it back
    try {
      fc.setId(null);
      fieldCatSrv.createEntity(fc);
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
  }

  private AddressDto testAddress() throws WsSrvException {
    // Read address
    AddressDto address = SharedJpaTestUtils.checkNonEmptyGoodResponse(
        getAddressSrv().readEntity(1), 1, AddressDto.class);
    assertEquals("Here we are", address.getLine1(),
        "Address line 1 not found.");
    assertEquals("ABC123", address.getZip(), "Zip not found.");

    // Try Save it back
    try {
      address.setId(null);
      getAddressSrv().createEntity(address);
      fail("WsSrvException expected");
    } catch (WsSrvException e) {
      assertEquals(ErrorCodes.ERROR_CREATE_ADDRESS.name() +
          " - Exception type org.springframework.dao.DataIntegrityViolationException" +
          " [could not execute statement; SQL [n/a]; constraint [UKSOJEAX63P91GBQA7B803DXSR0];" +
          " nested exception is org.hibernate.exception.ConstraintViolationException:" +
          " could not execute statement]", e.getMessage());
    }

    return address;
  }

  private void testBusinessInfo() throws WsSrvException, IOException {
    FieldCategoryDto fcat = new FieldCategoryDto();
    fcat.setId(1);
    EntityDto dto = new EntityDto();
    dto.setName("Temp");
    dto.setFieldCat(fcat);
    List<EntityFieldDto> fields = new ArrayList<>();
    fields.add(getTestEntityFieldDto(1, "http://www.temp.com/"));
    fields.add(getTestEntityFieldDto(2, "123-456-7890"));
    int fsize = 2;

    dto.setEntityFields(fields);
    EntityDto dto1 =
        (EntityDto) getEntitySrv().createEntity(dto).getData().get(0);

    // Test values
    assertEquals(dto.getName(), dto1.getName(),
        "BusinessInfo name doesn't match.");
    List<EntityFieldDto> nfields = dto1.getEntityFields();
    assertNotNull(nfields, "BusinessInfo Fields are null");
    assertEquals(fsize, dto1.getEntityFields().size(),
        "Size of BusinessInfo Fields doesn't match.");

    for (int i = 0; i < nfields.size(); i++) {
      assertEquals(fields.get(i).getValue(), nfields.get(i).getValue());
      assertEquals(fields.get(i).getField().getId(),
          nfields.get(i).getField().getId());

      // BusinessInfo should not be set
      assertNull(nfields.get(i).getEntity());
    }

    // Update company
    final String name = "Tempo";
    dto1.setName(name);
    // Remove old company field
    dto1.getEntityFields().remove(0);

    // add new company field
    final String nvalue = "zzz";
    dto1.getEntityFields().add(getTestEntityFieldDto(3, nvalue));

    EntityDto dto2 =
        (EntityDto) getEntitySrv().updateEntity(dto1).getData().get(0);
    assertEquals(name, dto2.getName());

    // Check for new field
    List<EntityFieldDto> ufields = dto2.getEntityFields();
    assertNotNull(ufields, "BusinessInfo Fields are null");
    assertEquals(fsize, dto2.getEntityFields().size(),
        "Size of BusinessInfo Fields doesn't match.");

    // Check new filed
    assertEquals("zzz", ufields.get(fsize - 1).getValue());
  }

  private EntityFieldDto getTestEntityFieldDto(int id, String value) {
    EntityFieldDto cfield = new EntityFieldDto();
    FieldDto field = new FieldDto();
    field.setId(id);
    cfield.setField(field);
    cfield.setValue(value);

    return cfield;
  }

  private void testCitySrv() throws WsSrvException {
    // Read city
    CityDto city = SharedJpaTestUtils.checkNonEmptyGoodResponse(
        getCitySrv().readEntity(1), 1, CityDto.class);
    assertEquals("Toronto", city.getName(), "Toronto not found.");

    // Try Delete City
    try {
      getCitySrv().deleteEntity(city.getId());
      fail("WsSrvException expected");
    } catch (WsSrvException e) {
      assertEquals(ErrorCodes.ERROR_DELETE_CITY.name() +
          " - Exception type org.springframework.dao.DataIntegrityViolationException" +
          " [could not execute statement; SQL [n/a]; constraint [FKPO044NG5X4GYNB291CV24VTEA];" +
          " nested exception is org.hibernate.exception.ConstraintViolationException:" +
          " could not execute statement]", e.getMessage());
    }

    // Try Save it back
    try {
      city.setId(null);
      getCitySrv().createEntity(city);
      fail("WsSrvException expected");
    } catch (WsSrvException e) {
      assertEquals(ErrorCodes.ERROR_CREATE_CITY.name() +
          " - Exception type org.springframework.dao.DataIntegrityViolationException" +
          " [could not execute statement; SQL [n/a]; constraint [UKTCEET8EH77LBFKI89RVCM27YK];" +
          " nested exception is org.hibernate.exception.ConstraintViolationException:" +
          " could not execute statement]", e.getMessage());
    }
  }

  private void testRegionSrv() throws WsSrvException {
    // Read region
    RegionDto region = SharedJpaTestUtils.checkNonEmptyGoodResponse(
        getRegionSrv().readEntity(1), 1, RegionDto.class);
    assertEquals("Ontario", region.getName(), "Onario not found.");
    assertEquals("ON", region.getShortName(),
        "Ontario short name doesn't match.");

    // Try Delete Region
    try {
      getRegionSrv().deleteEntity(region.getId());
      fail("WsSrvException expected");
    } catch (WsSrvException e) {
      assertEquals(ErrorCodes.ERROR_DELETE_REGION.name() +
          " - Exception type org.springframework.dao.DataIntegrityViolationException" +
          " [could not execute statement; SQL [n/a]; constraint [FKSI0DKM9KK6DYUEDMC0J18T770];" +
          " nested exception is org.hibernate.exception.ConstraintViolationException:" +
          " could not execute statement]", e.getMessage());
    }

    // Try Save it back
    try {
      region.setId(null);
      getRegionSrv().createEntity(region);
      fail("WsSrvException expected");
    } catch (WsSrvException e) {
      assertEquals(e.getErrorCode(), ErrorCodes.ERROR_CREATE_REGION.name());
    }
  }

  void testCountrySrv() throws Exception {
    // Read country
    CountryDto country = SharedJpaTestUtils.checkNonEmptyGoodResponse(
        getCountrySrv().readEntity(1), 1, CountryDto.class);
    assertEquals("Canada", country.getName(), "Canada not found.");
    assertEquals("LL_PROVINCE", country.getRegionName(),
        "Canada region name doesn't match.");

    // Try Delete Country
    try {
      getCountrySrv().deleteEntity(country.getId());
      fail("WsSrvException expected");
    } catch (WsSrvException e) {
      assertEquals(ErrorCodes.ERROR_DELETE_COUNTRY.name() +
          " - Exception type org.springframework.dao.DataIntegrityViolationException" +
          " [could not execute statement; SQL [n/a]; constraint [FK7VB2CQCNKR9391HFN72LOUXKQ];" +
          " nested exception is org.hibernate.exception.ConstraintViolationException:" +
          " could not execute statement]", e.getMessage());
    }

    // Try Save it back
    try {
      country.setId(null);
      getCountrySrv().createEntity(country);
      fail("WsSrvException expected");
    } catch (WsSrvException e) {
      assertEquals(ErrorCodes.ERROR_CREATE_COUNTRY.name() +
          " - Exception type org.springframework.dao.DataIntegrityViolationException" +
          " [could not execute statement; SQL [n/a]; constraint [UK_LLIDYP77H6XKEOKPBMOY710D4];" +
          " nested exception is org.hibernate.exception.ConstraintViolationException:" +
          " could not execute statement]", e.getMessage());
    }

    // Add country
    CountryDto cnew = new CountryDto();
    cnew.setName("Test");
    cnew.setRegionName("LL_TEST");
    cnew.setPostalName("LL_ZIP");
    cnew.setRegionField("name");
    cnew.setAddrFormatter("get_qq_address");
    cnew.setId(
        SharedJpaTestUtils.checkResponse(getCountrySrv().createEntity(cnew)));

    // Check the list of all countries
    SharedJpaTestUtils.checkNonEmptyGoodResponse(getCountrySrv().readEntities(),
        3, CountryDto.class);
  }

  @Test
  void testNonExistingRead() throws Exception {
    // Read country
    assertNull(SharedJpaTestUtils.checkNonEmptyGoodResponse(
        getCountrySrv().readEntity(1), 0, CountryDto.class));

    // Read region
    assertNull(SharedJpaTestUtils.checkNonEmptyGoodResponse(
        getRegionSrv().readEntity(1), 0, RegionDto.class));

    // Read city
    assertNull(SharedJpaTestUtils.checkNonEmptyGoodResponse(
        getCitySrv().readEntity(1), 0, CityDto.class));

    // Read address
    assertNull(SharedJpaTestUtils.checkNonEmptyGoodResponse(
        getAddressSrv().readEntity(1), 0, AddressDto.class));

    // Read company
    assertNull(SharedJpaTestUtils.checkNonEmptyGoodResponse(
        getEntitySrv().readEntity(1), 0, EntityDto.class));
  }

  private void testEmptyDataResponse(DataGoodResponse data) {
    assertNotNull(data, "Response is null");
    assertNotNull(data.getData(), "Data is null");
    assertEquals(0, data.getData().size(), "Data is not empty");
  }

  private void loadDbScript() throws IOException {
    if (initialized)
      return;

    Resource resource = new ClassPathResource("test_data.sql");
    ResourceDatabasePopulator databasePopulator =
        new ResourceDatabasePopulator(resource);
    databasePopulator.execute(_ds);

    initialized = true;
  }

}
