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

package com.seerema.shared.service;

/**
 * Web App Info class
 * 
 */

public interface RestInfo {

  public static final String MANIFEST_PATH = "/META-INF/MANIFEST.MF";

  /**
   * Get full Build Version, for ex 1.0.0-RELEASE
   * @return Full Build Version
   */
  String getBuildVersion();

  /**
   * Get version of embedded Web Service, for ex 1.0.0
   * @return Version of embedded Web Service
   */
  String getRestVersion();
}
