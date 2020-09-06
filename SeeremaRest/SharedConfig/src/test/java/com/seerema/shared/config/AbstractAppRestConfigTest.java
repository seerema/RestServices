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

import java.util.Properties;

import com.seerema.shared.common.TestConstants;

public abstract class AbstractAppRestConfigTest<T extends RestConfig>
    extends BaseAppRestConfigTestUnits<T> {

  protected final Boolean debug1 = Boolean.TRUE;
  protected final Boolean debug2 = Boolean.FALSE;

  @Override
  protected void testInitBeanProps() throws Exception {
    assertEquals(debug1.toString(), config.getPropByName("debug"));
  }

  @Override
  protected void setInitConfig() {
    config.setDebug(debug1);
  }

  @Override
  protected void invertConfig() {
    config.setDebug(!config.getDebug());
  }

  protected void testConfigProperties(Properties props, Boolean debug) {
    assertEquals(debug.toString(), props.getProperty("debug"),
        "debug parameter test failed");
  }

  @Override
  protected void setProperties(Properties props) {
    props.setProperty("debug", config.getDebug().toString());
  }

  protected void testConfigBean(Boolean debug) {
    assertEquals(debug, config.getDebug(), "debug parameter test failed");
  }

  @Override
  protected void testInitConfigBean() {
    testConfigBean(debug1);
  }

  @Override
  protected void testIvertedConfigBean() {
    testConfigBean(debug2);
  }

  @Override
  protected void testInitConfigProperties(Properties props) {
    testConfigProperties(props, debug1);
  }

  @Override
  protected void testInvertedConfigProperties(Properties props) {
    testConfigProperties(props, debug2);
  }

  @Override
  protected String getString() {
    return "home_dir=" + TestConstants.WORK_SBS_SHARED_DIR + "; debug=" +
        debug1 + ";";
  }
}
