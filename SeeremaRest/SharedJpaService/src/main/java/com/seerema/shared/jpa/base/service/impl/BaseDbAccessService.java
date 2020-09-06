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

package com.seerema.shared.jpa.base.service.impl;

import org.hibernate.exception.DataException;
import org.springframework.dao.DataAccessException;

import com.seerema.base.WsSrvException;
import com.seerema.shared.jpa.base.shared.ErrorCodes;
import com.seerema.srv.shared.annotations.DtoFor;

/**
 * Base DB Access exception
 *
 */
public class BaseDbAccessService {

  public WsSrvException throwError(String error, DataAccessException e) {
    Throwable t = null;

    if (e.getCause() != null) {
      if (e.getCause().getClass().equals(DataException.class)) {
        DataException dex = (DataException) e.getCause();
        t = dex.getSQLException();
      }
    }

    return t != null ? new WsSrvException(error, t)
        : new WsSrvException(error, e);
  }

  protected Class<?> getDtoDestClass(Object dto) throws WsSrvException {
    Class<?> clazz = dto.getClass();
    // Quick check for correct DTO annotation
    if (!clazz.isAnnotationPresent(DtoFor.class))
      throw new WsSrvException(ErrorCodes.INVALID_DTO.name(),
          "Dto class " + clazz.getSimpleName() + " missing DtoFor annotation.");

    return clazz.getAnnotation(DtoFor.class).value();

  }
}
