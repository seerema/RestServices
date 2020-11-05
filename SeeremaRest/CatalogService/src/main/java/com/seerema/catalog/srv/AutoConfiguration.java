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

package com.seerema.catalog.srv;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.seerema.base.WsSrvException;
import com.seerema.catalog.srv.shared.CatalogConstants;
import com.seerema.shared.dto.ModuleDto;
import com.seerema.shared.jpa.base.AbstractJpaConfiguration;

/**
 * AutoConfiguration class for shared library
 * 
 */

@Configuration
@EntityScan
@EnableJpaRepositories
@ComponentScan("com.seerema.catalog.srv")
public class AutoConfiguration extends AbstractJpaConfiguration {

  @Bean("mod_" + CatalogConstants.MODULE_NAME)
  protected ModuleDto getModule() throws WsSrvException {
    return getModule(CatalogConstants.MODULE_NAME);
  }
}