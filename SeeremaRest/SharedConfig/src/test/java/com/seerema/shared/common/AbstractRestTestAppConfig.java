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

import com.seerema.shared.Constants;
import com.seerema.shared.config.RestConfig;
import com.seerema.shared.config.RestConfigProperties;

/**
 * Test Application Configuration
 * 
 */

public abstract class AbstractRestTestAppConfig<T extends RestConfig>
    extends AbstractUserServiceTestAppConfig {

  private RestConfigProperties _props;

  public abstract RestConfigProperties getWsConfigProperties()
      throws Exception;

  public abstract T getBaseWsConfig();

  public RestConfigProperties getWsConfigProps() throws Exception {
    return _props = getWsConfigProperties();
  }

  protected T getWsCfg() throws Exception {
    T cfg = getBaseWsConfig();
    cfg.setFileName(Constants.WS_CONFIG_FILE_NAME);
    cfg.setLogger(getLogger());
    cfg.setHomeDir(getHomeDir());
    cfg.readCtxHomeDir(_props);
    return cfg;
  }

  /**
   * Retrieve internal WsConfig Properties. Used for test.
   * 
   * @return WsConfig Properties
   */
  protected RestConfigProperties getWsProperties() {
    return _props;
  }
}
