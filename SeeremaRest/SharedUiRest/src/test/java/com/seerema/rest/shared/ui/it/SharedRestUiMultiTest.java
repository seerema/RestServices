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

package com.seerema.rest.shared.ui.it;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerema.rest.shared.base.utils.GenericRestMultiTest;
import com.seerema.rest.shared.base.utils.GenericRestTestUnit;
import com.seerema.rest.shared.ui.rest.SharedRestUiMultiTestUnit;

/**
 * Multi Thread Test for Shared Module
 * 
 */

@DirtiesContext
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
    value = { "spring.config.name=test" })
public class SharedRestUiMultiTest extends GenericRestMultiTest {

  @BeforeAll
  public static void setConfigTest() {
    SharedRestUiMultiTestUnit.GONFIG_TEST = SharedRestUiTest.GONFIG_TEST;
  }

  @Override
  public Class<? extends GenericRestTestUnit> getTestClass() {
    return SharedRestUiMultiTestUnit.class;
  }

}
