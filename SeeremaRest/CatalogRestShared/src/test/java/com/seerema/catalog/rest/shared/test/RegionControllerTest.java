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
import com.seerema.catalog.srv.dto.RegionDto;

/**
 * protected integration test for Base Region Controller
 *
 */

public class RegionControllerTest
    extends AbstractSharedCatalogControllerTest<RegionDto> {

  @Override
  protected RegionDto getEntity() {
    return new RegionDto();
  }

  @Override
  protected void initEntity(RegionDto entity) {
    entity.setName("Test");

    CountryDto country = new CountryDto();
    country.setId(1);
    entity.setCountry(country);
  }

  @Override
  protected void setEntityPartial(RegionDto entity) {
    entity.setShortName("TT");

  }

  @Override
  protected void setEntityInvalid(RegionDto entity) {
    entity.setName("");
  }

  @Override
  protected String getEntityUrl() {
    return "region";
  }

  @Override
  protected String getEntitiesUrl() {
    return "regions";
  }

  @Override
  protected void updateEntity(RegionDto entity) {
    entity.setId(getEntityIdx());
    entity.setName(getUpdateValue());
  }

  @Override
  protected int getEntityIdx() {
    return 64;
  }

  @Override
  protected String getUpdateValue() {
    return "TX";
  }

  @Override
  protected String getUpdateIdx() {
    return "name";
  }

  @Override
  protected void checkAfterCreate(RegionDto region) throws URISyntaxException {
    // Check total number
    checkDataGooResponse(CatalogRestTestConstants.BASE_TEST_URL + "regions",
        64);
  }
}