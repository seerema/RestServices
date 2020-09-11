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

package com.seerema.rest.entity.shared.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerema.rest.shared.base.rest.SharedRestTestUtils;

/**
 * Integration test for User Controller
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:/test.properties")
public class UserControllerTest extends SharedRestTestUtils {

  @Test
  void testUserRegistration() {
    checkDataResponse("/test/users", 0);

    checkTestUserReg();
    checkTestUserReg();
  }

  private void checkTestUserReg() {
    List<?> lst = checkDataResponse("/test/user_reg", 1);
    assertEquals("{id=1, name=anonymous}", lst.get(0).toString(),
        "User name doesn't match.");
  }
}