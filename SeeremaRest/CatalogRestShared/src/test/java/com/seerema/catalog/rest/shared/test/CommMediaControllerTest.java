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
import com.seerema.shared.dto.CommMediaDto;

/**
 * protected integration test for Base CommMedia Controller
 *
 */

public class CommMediaControllerTest
    extends AbstractSharedCatalogControllerTest<CommMediaDto> {

  @Override
  protected CommMediaDto getEntity() {
    return new CommMediaDto();
  }

  @Override
  protected void initEntity(CommMediaDto entity) {
    entity.setName("Test");
  }

  @Override
  protected void setEntityPartial(CommMediaDto entity) {
  }

  @Override
  protected void setEntityInvalid(CommMediaDto entity) {
    entity.setName("");
  }

  @Override
  protected String getEntityUrl() {
    return "comm_media";
  }

  @Override
  protected String getEntitiesUrl() {
    return "comm_medias";
  }

  @Override
  protected void updateEntity(CommMediaDto entity) {
    entity.setId(getEntityIdx());
    entity.setName(getUpdateValue());
  }

  @Override
  protected int getEntityIdx() {
    return 5;
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
  protected void checkAfterCreate(CommMediaDto cmedia)
      throws URISyntaxException {
    // Check total number
    checkDataGooResponse(CatalogRestTestConstants.BASE_TEST_URL + "comm_medias",
        5);
  }
}