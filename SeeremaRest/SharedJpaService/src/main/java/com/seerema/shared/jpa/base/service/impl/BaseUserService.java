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

package com.seerema.shared.jpa.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.seerema.base.WsSrvException;
import com.seerema.shared.dto.UserDto;
import com.seerema.shared.jpa.base.model.User;
import com.seerema.shared.jpa.base.repo.UserRepo;
import com.seerema.shared.jpa.base.service.UserService;
import com.seerema.shared.jpa.base.shared.ErrorCodes;
import com.seerema.shared.rest.response.DataGoodResponse;
import com.seerema.srv.shared.mapper.EntityMapper;

/**
 * User Service
 */

public class BaseUserService extends BaseDbAccessService
    implements UserService {

  @Autowired
  private UserRepo _repo;

  @Autowired
  private EntityMapper _mapper;

  @Override
  public DataGoodResponse readUsers() throws WsSrvException {
    Iterable<User> users;

    try {
      users = _repo.findAllByOrderByNameAsc();
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_READ_USERS.name(), e);
    }

    return new DataGoodResponse(_mapper.map(users, UserDto.class));
  }

  /**
   * Check user registration
   * 
   * @param userName User Name
   * @throws WsSrvException 
   */
  @Override
  public DataGoodResponse checkUser(String userName) throws WsSrvException {
    // Check user registration
    User user;
    try {
      user = _repo.findByName(userName);
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_FIND_USER.name(), e);
    }

    if (user == null)
      try {
        // Register user
        _repo.save(new User(userName));
      } catch (DataAccessException e) {
        throw throwError(ErrorCodes.ERROR_CREATE_USER.name(), e);
      }

    return readUsers();
  }

}
