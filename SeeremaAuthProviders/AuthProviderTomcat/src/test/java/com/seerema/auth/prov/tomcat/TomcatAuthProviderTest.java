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

package com.seerema.auth.prov.tomcat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.assertj.core.util.Files;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Tomcat Authentication Provider Test
 * 
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { TomcatUserSecurityProvider.class,
    TomcatAuthProviderConfig.class }, value = { "spring.config.name=test" })
public class TomcatAuthProviderTest {

  @Autowired
  private TomcatUserSecurityProvider _prov;

  // User roles
  private static final Set<String> USER_ROLES = new HashSet<>(
      Arrays.asList(new String[] { "user_role", "manager_role" }));

  // Admin roles
  private static final Set<String> ADMIN_ROLES =
      new HashSet<>(Arrays.asList(new String[] { "admin_role" }));

  // List with test users
  private static final String[] USERS = new String[] { "user", "admin" };

  // Map with roles
  private static final Map<String, Set<String>> ROLES = new HashMap<>();

  static {
    ROLES.put("user", USER_ROLES);
    ROLES.put("admin", ADMIN_ROLES);
  }

  @BeforeEach
  public void init() {
    clearUsersFile();
  }

  @Test
  public void testCorrect() {

    // Find user
    for (String name : USERS) {
      // Find user in a list
      String user = null;
      for (String suser : _prov.getUsers())
        if (suser.equals(name)) {
          user = suser;
          break;
        }

      assertNotNull(user);

      // Test provider
      UsernamePasswordAuthenticationToken auth =
          (UsernamePasswordAuthenticationToken) _prov
              .authenticate(new UsernamePasswordAuthenticationToken(name,
                  name + "_pwd", null));

      // Expected set of roles
      Set<String> roles = ROLES.get(name);

      // Check user roles
      assertNotNull(auth);
      assertEquals(roles.size(), auth.getAuthorities().size(),
          "Number of roles for " + name + " doesn't match.");

      // Go check role by role
      for (GrantedAuthority authority : auth.getAuthorities()) {
        String role = authority.getAuthority();
        assertTrue(roles.contains(role), "Role " + role +
            " not found in expected roles list for user " + name);
        roles.remove(role);
      }

      assertEquals(0, roles.size(), "Expected set is not empty");
    }
  }

  @Test
  public void testIncorrect() {

    try {
      // Test correct user but null password
      _prov.authenticate(
          new UsernamePasswordAuthenticationToken("user", null, null));
      fail("AuthenticationException expected");
    } catch (AuthenticationException e) {
      // Ignore
    }

    try {
      // Test correct user but empty password
      _prov.authenticate(
          new UsernamePasswordAuthenticationToken("user", "", null));
      fail("AuthenticationException expected");
    } catch (AuthenticationException e) {
      // Ignore
    }

    try {
      // Test correct user but invalid password
      _prov.authenticate(
          new UsernamePasswordAuthenticationToken("user", "zzz", null));
      fail("AuthenticationException expected");
    } catch (AuthenticationException e) {
      // Ignore
    }

    try {
      // Test invalid user
      _prov.authenticate(
          new UsernamePasswordAuthenticationToken("zzz", "xxx", null));
      fail("AuthenticationException expected");
    } catch (AuthenticationException e) {
      // Ignore
    }
  }

  @AfterEach
  public void clear() {
    clearUsersFile();
  }

  private void clearUsersFile() {
    // Same file as defined in test.properties
    File temp = new File("sbs_users.xml");

    if (temp.exists())
      Files.delete(temp);
  }
}
