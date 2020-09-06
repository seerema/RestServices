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

package com.seerema.shared.rest.response;

public class BaseResponse {

  // Response result. Default: false
  private boolean result = false;

  /**
   * @return the result
   */
  public boolean getResult() {
    return result;
  }

  /**
   * @param result the result to set
   */
  public void setResult(boolean result) {
    this.result = result;
  }
}
