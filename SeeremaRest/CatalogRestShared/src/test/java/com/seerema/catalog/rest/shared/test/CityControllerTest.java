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

import com.seerema.catalog.srv.dto.CityDto;
import com.seerema.catalog.srv.dto.RegionDto;

/**
 * protected integration test for Base City Controller
 *
 */

public class CityControllerTest
    extends AbstractSharedCatalogControllerTest<CityDto> {

  @Override
  protected CityDto getEntity() {
    return new CityDto();
  }

  @Override
  protected void initEntity(CityDto entity) {
    entity.setName("Test");

    RegionDto region = new RegionDto();
    region.setId(1);
    entity.setRegion(region);
  }

  @Override
  protected void setEntityPartial(CityDto entity) {
    entity.setName("Test");

  }

  @Override
  protected void setEntityInvalid(CityDto entity) {
    entity.setName("");
  }

  @Override
  protected String getEntityUrl() {
    return "city";
  }

  @Override
  protected String getEntitiesUrl() {
    return "cities";
  }

  @Override
  protected void updateEntity(CityDto entity) {
    entity.setId(getEntityIdx());
    entity.setName(getUpdateValue());
  }

  @Override
  protected int getEntityIdx() {
    return 2;
  }

  @Override
  protected String getUpdateValue() {
    return "Temp";
  }

  @Override
  protected String getUpdateIdx() {
    return "name";
  }
}