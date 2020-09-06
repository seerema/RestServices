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

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerema.shared.SharedTestAppConfig;
import com.seerema.shared.config.RestConfig;
import com.seerema.shared.config.app.AbstractAppRestConfigTestNew;

/**
 * Basic Web Tests
 * 
 */

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:test.properties")
@ContextConfiguration(classes = { SharedTestAppConfig.class })
public class AppNewRestConfigTest
    extends AbstractAppRestConfigTestNew<RestConfig> {

  @Override
  protected String getRestCfgName() {
    return "rest_cfg";
  }

}