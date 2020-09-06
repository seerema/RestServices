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

package com.seerema.rest.shared.base.it;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerema.rest.shared.base.rest.SharedRestBaseMultiTestUnit;
import com.seerema.rest.shared.base.utils.GenericRestMultiTest;
import com.seerema.rest.shared.base.utils.GenericRestTestUnit;

/**
 * Multi Thread Test for Shared Module
 * 
 */

@DirtiesContext
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
    value = { "spring.config.name=test" })
public class SharedRestBaseMultiTest extends GenericRestMultiTest {

  @Override
  public Class<? extends GenericRestTestUnit> getTestClass() {
    return SharedRestBaseMultiTestUnit.class;
  }

}
