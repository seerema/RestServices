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

import com.seerema.catalog.srv.shared.CatalogConstants;
import com.seerema.rest.shared.base.common.RestBaseConstants;
import com.seerema.rest.shared.base.rest.AbstractSharedEntityControllerTest;

/**
 * Integration test for Catalog Controller
 *
 */

public abstract class AbstractSharedCatalogControllerTest<T>
    extends AbstractSharedEntityControllerTest<T> {

  @Override
  protected String getModuleName() {
    return CatalogConstants.MODULE_NAME;
  }

  @Override
  protected String getSecurityPrefix() {
    return RestBaseConstants.SECURITY_PREFIX + "/";
  }
}
