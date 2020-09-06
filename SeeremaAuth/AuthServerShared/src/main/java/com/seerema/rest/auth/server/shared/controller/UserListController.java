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

package com.seerema.rest.auth.server.shared.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seerema.auth.prov.shared.SecurityAuthenticationProvider;

/**
 * REST controller for user list
 *
 */

@RestController
public class UserListController {

  @Autowired
  private SecurityAuthenticationProvider _prov;

  @RequestMapping("/users")
  public String[] getUserList() {
    return _prov.getUsers();
  }
}
