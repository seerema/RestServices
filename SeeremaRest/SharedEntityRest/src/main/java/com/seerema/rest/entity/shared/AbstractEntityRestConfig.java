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

package com.seerema.rest.entity.shared;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.seerema.rest.shared.ui.app.config.AbstractBaseRestConfig;

/**
 * AutoConfiguration class for shared library
 */

public abstract class AbstractEntityRestConfig extends AbstractBaseRestConfig {

  @Bean
  Jackson2ObjectMapperBuilderCustomizer custObjecMapper() {
    return new Jackson2ObjectMapperBuilderCustomizer() {

      @Override
      public void
          customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        jacksonObjectMapperBuilder
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jacksonObjectMapperBuilder.modulesToInstall(new JavaTimeModule());
      }
    };
  }
}
