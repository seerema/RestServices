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
import com.seerema.catalog.srv.jpa.repo.CommMediaRepo;
import com.seerema.catalog.srv.shared.ErrorCodes;
import com.seerema.shared.dto.CommMediaDto;
import com.seerema.shared.jpa.base.model.CommMedia;
import com.seerema.shared.jpa.base.service.impl.AbstractEntityServiceImpl;

/**
 * Implementation for CommMedia Service
 */
@Service
public class CommMediaServiceImpl
    extends AbstractEntityServiceImpl<CommMedia, CommMediaDto> {

  @Autowired
  private CommMediaRepo _repo;

  @Override
  protected CrudRepository<CommMedia, Integer> getEntityRepo() {
    return _repo;
  }

  @Override
  protected Iterable<CommMedia> findAll() throws WsSrvException {
    try {
      return _repo.findAllByOrderByNameAsc();
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_FIND_ALL_COMM_MEDIAS.name(), e);
    }
  }

  @Override
  protected Class<CommMediaDto> getEntityDtoClass() {
    return CommMediaDto.class;
  }

  @Override
  protected String getEntityCreateErrorCode() {
    return ErrorCodes.ERROR_CREATE_COMM_MEDIA.name();
  }

  @Override
  protected String getEntityReadErrorCode() {
    return ErrorCodes.ERROR_READ_COMM_MEDIA.name();
  }

  @Override
  protected String getEntitiesReadErrorCode() {
    return ErrorCodes.ERROR_READ_COMM_MEDIAS.name();
  }

  @Override
  protected String getEntityUpdateErrorCode() {
    return ErrorCodes.ERROR_UPDATE_COMM_MEDIA.name();
  }

  @Override
  protected String getEntityDeleteErrorCode() {
    return ErrorCodes.ERROR_DELETE_COMM_MEDIA.name();
  }
}
