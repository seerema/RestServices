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

package com.seerema.crm.srv.test;

import com.seerema.base.WsSrvException;
import com.seerema.shared.jpa.status.test.AbstractEntityStatusServiceTest;

/**
 * Crm Service Tests
 */
public class CrmServiceTest extends AbstractEntityStatusServiceTest {

  // TODO Add runCheck
  // @Autowired
  // private ContactService contSrv;

  @Override
  protected void runCheck(int idx) throws WsSrvException {
    // TODO
  }

  @Override
  protected String getEntityFieldValue() {
    return "http://www.example.com/";
  }

  @Override
  protected String getEntityName() {
    return "Example";
  }

  @Override
  protected String getFieldName() {
    return "LL_SITE";
  }

  @Override
  protected String getFieldCategoryName() {
    return "LL_COMPANY";
  }

  @Override
  protected int getStatusNum() {
    return 3;
  }
}
