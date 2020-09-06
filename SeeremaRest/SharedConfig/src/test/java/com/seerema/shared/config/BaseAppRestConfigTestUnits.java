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

package com.seerema.shared.config;

import com.seerema.shared.common.TestConstants;

/**
 * Abstract file for Application mode WebService Configuration Tests
 * 
 */

public abstract class BaseAppRestConfigTestUnits<T extends AbstractConfig>
    extends BaseConfigTestUnits<T> {

  @Override
  protected String getConfigFileName() {
    return TestConstants.WORK_WS_CONFIG_FILE;
  }

}
