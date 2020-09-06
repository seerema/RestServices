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

package com.seerema.shared.test;

import com.seerema.shared.config.AbstractAppRestConfigTest;
import com.seerema.shared.config.RestConfig;

public class RestConfigTest
    extends AbstractAppRestConfigTest<RestConfig> {

  @Override
  protected RestConfig getNewConfigBean(String fileName) {
    return new RestConfig(fileName, getLogger());
  }

  @Override
  protected RestConfig getExternalConfigBean(String fileName,
      String dir) {
    return new RestConfig(fileName, dir, debug1.toString(), getLogger());
  }

}
