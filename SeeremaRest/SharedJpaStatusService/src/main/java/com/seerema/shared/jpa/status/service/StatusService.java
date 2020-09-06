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

package com.seerema.shared.jpa.status.service;

import com.seerema.base.WsSrvException;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Interface for Quest Statuses
 *
 */
public interface StatusService {

  public DataGoodResponse readStatuses() throws WsSrvException;
}
