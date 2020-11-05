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

package com.seerema.shared.jpa.status.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.seerema.base.WsSrvException;
import com.seerema.shared.dto.StatusDto;
import com.seerema.shared.jpa.base.service.impl.BaseDbAccessService;
import com.seerema.shared.jpa.status.model.Status;
import com.seerema.shared.jpa.status.repo.StatusRepo;
import com.seerema.shared.jpa.status.service.StatusService;
import com.seerema.shared.jpa.status.shared.ErrorCodes;
import com.seerema.shared.rest.response.DataGoodResponse;
import com.seerema.srv.shared.mapper.EntityMapper;

/**
 * Status read only service
 */

public abstract class AbstractStatusServiceImpl extends BaseDbAccessService
    implements StatusService {

  @Autowired
  private StatusRepo _repo;

  protected abstract int getModuleId();

  @Autowired
  private EntityMapper _mapper;

  @Override
  public DataGoodResponse readStatuses() throws WsSrvException {
    Iterable<Status> statuses;

    try {
      statuses = _repo.findAllByModuleIdOrderByIdAsc(getModuleId());
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_READ_STATUS.name(), e);
    }

    return new DataGoodResponse(_mapper.map(statuses, StatusDto.class));
  }
}
