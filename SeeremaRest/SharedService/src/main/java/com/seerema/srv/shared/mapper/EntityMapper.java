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

package com.seerema.srv.shared.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.seerema.base.WsSrvException;
import com.seerema.shared.GenericUtils;
import com.seerema.srv.shared.ErrorCodes;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

import net.jodah.typetools.TypeResolver;

/**
 * Mapper for Entity-> Dto conversion
 *
 */
@Component
public class EntityMapper {

  @Autowired
  private Logger log;

  /**
   * Convert Dto object into model
   * 
   * @param <T> Type of output model
   * @param dto Input DTO object
   * @param entity Type of output model
   * @return The model object
   * 
   * @throws WsSrvException
   */
  public <T> T convert(Object dto, Class<T> entity) throws WsSrvException {
    // Quick check
    if (dto == null)
      return null;

    if (!dto.getClass().isAnnotationPresent(DtoFor.class))
      throw new WsSrvException(ErrorCodes.MISSING_DTO_TYPE_ANNOTATION.name(),
          "Class " + dto.getClass().getSimpleName() +
              " missing @DtoFrom annotation.");

    Class<?> src = dto.getClass();
    Class<?> dst = src.getAnnotation(DtoFor.class).value();

    // Check if entity of same class
    if (!dst.equals(entity))
      throw new WsSrvException(ErrorCodes.INVALID_INPUT_ENTITY.name(),
          "Input entity of type " + src.getSimpleName() +
              " differs from expected " + entity.getSimpleName() +
              " defined in @DtoFrom annotation.");

    T result;

    try {
      // Create instance of destination class
      result = entity.getConstructor().newInstance();

      Class<?> clazz = src;
      while (!clazz.equals(Object.class)) {
        // Check for @ModelItem annotated fields
        for (Field field : clazz.getDeclaredFields()) {
          if (field.isAnnotationPresent(ModelItem.class) &&
              // Only analyze getter flag when converting from Dto -> Entity
              field.getAnnotation(ModelItem.class).getter()) {

            // Don't process primitive fields
            if (field.getType().isPrimitive())
              throw new WsSrvException(ErrorCodes.PRIMITIVE_DATATYPE.name(),
                  "Detect primitive field " + src.getSimpleName() + "." +
                      field.getName());

            // Look for public getter for source field name
            Method m = BeanUtils.findMethod(src,
                "get" + StringUtils.capitalize(field.getName()));

            if (m != null) {
              Object value = m.invoke(dto);

              if (value == null)
                continue;

              // Read dest field name
              String dname = field.getAnnotation(ModelItem.class).value();
              if (dname.equals(""))
                dname = field.getName();

              // Find recursively destination field
              Field dfield = null;
              Class<?> dest = entity;

              while (!dest.equals(Object.class) && dfield == null) {
                // Read destination field
                try {
                  dfield = dest.getDeclaredField(dname);
                } catch (NoSuchFieldException e) {
                  dest = dest.getSuperclass();
                }
              }

              if (dfield == null)
                throw new WsSrvException(ErrorCodes.MISSING_OUTPUT_FIELD.name(),
                    "Unable find output field " + entity.getSimpleName() + "." +
                        dname);

              Class<?> dclass = dfield.getType();
              Class<?> dtype = null;

              // Check for List<> values
              if (List.class.isInstance(value)) {
                // Process each item from the list individually
                List<?> values = (List<?>) value;
                List<Object> list = new ArrayList<>();

                // Destination class for each list item
                Class<?> dclazz = null;

                for (Object item : values) {
                  if (dclazz == null) {
                    // Type of destination list
                    ParameterizedType ltype =
                        (ParameterizedType) dfield.getGenericType();

                    Type actual = ltype.getActualTypeArguments()[0];
                    boolean fgen = actual instanceof TypeVariable<?>;

                    // Check if type is generic
                    if (fgen) {
                      Class<?> iclass = item.getClass();
                      // Find destination class from DtoFor annotation
                      if (!iclass.isAnnotationPresent(DtoFor.class))
                        throw new WsSrvException(
                            ErrorCodes.MISSING_LIST_ITEM_DTO_TYPE_ANNOTATION
                                .name(),
                            "List item class " + iclass.getSimpleName() +
                                " missing @DtoFrom annotation.");
                      dclazz = iclass.getAnnotation(DtoFor.class).value();
                    } else {
                      dclazz = (Class<?>) actual;
                    }
                  }

                  // Go recursive
                  list.add(convert(item, dclazz));
                }

                value = list;
              } else if (!value.getClass().equals(dfield.getType()) &&
                  value.getClass().isAnnotationPresent(DtoFor.class)) {

                // Try finding class that masking DtoFor spec
                Class<?> dfor =
                    value.getClass().getAnnotation(DtoFor.class).value();

                Class<?> dc = dfor;
                Class<?> dfound = null;
                while (!dc.equals(Object.class)) {
                  if (dc.equals(dclass)) {
                    dfound = dc;
                    break;
                  }
                  dc = dc.getSuperclass();
                }

                if (dfound != null) {
                  dtype = dfound;
                  value = convert(value, dfor);
                }
              }

              if (!GenericUtils.setFieldValue(entity, dfield, result, value,
                  dtype, log))
                throw new WsSrvException(ErrorCodes.SET_DTO_FIELD_FAIL.name(),
                    "Error set field " + entity.getSimpleName() + "." +
                        dfield.getName() + " of type " +
                        dfield.getType().getSimpleName() +
                        " with value of type " +
                        value.getClass().getSimpleName());
            } else {
              throw new WsSrvException(ErrorCodes.MISSING_INPUT_GETTER.name(),
                  "Unable find getter for field " + src.getSimpleName() + "." +
                      field.getName());
            }
          }
        }

        clazz = clazz.getSuperclass();
      }
    } catch (NoSuchMethodException | InstantiationException
        | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | SecurityException e) {
      throw new WsSrvException(ErrorCodes.DTO_CONVERT_ERROR.name(), e);
    }

    return result;
  }

