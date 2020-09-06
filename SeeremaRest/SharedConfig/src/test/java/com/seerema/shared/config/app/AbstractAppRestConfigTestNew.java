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

package com.seerema.shared.config.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;

import com.seerema.shared.config.BaseAppConfigTestUnits;
import com.seerema.shared.config.RestConfig;

/**
 * Basic Web Tests for SharedRestBaseWsConfig. Clear all existing configuration before test start
 * to test correct load from application.properties file
 * 
 */

public abstract class AbstractAppRestConfigTestNew<T extends RestConfig>
    extends BaseAppConfigTestUnits<T> {

  @BeforeAll
  public static void clearConfigFile() throws IOException {
    delWsConfigFile();
  }

  @Override
  protected void checkConfig(RestConfig wcfg) {
    assertTrue(wcfg.getDebug());
  }

}
