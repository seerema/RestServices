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

package com.seerema.auth.prov.shared;

/**
 * Interface for Security Authentication Providers
 * 
 */

public interface SecurityAuthenticationProvider {

  /**
   * Get list of all configured users
   * 
   * @return List of users
   */
  String[] getUsers();
}
