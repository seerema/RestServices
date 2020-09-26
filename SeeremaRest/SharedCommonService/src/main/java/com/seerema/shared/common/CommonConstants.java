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

package com.seerema.shared.common;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Common Constants for all Services
 * 
 */

public class CommonConstants {

  // Default language
  public static final String DEFAULT_LANG = "en";

  //Array of known locales
  public static Map<String, Locale> LOCALES =
      new LinkedHashMap<String, Locale>();

  static {
    LOCALES.put("en", Locale.US);
    LOCALES.put("fr", Locale.CANADA_FRENCH);
    LOCALES.put("ru", new Locale("ru", "RU"));
    LOCALES.put("es", new Locale("es", "ES"));
    LOCALES.put("de", new Locale("de", "DE"));
  }
}
