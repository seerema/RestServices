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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.seerema.base.BaseUtils;
import com.seerema.shared.common.TestConstants;
import com.seerema.shared.config.RestConfig;

/**
 * Basic Web Tests
 * 
 */

public abstract class AbstractRestConfigAppTest<T extends RestConfig> {

  @Autowired
  T _rcfg;

  @BeforeEach
  public void clearConfigDir() throws IOException {
    File dconfig = new File(TestConstants.WORK_SBS_SHARED_DIR);
    if (dconfig.exists())
      BaseUtils.delDirRecurse(dconfig);

    assertFalse(dconfig.exists());
  }

  @Test
  public void testRestConfig() {
    assertNotNull(_rcfg);

    assertTrue(_rcfg.getDebug());
  }
}