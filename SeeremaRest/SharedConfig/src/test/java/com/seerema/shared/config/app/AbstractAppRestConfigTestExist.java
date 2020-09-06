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

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.BeforeAll;

import com.seerema.shared.Constants;
import com.seerema.shared.common.TestConstants;
import com.seerema.shared.config.BaseAppConfigTestUnits;
import com.seerema.shared.config.RestConfig;

/**
 * Basic Web Tests for SharedRestBaseWsConfig. Recreate configuration.properties file before test start
 * to test correct load from external configuration.properties file
 * 
 */

public abstract class AbstractAppRestConfigTestExist<T extends RestConfig>
    extends BaseAppConfigTestUnits<T> {

  @BeforeAll
  public static void clearExistingPropFile() throws IOException {
    // Copy file from src folder. Rewrite if exist
    Files.copy(
        new File(TestConstants.SRC_TEST_DIR + TestConstants.CONFIG_DIR +
            Constants.WS_CONFIG_FILE_NAME).toPath(),
        new File(TestConstants.WORK_WS_CONFIG_FILE).toPath(),
        StandardCopyOption.REPLACE_EXISTING);

  }

  @Override
  protected void checkConfig(RestConfig wcfg) {
    assertFalse(wcfg.getDebug());
  }

}