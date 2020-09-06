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

package com.seerema.auth.prov.tomcat;

import org.slf4j.Logger;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import com.seerema.shared.common.TestConstants;

@SpringBootConfiguration
public class TomcatAuthProviderConfig {

  @Bean
  public Logger getLogger() {
    return TestConstants.LOG;
  }
}