  /**
   * Map Entity object into the dto class
   * 
   * @param <T> Type of destination object
   * @param entity Source object
   * @param dto Type of destination object
   * @return Destination object
   * 
   * @throws WsSrvException
   */
  public <T> T map(Object entity, Class<T> dto) throws WsSrvException {
    // Quick check
    if (entity == null)
      return null;

    if (!dto.isAnnotationPresent(DtoFor.class))
      throw new WsSrvException(ErrorCodes.MISSING_DTO_TYPE_ANNOTATION.name(),
          "Class " + dto.getSimpleName() + " missing @DtoFrom annotation.");

    Class<?> src = dto.getAnnotation(DtoFor.class).value();

    // Check if entity of same class
    if (!entity.getClass().equals(src))
      throw new WsSrvException(ErrorCodes.INVALID_INPUT_ENTITY.name(),
          "Input entity of type " + entity.getClass().getSimpleName() +
              " differs from expected " + src.getSimpleName() +
              " defined in @DtoFrom annotation.");

    T result;

    try {
      // Create instance of destination class
      result = dto.getConstructor().newInstance();

      Class<?> clazz = dto;
      while (!clazz.equals(Object.class)) {
        // Check for @ModelItem annotated fields
        for (Field field : clazz.getDeclaredFields()) {
          // Ensure the RetentionPolicy of 'PropertyItem' is RUNTIME.
          if (field.isAnnotationPresent(ModelItem.class) &&
              // Only analyze setter flag when mapping from Entity -> Dto
              field.getAnnotation(ModelItem.class).setter()) {
            // Read source field name
            String fname = field.getAnnotation(ModelItem.class).value();
            if (fname.equals(""))
              fname = field.getName();

            // Look for public getter in source class
            Method m = BeanUtils.findMethod(src,
                "get" + StringUtils.capitalize(fname));

            if (m != null) {
              Object value = m.invoke(entity);

              // Check for some non-standard situations
              if (value == null)
                continue;

              // Check if field is generic
              boolean fgen = field.getGenericType() instanceof TypeVariable<?>;

              Class<?> fclass = field.getType();
              if (fgen)
                fclass = resolveGenericClass(fclass, clazz, dto);

              // Define the type of variable for setter to look up
              Class<?> ftype = fgen ? field.getType() : null;

              // Check for List<> values
              if (List.class.isInstance(value)) {

                // Process each item from the list individually
                List<?> values = (List<?>) value;
                List<Object> list = new ArrayList<>();

                Class<?> dclazz = null;
                for (Object item : values) {
                  // All elements in array of same type. Only need detect type once
                  if (dclazz == null) {
                    // Type of destination list
                    ParameterizedType ltype =
                        (ParameterizedType) field.getGenericType();
                    Type actual = ltype.getActualTypeArguments()[0];

                    fgen = actual instanceof TypeVariable<?>;

                    // Check if type is generic
                    if (fgen) {
                      dclazz =
                          resolveGenericListClass(item.getClass(), clazz, dto);
                    } else {
                      dclazz = (Class<?>) actual;
                    }
                  }

                  if (dclazz == null)
                    throw new WsSrvException(
                        ErrorCodes.UNABLE_MAP_LIST_ITEM.name(),
                        "Unable find class to map for list item of type " +
                            item.getClass().getSimpleName());
                  // Go recursive
                  list.add(map(item, dclazz));
                }

                value = list;
              } else if (!value.getClass().equals(field.getType()) &&
                  fclass.isAnnotationPresent(DtoFor.class) &&
                  fclass.getAnnotation(DtoFor.class).value()
                      .equals(value.getClass())) {
                // Value needs to be map itself before set to destination field
                value = map(value, fclass);
              }

              if (!GenericUtils.setFieldValue(dto, field, result, value, ftype,
                  log))
                throw new WsSrvException(ErrorCodes.SET_DTO_FIELD_FAIL.name(),
                    "Error set field " + dto.getSimpleName() + "." +
                        field.getName() + " of type " + fclass.getSimpleName() +
                        " with value of type " +
                        value.getClass().getSimpleName());
            } else {
              throw new WsSrvException(
                  ErrorCodes.MISSING_MODEL_INPUT_GETTER.name(),
                  "Unable find getter for field " + src.getSimpleName() + "." +
                      fname);
            }
          }
        }

        clazz = clazz.getSuperclass();
      }
    } catch (NoSuchMethodException | InstantiationException
        | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | SecurityException e) {
      throw new WsSrvException(ErrorCodes.DTO_MAP_ERROR.name(), e);
    }

    return result;
  }

