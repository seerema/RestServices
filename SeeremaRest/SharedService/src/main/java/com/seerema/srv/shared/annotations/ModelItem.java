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

package com.seerema.srv.shared.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation ModelItem to map from Entity Column
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface ModelItem {

  String value() default "";

  /**
   * Flag to use or not DTO Getter
   * 
   * @return True(default) or False 
   */
  boolean getter() default true;

  /**
   * Flag to use or not DTO Setter
   * 
   * @return True(default) or False 
   */
  boolean setter() default true;
}
