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

package com.seerema.shared.ui.config.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerema.base.BaseUtils;
import com.seerema.shared.common.TestConstants;
import com.seerema.shared.ui.config.service.UiConfigService;

/**
 * Basic Web Tests
 *
 */

@DirtiesContext
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE,
    value = { "spring.config.name=test" })
public class BaseUiServiceTest {

  @Autowired
  private UiConfigService _wsrv;

  @BeforeEach
  public void clearConfigDir() throws IOException {
    File dconfig = new File(TestConstants.WORK_SBS_SHARED_DIR);
    if (dconfig.exists())
      BaseUtils.delDirRecurse(dconfig);

    assertFalse(dconfig.exists());
  }

  @Test
  public void testBaseUiService() {
    Map<String, String> config = _wsrv.getUiConfig("param1,param2,param3");

    assertNotNull(config);

    assertEquals("one", config.get("param1"));
    assertEquals("two", config.get("param2"));
    assertEquals("three", config.get("param3"));
  }

}