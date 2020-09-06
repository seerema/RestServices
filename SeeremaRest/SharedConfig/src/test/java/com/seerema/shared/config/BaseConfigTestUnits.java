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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import com.seerema.shared.Constants;
import com.seerema.shared.common.TestConstants;

public abstract class BaseConfigTestUnits<T extends AbstractConfig> {

  protected T config;

  protected File fconfig;
  protected static File sdir;

  @BeforeAll
  public static void setup() {
    sdir = new File(TestConstants.WORK_SBS_SHARED_DIR);
  }

  @BeforeEach
  public void init() throws IOException {
    fconfig = new File(getConfigFileName());
    delConfigFile();
  }

  @AfterEach
  public void clear() throws IOException {
    delConfigFile();
  }

  protected void delConfigFile() throws IOException {
    // Delete configuration file (if exists)
    if (fconfig.exists())
      assertTrue(fconfig.delete());
  }

  protected abstract T getNewConfigBean(String fileName);

  protected abstract T getExternalConfigBean(String fileName, String dir);

  protected abstract String getConfigFileName();

  protected abstract void testInitConfigBean();

  protected abstract void invertConfig();

  protected abstract void testIvertedConfigBean();

  protected abstract void setInitConfig();

  protected abstract void testInitBeanProps() throws Exception;

  protected abstract void testInitConfigProperties(Properties props);

  protected abstract void setProperties(Properties props);

  protected abstract void testInvertedConfigProperties(Properties props);

  protected abstract String getString();

  @Test
  public void testLocalConfig() throws FileNotFoundException, IOException {
    setLocalConfigBean(Constants.WS_CONFIG_FILE_NAME,
        TestConstants.WORK_SBS_SHARED_DIR);

    assertTrue(fconfig.exists());
    assertTrue(sdir.exists());
    String[] sdl = config.getHomeSubDirList();

    // Check for sub-directories (if any)
    if (sdl != null)
      for (String sd : sdl)
        assertTrue(new File(TestConstants.WORK_SBS_SHARED_PATH + sd).exists());

    assertTrue(config.isNew());
    Properties props = loadProps(fconfig);

    testInitConfigBean();

    testInitConfigProperties(loadProps(fconfig));

    // Change default configuration and load existing property file
    invertConfig();
    setProperties(props);

    // Don't save & Reload
    config.init();

    assertFalse(config.isNew(), "Configuration still new");

    testInitConfigBean();
    testInitConfigProperties(loadProps(fconfig));

    // Change inverted property file and reload configuration
    FileOutputStream out = new FileOutputStream(fconfig);
    props.store(out, "Inverted");
    out.close();

    setInitConfig();

    // Reload
    config.init();

    testIvertedConfigBean();
    testInvertedConfigProperties(loadProps(fconfig));
  }

  @Test
  public void testExternalConfig()
      throws IllegalArgumentException, IllegalAccessException, IOException {
    config = getExternalConfigBeanEx(Constants.WS_CONFIG_FILE_NAME,
        TestConstants.WORK_SBS_SHARED_DIR);
    testInitConfigBean();
    assertTrue(fconfig.exists());
    assertTrue(sdir.exists());
    assertTrue(config.isNew());

    invertConfig();
    config.save("Inverted");
    testIvertedConfigBean();

    assertTrue(fconfig.exists());
    assertTrue(sdir.exists());

    setLocalConfigBean(Constants.WS_CONFIG_FILE_NAME,
        TestConstants.WORK_SBS_SHARED_DIR);
    assertFalse(config.isNew());
    testIvertedConfigBean();
  }

  @Test
  public void testBadDynamicPropRetrieval() {
    T conf = getExternalConfigBeanEx(Constants.WS_CONFIG_FILE_NAME,
        TestConstants.WORK_SBS_SHARED_DIR);

    try {
      conf.getPropByName("qq");
      fail("Exception expected");
    } catch (Exception e) {
      assertEquals(Exception.class, e.getClass());
      assertEquals("Getter for property 'qq not found", e.getMessage());
    }
  }

  @Test
  public void testGoodDynamicPropRetrieval() throws Exception {
    config = getExternalConfigBeanEx(Constants.WS_CONFIG_FILE_NAME,
        TestConstants.WORK_SBS_SHARED_DIR);
    testInitBeanProps();
  }

  @Test
  public void testToString() {
    config = getExternalConfigBeanEx(Constants.WS_CONFIG_FILE_NAME,
        TestConstants.WORK_SBS_SHARED_DIR);
    assertEquals(getString(), config.toString(),
        "String config form doesn't match");
  }

  private void setLocalConfigBean(String file, String dir) {
    config = getNewConfigBean(file);
    setInitConfig();

    // Create new config.properties
    config.setHomeDir(TestConstants.WORK_SBS_SHARED_DIR);
    config.init();
  }

  protected Properties loadProps(File f) throws IOException {
    Properties props = new Properties();
    FileInputStream in = new FileInputStream(f);
    props.load(in);
    in.close();

    return props;
  }

  protected Logger getLogger() {
    return TestConstants.LOG;
  }

  private T getExternalConfigBeanEx(String fileName, String dir) {
    T conf = getExternalConfigBean(fileName, dir);
    conf.init();
    return conf;
  }
}
