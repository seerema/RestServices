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

import java.net.URISyntaxException;

import com.seerema.catalog.rest.shared.CatalogRestTestConstants;
import com.seerema.catalog.srv.dto.CountryDto;

/**
 * protected integration test for Base Country Controller
 *
 */

public class CountryControllerTest
    extends AbstractSharedCatalogControllerTest<CountryDto> {

  @Override
  protected CountryDto getEntity() {
    return new CountryDto();
  }

  @Override
  protected void initEntity(CountryDto entity) {
    entity.setName("Test");
    entity.setPostalName("LL_ZIP");
    entity.setRegionField("name");
    entity.setAddrFormatter("test_addr_formatter");
  }

  @Override
  protected void setEntityPartial(CountryDto entity) {
    entity.setRegionName("LL_TEST");
  }

  @Override
  protected void setEntityInvalid(CountryDto entity) {
    entity.setName("");
  }

  @Override
  protected String getEntityUrl() {
    return "country";
  }

  @Override
  protected String getEntitiesUrl() {
    return "countries";
  }

  @Override
  protected void updateEntity(CountryDto entity) {
    entity.setId(getEntityIdx());
    entity.setName(getUpdateValue());
  }

  @Override
  protected int getEntityIdx() {
    return 3;
  }

  @Override
  protected String getUpdateValue() {
    return "Temp";
  }

  @Override
  protected void checkAfterCreate(CountryDto country)
      throws URISyntaxException {
    // Check total number
    checkDataGooResponse(CatalogRestTestConstants.BASE_TEST_URL + "countries",
        3);
  }
}