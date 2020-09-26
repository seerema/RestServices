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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerema.shared.SharedTestAppConfig;
import com.seerema.shared.common.SharedTestUtils;
import com.seerema.shared.common.TestConstants;
import com.seerema.shared.config.RestConfig;
import com.seerema.shared.service.BaseService;
import com.seerema.shared.service.impl.AppRestInfo;
import com.seerema.shared.service.impl.BaseServiceImpl;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:test.properties")
@ContextConfiguration(
    classes = { SharedTestAppConfig.class, BaseServiceImpl.class })
public class BaseServiceTest {

  @Autowired
  BaseService<RestConfig> _bs;

  @Test
  public void testGetRestAppVersion() throws IOException {
    AppRestInfo info = (AppRestInfo) _bs.getRestInfo();
    assertTrue(info.hasMavenVersion());
    assertNotNull(info.getRestVersion());
    assertEquals(
        SharedTestUtils.readAppVersion(TestConstants.TARGET_PATH +
            "test-classes" + File.separator + "META-INF" + File.separator),
        info.getRestVersion(), "App Version doesn't match");
  }
}
