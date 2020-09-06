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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerema.shared.config.RestConfig;

/**
 * Basic Web Tests
 * 
 */

@DirtiesContext
@ExtendWith(SpringExtension.class)
@SpringBootTest(value = { "spring.config.name=test" })
public class BaseRestConfigAppTest
    extends AbstractRestConfigAppTest<RestConfig> {

}