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

package com.seerema.shared.config;

/**
 * Base class for all future Ws Configuratino Properties
 * 
 */
public class RestConfigProperties {

  private Boolean debug;

  /**
   * @return the debug
   */
  public Boolean getDebug() {
    return debug;
  }

  /**
   * @param debug
   *          the debug to set
   */
  public void setDebug(Boolean debug) {
    this.debug = debug;
  }
}
