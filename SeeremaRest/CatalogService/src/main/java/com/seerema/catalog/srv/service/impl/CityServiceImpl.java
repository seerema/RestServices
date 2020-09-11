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

package com.seerema.catalog.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.seerema.base.WsSrvException;
import com.seerema.catalog.srv.dto.CityDto;
import com.seerema.catalog.srv.jpa.model.City;
import com.seerema.catalog.srv.jpa.repo.CityRepo;
import com.seerema.catalog.srv.shared.ErrorCodes;
import com.seerema.shared.jpa.base.service.impl.AbstractEntityServiceImpl;

/**
 * Implementation for City Service
 */
@Service
public class CityServiceImpl extends AbstractEntityServiceImpl<City, CityDto> {

  @Autowired
  private CityRepo _repo;

  @Override
  protected CrudRepository<City, Integer> getEntityRepo() {
    return _repo;
  }

  @Override
  protected Iterable<City> findAll() throws WsSrvException {
    try {
      return _repo.findAllByOrderByNameAsc();
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_FIND_ALL_CITIES.name(), e);
    }
  }

  @Override
  protected Class<CityDto> getEntityDtoClass() {
    return CityDto.class;
  }

  @Override
  protected String getEntityCreateErrorCode() {
    return ErrorCodes.ERROR_CREATE_CITY.name();
  }

  @Override
  protected String getEntityReadErrorCode() {
    return ErrorCodes.ERROR_READ_CITY.name();
  }

  @Override
  protected String getEntitiesReadErrorCode() {
    return ErrorCodes.ERROR_READ_CITIES.name();
  }

  @Override
  protected String getEntityUpdateErrorCode() {
    return ErrorCodes.ERROR_UPDATE_CITY.name();
  }

  @Override
  protected String getEntityDeleteErrorCode() {
    return ErrorCodes.ERROR_DELETE_CITY.name();
  }
}
