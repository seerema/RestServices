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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

import com.seerema.shared.common.TestConstants;

/**
 * Abstract file for Application mode WebService Configuration Tests
 * 
 */

@DirtiesContext
public abstract class BaseAppConfigTestUnits<T extends AbstractConfig> {

  @Autowired
  private ApplicationContext context;

  protected abstract String getRestCfgName();

  @SuppressWarnings("unchecked")
  protected T getConfig() {
    // Retrieve bean dynamically because auto-wiring by generic class doesn't work
    Object o = context.getBean(getRestCfgName());
    return (T) o;
  }

  protected abstract void checkConfig(T wcfg);

  @BeforeAll
  public static void checkConfigDir() {
    File d = new File(TestConstants.WORK_SBS_SHARED_DIR);
    assertTrue(d.exists() || !d.exists() && d.mkdir());
  }

  @AfterAll
  public static void delWsConfigFile() {
    File f = new File(TestConstants.WORK_WS_CONFIG_FILE);
    assertTrue(!f.exists() || f.exists() && f.delete());
  }

  @Test
  public void testRestConfig() {
    T wcfg = getConfig();
    assertNotNull(wcfg);

    checkConfig(wcfg);
  }

  @Test
  public void testHomeDir() throws Exception {
    T wcfg = getConfig();
    assertNotNull(wcfg);

    assertEquals(getHomeDir(), wcfg.getHomeDir(), "Home dir doesn't match");
  }

  protected String getHomeDir() {
    return TestConstants.WORK_SBS_SHARED_DIR;
  }
}
