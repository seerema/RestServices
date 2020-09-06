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

/**
 * Entity Service Tests
 */
public class EntityStatusServiceTest extends AbstractEntityStatusServiceTest {

  @Override
  protected String getEntityFieldValue() {
    return "test_value";
  }

  @Override
  protected String getEntityName() {
    return "Example";
  }

  @Override
  protected String getFieldName() {
    return "TEST_FIELD";
  }

  @Override
  protected String getFieldCategoryName() {
    return "TEST_FIELD_CATEGORY";
  }

  @Override
  protected int getStatusNum() {
    return 2;
  }

}
