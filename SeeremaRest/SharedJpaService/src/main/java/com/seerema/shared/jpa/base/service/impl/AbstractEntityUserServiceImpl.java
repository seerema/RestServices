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
import com.seerema.shared.dto.AbstractEntityDto;
import com.seerema.shared.jpa.base.model.BaseEntityUser;
import com.seerema.shared.jpa.base.model.User;
import com.seerema.shared.jpa.base.repo.UserRepo;
import com.seerema.shared.jpa.base.service.BaseEntityUserService;
import com.seerema.shared.jpa.base.shared.ErrorCodes;

/**
 * Partial Implementation for BaseEntity Service
 *
 * @param <T1> Entity
 * @param <T2> EntityDto
 */

public abstract class AbstractEntityUserServiceImpl<T1 extends BaseEntityUser, T2 extends AbstractEntityDto>
    extends AbstractEntityServiceImpl<T1, T2>
    implements BaseEntityUserService<T1, T2> {

  @Autowired
  private UserRepo _users;

  /**
   * Find user by name
   * 
   * @param userName User Name
   * @return User model
   * @throws WsSrvException 
   */
  protected User findUser(String userName) throws WsSrvException {
    try {
      return _users.findByName(userName);
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_FIND_USER_NAME.name(), e);
    }
  }

  protected T1 insertUser(T2 dto, User user) throws WsSrvException {
    @SuppressWarnings("unchecked")
    Class<T1> clazz = (Class<T1>) getDtoDestClass(dto);

    T1 entity = getEntityMapper().convert(dto, clazz);

    // Check if username needs to be injected
    if (user != null)
      entity.setUser(user);

    return entity;
  }

  protected ServiceData checkUserAccess(T2 entity, String userName,
      Boolean allowOverride) throws WsSrvException {
    // Find authorized user
    User authz = findUser(userName);

    // Get the previous status
    T1 dprev = readRawEntity(entity.getId());

    // Quick Check
    if (dprev == null)
      throw new WsSrvException(ErrorCodes.INVALID_UPDATE_ENTITY.name(),
          "Unable update non-existing entity",
          "Entity with id #" + entity.getId() + " not found");

    // Check if username matches
    if (!(dprev.getUser().equals(authz) || allowOverride))
      throw new WsSrvException(ErrorCodes.USER_ACCESS_DENIED.name(),
          "Username for update doesn't match existing",
          "Existing username [" + dprev.getUser().getName() +
              "] != request username [" + userName + "]");

    return new ServiceData(dprev, authz);
  }
}
