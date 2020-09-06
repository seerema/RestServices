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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

/**
 * Class with static Generic File utilities
 * 
 */
public class GenericUtils {

  // End Of File
  static final int EOF = -1;

  // Buffer size for I/O operations
  static final int DEFAULT_BUFFER_SIZE = 1024 * 8;

  /**
   * Set field value
   * 
   * @param clazz Field class
   * @param field Field
   * @param obj Destination object where field must be set
   * @param value Source value
   * @param vcz Source value class (optional)
   * @param log Logger
   * @return
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   * @throws SecurityException
   * @throws InstantiationException
   */
  public static boolean setFieldValue(Class<?> clazz, Field field, Object obj,
      Object value, Class<?> vcz, Logger log) throws IllegalAccessException,
      IllegalArgumentException, InvocationTargetException,
      NoSuchMethodException, SecurityException, InstantiationException {
    if (value != null) {
      Method m = BeanUtils.findMethod(clazz,
          "set" + StringUtils.capitalize(field.getName()),
          new Class<?>[] { vcz != null ? vcz : value.getClass() });

      if (m != null) {
        m.invoke(obj, value);
      } else {
        // Try use interface if value class inherited any
        Class<?>[] interfaces = value.getClass().getInterfaces();

        if (interfaces.length != 0) {
          for (Class<?> ifs : interfaces)
            // Go to recursion but dictate this time the setter parameter type
            if (setFieldValue(clazz, field, obj, value, ifs, log))
              return true;

          log.error("Unable find setter with type " + value.getClass() +
              " for field '" + field.getName());
          return false;
        } else {
          log.error("Unable find any implemented interfaces for type " +
              value.getClass() + "," +
              " Trying find constructor to intialize field directly");

          return false;

          // Edge scenario that needs to be avoided
          // setFieldValue(field, value.toString(), obj);
        }
      }
    }

    return true;
  }

  /**
   * Dynamically set field value as string
   * 
   * @param field
   *          Field to set
   * @param value
   *          Value to set
   * @throws SecurityException
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   */
  /*
  private static void setFieldValue(Field field, String value, Object obj)
      throws NoSuchMethodException, SecurityException, IllegalArgumentException,
      IllegalAccessException, InstantiationException,
      InvocationTargetException {
    Constructor<?> co = field.getType().getConstructor(String.class);
    field.set(obj, co.newInstance(value));
  }
  */
}
