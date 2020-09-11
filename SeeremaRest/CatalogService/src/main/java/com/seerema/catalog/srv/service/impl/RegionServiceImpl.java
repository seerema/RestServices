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
import com.seerema.catalog.srv.dto.RegionDto;
import com.seerema.catalog.srv.jpa.model.Region;
import com.seerema.catalog.srv.jpa.repo.RegionRepo;
import com.seerema.catalog.srv.shared.ErrorCodes;
import com.seerema.shared.jpa.base.service.impl.AbstractEntityServiceImpl;

/**
 * Implementation for Region Service
 */
@Service
public class RegionServiceImpl
    extends AbstractEntityServiceImpl<Region, RegionDto> {

  @Autowired
  private RegionRepo _repo;

  @Override
  protected CrudRepository<Region, Integer> getEntityRepo() {
    return _repo;
  }

  @Override
  protected Iterable<Region> findAll() throws WsSrvException {
    try {
      return _repo.findAllByOrderByNameAsc();
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_FIND_ALL_REGIONS.name(), e);
    }
  }

  @Override
  protected Class<RegionDto> getEntityDtoClass() {
    return RegionDto.class;
  }

  @Override
  protected String getEntityCreateErrorCode() {
    return ErrorCodes.ERROR_CREATE_REGION.name();
  }

  @Override
  protected String getEntityReadErrorCode() {
    return ErrorCodes.ERROR_READ_REGION.name();
  }

  @Override
  protected String getEntitiesReadErrorCode() {
    return ErrorCodes.ERROR_READ_REGIONS.name();
  }

  @Override
  protected String getEntityUpdateErrorCode() {
    return ErrorCodes.ERROR_UPDATE_REGION.name();
  }

  @Override
  protected String getEntityDeleteErrorCode() {
    return ErrorCodes.ERROR_DELETE_REGION.name();
  }
}
