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

package com.seerema.shared.jpa.base.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerema.base.WsSrvException;
import com.seerema.shared.dto.UserDto;
import com.seerema.shared.jpa.base.service.UserService;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Empty DB test
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:/test.properties")
public class UserServiceTest {

  private static final String TEST_USER = "user";

  @Autowired
  private UserService _users;

  @Test
  void testUserRegistration() throws WsSrvException {
    // Check users list is empty
    getUser(_users.readUsers(), 0);

    // Repeat check twice
    checkTestUser();
    checkTestUser();
  }

  private void checkTestUser() throws WsSrvException {
    _users.checkUser(TEST_USER);

    // Check test user registered
    assertEquals(TEST_USER, getUser(_users.readUsers(), 1),
        "Test user name doesn't match");
  }

  private String getUser(DataGoodResponse resp, int size) {
    // Check users list is empty
    assertTrue(resp.getResult(), "List of users failed.");
    assertEquals(size, resp.getData().size(), "List of users doesn't match.");

    return size == 0 ? null : ((UserDto) resp.getData().get(0)).getName();
  }

}
