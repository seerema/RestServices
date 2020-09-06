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

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.seerema.catalog.srv.dto.AddressDto;
import com.seerema.catalog.srv.dto.CityDto;
import com.seerema.catalog.srv.dto.CountryDto;
import com.seerema.catalog.srv.dto.RegionDto;
import com.seerema.catalog.srv.service.impl.BaseUiStateConfigCustomizer;
import com.seerema.shared.dto.EntityDto;
import com.seerema.shared.dto.FieldCategoryDto;
import com.seerema.shared.jpa.base.model.FieldCategory;
import com.seerema.shared.jpa.base.service.impl.AbstractEntityModuleServiceImpl;
import com.seerema.shared.jpa.base.utils.SharedJpaTestUtils;

/**
 * Test for configuration states
 */
public class ConfigruationStateTest extends SharedCatalogServiceTestUnits {

  @Autowired
  private BaseUiStateConfigCustomizer _custom;

  @Autowired
  private AbstractEntityModuleServiceImpl<FieldCategory, FieldCategoryDto> _fcat;

  @Test
  void testConfig() throws Exception {
    // Check setup status is country
    String state = _custom.getPropByName("state_to_continue");

    assertNotNull(state);
    assertEquals("country", state);

    // Add country
    CountryDto country = new CountryDto();
    country.setName("Test");
    country.setRegionName("LL_TEST");
    country.setPostalName("LL_ZIP");
    country.setRegionField("name");
    country.setAddrFormatter("test_addr_formatter");
    country.setId(SharedJpaTestUtils
        .checkResponse(getCountrySrv().createEntity(country)));

    // Test new state
    _custom.initState();
    assertEquals("region", _custom.getPropByName("state_to_continue"));

    // Add region
    RegionDto region = new RegionDto();
    region.setName("Qqqq");
    region.setShortName("QQ");
    region.setCountry(country);
    region.setId(
        SharedJpaTestUtils.checkResponse(getRegionSrv().createEntity(region)));

    // Test new state
    _custom.initState();
    assertEquals("city", _custom.getPropByName("state_to_continue"));

    // Add city
    CityDto city = new CityDto();

    city.setName("QQ");
    city.setRegion(region);
    city.setId(
        SharedJpaTestUtils.checkResponse(getCitySrv().createEntity(city)));

    // Test new state
    _custom.initState();
    assertEquals("address", _custom.getPropByName("state_to_continue"));

    // Add address
    AddressDto address = new AddressDto();
    address.setLine1("Here we go");
    address.setLine2("Here we go again");
    address.setZip("ABC123");
    address.setCity(city);
    address.setId(SharedJpaTestUtils
        .checkResponse(getAddressSrv().createEntity(address)));

    // Test state ready
    _custom.initState();
    assertEquals("business_info", _custom.getPropByName("state_to_continue"));

    // Create field category
    FieldCategoryDto fcat = new FieldCategoryDto();
    fcat.setId(1);
    fcat.setName("LL_TEST");
    _fcat.createEntity(fcat);

    // Finally add business_info
    EntityDto company = new EntityDto();
    company.setName("Example");
    company.setFieldCat(fcat);
    getEntitySrv().createEntity(company);

    // Test state ready
    _custom.initState();

    // Setup workflow completed
    assertEquals("", _custom.getPropByName("state_to_continue"));
  }

}
