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

package com.seerema.shared.service.impl;

import com.seerema.shared.Constants;
import com.seerema.shared.common.UserService;

public class AnonymousUserServiceImpl implements UserService {

  @Override
  public String getLoginUser() {
    return Constants.ANONYMOUS_USER;
  }

}
