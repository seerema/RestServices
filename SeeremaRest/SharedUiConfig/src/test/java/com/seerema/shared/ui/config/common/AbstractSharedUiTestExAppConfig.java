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

package com.seerema.shared.ui.config.common;

import com.seerema.shared.config.RestConfig;
import com.seerema.shared.config.RestConfigProperties;
import com.seerema.shared.ui.config.UIConfigProperties;
import com.seerema.shared.ui.config.UiConfig;
import com.seerema.shared.ui.config.config.TestUiConfig;
import com.seerema.shared.ui.config.config.TestUiConfigProperties;

/**
 * Shared Configuration class for Web Spring Boot Test Launcher
 * 
 */

public abstract class AbstractSharedUiTestExAppConfig
    extends AbstractSharedUiTestAppConfig<RestConfig> {

  @Override
  public UiConfig getWebConfig() {
    return new TestUiConfig();
  }

  @Override
  public UIConfigProperties getWebConfigProperties() {
    return new TestUiConfigProperties();
  }

  @Override
  public RestConfigProperties getWsConfigProperties() {
    return new RestConfigProperties();
  }

  @Override
  public RestConfig getBaseWsConfig() {
    return new RestConfig();
  }
}