  private Class<?> resolveGenericClass(Class<?> flass, Type gtype,
      Class<?> stype) {
    // Could be more than one generic type
    Class<?>[] fclasses = TypeResolver.resolveRawArguments(gtype, stype);
    for (Class<?> fc : fclasses) {
      Class<?> vclass = fc;

      while (!vclass.equals(Object.class)) {
        // Find which class it's inherited from
        if (flass.equals(vclass))
          return fc;
        vclass = vclass.getSuperclass();
      }
    }

    return flass;
  }

  private Class<?> resolveGenericListClass(Class<?> flass, Type gtype,
      Class<?> stype) {
    // Could be more than one generic type
    Class<?>[] fclasses = TypeResolver.resolveRawArguments(gtype, stype);
    for (Class<?> fc : fclasses) {
      // Process only classes with DtoFor annotation
      if (!fc.isAnnotationPresent(DtoFor.class))
        continue;

      Class<?> vclass = fc.getAnnotation(DtoFor.class).value();

      while (!vclass.equals(Object.class)) {
        // Find which class it's inherited from
        if (flass.equals(vclass))
          return fc;
        vclass = vclass.getSuperclass();
      }
    }

    return flass;
  }

  /**
   * Map source list into destination list of objects
   * 
   * @param <T> Type of destination object
   * @param list Source list
   * @param dto Type of destination object
   * @return List of destination objects
   * @throws WsSrvException
   */
  public <T> List<T> map(Iterable<?> list, Class<T> dto) throws WsSrvException {
    // Quick check
    if (list == null)
      return null;

    // Create destination list
    List<T> result = new ArrayList<T>();

    // Map one by one
    for (Object entity : list)
      result.add(map(entity, dto));

    return result;
  }

  public void setLog(Logger log) {
    this.log = log;
  }
}
