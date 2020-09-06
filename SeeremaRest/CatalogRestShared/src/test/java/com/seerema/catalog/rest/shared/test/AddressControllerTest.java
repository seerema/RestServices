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

import com.seerema.catalog.srv.dto.AddressDto;
import com.seerema.catalog.srv.dto.CityDto;

/**
 * Integration test for Base Address Controller
 *
 */

public class AddressControllerTest
    extends AbstractSharedCatalogControllerTest<AddressDto> {

  @Override
  protected AddressDto getEntity() {
    return new AddressDto();
  }

  @Override
  protected void initEntity(AddressDto entity) {
    entity.setLine1(getTestString(191));
    entity.setLine2(getTestString(255));
    entity.setZip(getTestString(25));

    CityDto city = new CityDto();
    city.setId(1);
    entity.setCity(city);
  }

  @Override
  protected void setEntityPartial(AddressDto entity) {
    entity.setZip("");
  }

  @Override
  protected void setEntityInvalid(AddressDto entity) {
    entity.setLine1("");
  }

  @Override
  protected String getEntityUrl() {
    return "address";
  }

  @Override
  protected String getEntitiesUrl() {
    return "addresses";
  }

  @Override
  protected void updateEntity(AddressDto entity) {
    entity.setId(getEntityIdx());
    entity.setLine1(getUpdateValue());
  }

  @Override
  protected int getEntityIdx() {
    return 2;
  }

  @Override
  protected String getUpdateValue() {
    return "Avenue";
  }

  @Override
  protected String getUpdateIdx() {
    return "line1";
  }
}