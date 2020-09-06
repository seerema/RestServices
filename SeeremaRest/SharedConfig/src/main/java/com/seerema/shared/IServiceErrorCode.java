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

package com.seerema.shared;

/**
 * Interface for service error code. It depends of layer and could be HTTP Error code or 
 *  SQL Error code or any other.
 *  
 */
public interface IServiceErrorCode {

  /**
   * Get Service Error Code
   * 
   * @param errCode Error code
   * @return Service Error Code
   */
  int getServiceErrorCode(String errCode);
}
