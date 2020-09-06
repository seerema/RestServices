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

package com.seerema.srv.shared.dto;

import java.lang.reflect.Field;
import java.util.List;

import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

/**
 * Base DTO class
 *
 */
public class BaseDto {

  @Override
  public String toString() {
    return collect(this);
  }

  private String collect(Object value) {
    String result = "";

    Class<?> clazz = value.getClass();

    // Traverse through all @MapItem field and collect result
    while (!clazz.equals(Object.class)) {
      // Check for @ModelItem annotated fields
      for (Field field : clazz.getDeclaredFields()) {
        // Ensure the RetentionPolicy of 'PropertyItem' is RUNTIME.
        if (field.isAnnotationPresent(ModelItem.class)) {

          field.setAccessible(true);
          try {
            Object obj = field.get(value);

            // Check if field itself is a dto
            Class<?> ftype = field.getType();
            boolean fdto = ftype.isAnnotationPresent(DtoFor.class);

            // Check for some complications
            if (value != null) {
              if (fdto)
                obj = collect(obj);

              // Process array
              if (List.class.isInstance(obj)) {
                @SuppressWarnings("rawtypes")
                List list = (List) obj;

                String sl = "";
                for (Object item : list)
                  sl += "," + collect(item);

                obj = "[" + (sl.equals("") ? "" : sl.substring(1)) + "]";
              }
            }

            result += ";" + field.getName() + ":" + (fdto ? "{" : "") +
                (obj != null ? obj.toString() : "null") + (fdto ? "}" : "");

          } catch (Exception e) {
            return e.getMessage();
          }
        }
      }

      clazz = clazz.getSuperclass();
    }

    return result.equals("") ? "" : result.substring(1);
  }
}
