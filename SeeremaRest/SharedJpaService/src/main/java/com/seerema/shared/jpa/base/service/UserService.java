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

package com.seerema.shared.jpa.base.service;

import com.seerema.base.WsSrvException;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Interface for User Service
 */

public interface UserService {

  DataGoodResponse readUsers() throws WsSrvException;

  DataGoodResponse checkUser(String userName) throws WsSrvException;
}
