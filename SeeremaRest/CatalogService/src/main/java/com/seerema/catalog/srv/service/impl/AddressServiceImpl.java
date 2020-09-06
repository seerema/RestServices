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
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.seerema.catalog.srv.dto.AddressDto;
import com.seerema.catalog.srv.jpa.model.Address;
import com.seerema.catalog.srv.jpa.repo.AddressRepo;
import com.seerema.catalog.srv.shared.ErrorCodes;
import com.seerema.shared.jpa.base.service.impl.AbstractEntityServiceImpl;

/**
 * Implementation for Address Service
 */
@Service
public class AddressServiceImpl
    extends AbstractEntityServiceImpl<Address, AddressDto> {

  @Autowired
  private AddressRepo _repo;

  @Override
  protected CrudRepository<Address, Integer> getEntityRepo() {
    return _repo;
  }

  @Override
  protected Iterable<Address> findAll() {
    return _repo.findAllByOrderByLine1Asc();
  }

  @Override
  protected Class<AddressDto> getEntityDtoClass() {
    return AddressDto.class;
  }

  @Override
  protected String getEntityCreateErrorCode() {
    return ErrorCodes.ERROR_CREATE_ADDRESS.name();
  }

  @Override
  protected String getEntityReadErrorCode() {
    return ErrorCodes.ERROR_READ_ADDRESS.name();
  }

  @Override
  protected String getEntitiesReadErrorCode() {
    return ErrorCodes.ERROR_READ_ADDRESSES.name();
  }

  @Override
  protected String getEntityUpdateErrorCode() {
    return ErrorCodes.ERROR_UPDATE_ADDRESS.name();
  }

  @Override
  protected String getEntityDeleteErrorCode() {
    return ErrorCodes.ERROR_DELETE_ADDRESS.name();
  }
}
