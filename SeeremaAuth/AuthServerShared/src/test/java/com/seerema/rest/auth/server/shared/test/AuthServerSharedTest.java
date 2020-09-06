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
package com.seerema.rest.auth.server.shared.test;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerema.rest.auth.server.shared.common.AuthServerTestConstants;
import com.seerema.rest.auth.server.shared.utils.AbstractAuthServerSharedTest;

/**
 * Integration tests
 * 
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
    value = { "spring.config.name=test" })
public class AuthServerSharedTest extends AbstractAuthServerSharedTest {

  @Override
  protected String[] getCredentials() {
    return new String[] { AuthServerTestConstants.TEST_USER_NAME,
        AuthServerTestConstants.TEST_USER_PWD };
  }

  @Override
  protected String getTestUser() {
    return AuthServerTestConstants.TEST_USER_NAME;
  }

  @Override
  protected String getTestUserRole() {
    return "ROLE_" + AuthServerTestConstants.TEST_USER_ROLE;
  }

  @Override
  protected List<String> getTestUsers() {
    return Arrays
        .asList(new String[] { AuthServerTestConstants.TEST_USER_NAME });
  }

}
