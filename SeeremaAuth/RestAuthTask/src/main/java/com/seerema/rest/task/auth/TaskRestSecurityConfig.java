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

package com.seerema.rest.task.auth;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.seerema.rest.auth.base.AbstractRestSecurityConfig;
import com.seerema.task.srv.shared.TaskConstants;

/**
 * Security configuration for Task REST services
 * 
 */
@EnableWebSecurity
public class TaskRestSecurityConfig extends AbstractRestSecurityConfig {

  @Override
  protected String getModuleName() {
    return TaskConstants.MODULE_NAME;
  }
}
