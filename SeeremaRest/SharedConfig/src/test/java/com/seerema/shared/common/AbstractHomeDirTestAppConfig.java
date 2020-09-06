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

package com.seerema.shared.common;

import org.springframework.beans.factory.annotation.Value;

/**
 * Test Application Configuration
 * 
 */

public abstract class AbstractHomeDirTestAppConfig extends BaseTestAppConfig {

  @Value("${home.dir}")
  private String _hdir;

  protected String getHomeDir() {
    return _hdir;
  }

}
