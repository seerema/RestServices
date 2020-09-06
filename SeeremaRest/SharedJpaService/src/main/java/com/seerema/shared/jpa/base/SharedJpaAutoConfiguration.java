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

package com.seerema.shared.jpa.base;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * AutoConfiguration class for shared library
 * 
 */

@Configuration
@EntityScan("com.seerema.shared.jpa.base.model")
@EnableJpaRepositories("com.seerema.shared.jpa.base.repo")
@ComponentScan("com.seerema.shared.jpa.base")
public class SharedJpaAutoConfiguration {

}
