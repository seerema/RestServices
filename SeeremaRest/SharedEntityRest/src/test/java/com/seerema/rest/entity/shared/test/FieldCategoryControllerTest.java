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

package com.seerema.rest.entity.shared.test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerema.shared.dto.FieldCategoryDto;

/**
 * Integration test for Base FieldCategory Controller
 *
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:/test.properties")
public class FieldCategoryControllerTest
    extends AbstractSharedEntityRestControllerTest<FieldCategoryDto> {

  @Override
  protected void initEntity(FieldCategoryDto entity) {
    entity.setName("LL_TEST");
  }

  @Override
  protected void setEntityPartial(FieldCategoryDto entity) {
    entity.setName("");
  }

  @Override
  protected void setEntityInvalid(FieldCategoryDto entity) {
    entity.setName("");
  }

  @Override
  protected String getEntityUrl() {
    return "field_cat";
  }

  @Override
  protected String getEntitiesUrl() {
    return "field_cats";
  }

  @Override
  protected void updateEntity(FieldCategoryDto entity) {
    entity.setId(getEntityIdx());
    entity.setName(getUpdateValue());
  }

  @Override
  protected int getEntityIdx() {
    return 2;
  }

  @Override
  protected String getUpdateValue() {
    return "LL_DEMO";
  }

  @Override
  protected String getUpdateIdx() {
    return "name";
  }

  @Override
  protected FieldCategoryDto getEntity() {
    return new FieldCategoryDto();
  }

}
