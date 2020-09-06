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

import java.lang.reflect.InvocationTargetException;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import com.seerema.shared.common.AbstractRestTestAppConfig;
import com.seerema.shared.config.RestConfig;
import com.seerema.shared.ui.config.UIConfigProperties;
import com.seerema.shared.ui.config.UiConfig;

/**
 * Base Configuration class for Web Based Application Configuration
 * 
 */

public abstract class AbstractSharedUiTestAppConfig<T extends RestConfig>
    extends AbstractRestTestAppConfig<T> {

  private UIConfigProperties _wprops;

  public abstract UiConfig getWebConfig();

  public abstract UIConfigProperties getWebConfigProperties();

  @Bean("ui_cfg")
  @DependsOn("ui_cfg_props")
  public UiConfig webCfg()
      throws NoSuchMethodException, SecurityException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException {
    UiConfig cfg = getWebConfig();
    cfg.setFileName(SharedUiConstants.UI_CONFIG_FILE_NAME);
    cfg.setHomeDir(getHomeDir());
    cfg.setLogger(getLogger());
    cfg.readCtxHomeDir(_wprops);
    return cfg;
  }

  @Bean("ui_cfg_props")
  @ConfigurationProperties("test.ui.config")
  public UIConfigProperties webConfigProperties() {
    _wprops = getWebConfigProperties();
    return _wprops;
  }
}
