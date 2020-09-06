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

package com.seerema.rest.shared.base;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * AutoConfiguration class for Shared Rest Base library
 * 
 */

@Configuration
@ComponentScan("com.seerema.rest.shared.base")
public class AutoConfiguration {

  /**
   * Force JSON mapper convert camelCase into snake_case for all returning DTO
   * 
   * @return Jackson2ObjectMapperBuilderCustomizer
   */
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer baseConfigBuilder() {
    Jackson2ObjectMapperBuilderCustomizer cust =
        new Jackson2ObjectMapperBuilderCustomizer() {

          @Override
          public void customize(Jackson2ObjectMapperBuilder builder) {
            builder.serializationInclusion(Include.NON_NULL);
            builder.propertyNamingStrategy(
                PropertyNamingStrategy.SnakeCaseStrategy.SNAKE_CASE);
          }
        };

    return cust;
  }
}
