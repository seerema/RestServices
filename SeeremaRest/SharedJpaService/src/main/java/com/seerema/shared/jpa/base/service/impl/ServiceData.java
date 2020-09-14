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

import com.seerema.shared.jpa.base.model.BaseEntityUser;
import com.seerema.shared.jpa.base.model.User;

/**
 * Temporarily Service data
 */
public class ServiceData {

  final BaseEntityUser entity;

  final User user;

  public ServiceData(BaseEntityUser entity, User user) {
    this.entity = entity;
    this.user = user;
  }

  public BaseEntityUser getEntity() {
    return entity;
  }

  public User getUser() {
    return user;
  }
}
