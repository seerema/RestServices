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

package com.seerema.rest.shared.ui;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.rest.shared.base.controller.BaseVersionController;
import com.seerema.rest.shared.ui.controller.BaseConfigurationController;
import com.seerema.shared.ui.config.common.AbstractSharedUiTestExAppConfig;

/**
 * Configuration class for Spring Boot Test Launcher
 * 
 */

public abstract class AbstractSharedUiRestAppConfig
    extends AbstractSharedUiTestExAppConfig {

  @RestController
  @RequestMapping
  public class TestConfigurationController extends BaseConfigurationController {

  }

  @RestController
  @RequestMapping
  public class TestRestVersionController extends BaseVersionController {

  }
}
