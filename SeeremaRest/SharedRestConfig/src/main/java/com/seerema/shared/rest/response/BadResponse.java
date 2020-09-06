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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.seerema.base.WsError;
import com.seerema.base.WsSrvException;

/**
 * Bad response with details included
 */
@JsonInclude(Include.NON_EMPTY)
public class BadResponse extends BaseResponse {

  private WsError error;

  /**
   * Default constructor
   */
  public BadResponse() {
    // Do nothing
  }

  /**
   * Main constructor
   * 
   * @param code Error code
   * @param message Error message
   * @param info Error info
   * @param debug Debug flag. Clearing details messages if false
   */
  public BadResponse(WsError error, boolean debug) {
    this.error = error;

    if (!(debug))
      // Clear details
      this.error.clearDetails();

  }

  /**
   * Create Bad Response out of WsSrvException using the next mapping:
   * 
   * @param e Exception
   * @param debug Debug flag. If false than clear details
   */
  public BadResponse(WsSrvException e, boolean debug) {
    this(e.getError(), debug);
  }

  /**
   * @return the error object
   */
  public WsError getError() {
    return error;
  }

  /**
   * @param error the error to set
   */
  public void setError(WsError error) {
    this.error = error;
  }
}
