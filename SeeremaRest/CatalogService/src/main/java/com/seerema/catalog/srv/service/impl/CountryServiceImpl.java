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
import com.seerema.catalog.srv.dto.CountryDto;
import com.seerema.catalog.srv.jpa.model.Country;
import com.seerema.catalog.srv.jpa.repo.CountryRepo;
import com.seerema.catalog.srv.shared.ErrorCodes;
import com.seerema.shared.jpa.base.service.impl.AbstractEntityServiceImpl;

/**
 * Implementation for CountryService
 */
@Service
public class CountryServiceImpl
    extends AbstractEntityServiceImpl<Country, CountryDto> {

  @Autowired
  private CountryRepo _repo;

  @Override
  protected CrudRepository<Country, Integer> getEntityRepo() {
    return _repo;
  }

  @Override
  protected Iterable<Country> findAll() throws WsSrvException {
    try {
      return _repo.findAllByOrderByNameAsc();
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_FIND_ALL_COUNTRIES.name(), e);
    }
  }

  @Override
  protected Class<CountryDto> getEntityDtoClass() {
    return CountryDto.class;
  }

  @Override
  protected String getEntityCreateErrorCode() {
    return ErrorCodes.ERROR_CREATE_COUNTRY.name();
  }

  @Override
  protected String getEntityReadErrorCode() {
    return ErrorCodes.ERROR_READ_COUNTRY.name();
  }

  @Override
  protected String getEntitiesReadErrorCode() {
    return ErrorCodes.ERROR_READ_COUNTRIES.name();
  }

  @Override
  protected String getEntityUpdateErrorCode() {
    return ErrorCodes.ERROR_UPDATE_COUNTRY.name();
  }

  @Override
  protected String getEntityDeleteErrorCode() {
    return ErrorCodes.ERROR_DELETE_COUNTRY.name();
  }
}
