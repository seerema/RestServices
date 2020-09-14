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

package com.seerema.crm.srv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.seerema.base.WsSrvException;
import com.seerema.crm.srv.dto.CustCommHistoryDto;
import com.seerema.crm.srv.jpa.model.CustCommHistory;
import com.seerema.crm.srv.jpa.repo.CustCommHistoryRepo;
import com.seerema.crm.srv.service.CustCommHistoryService;
import com.seerema.crm.srv.shared.ErrorCodes;
import com.seerema.shared.jpa.base.model.User;
import com.seerema.shared.jpa.base.service.impl.AbstractEntityUserServiceImpl;
import com.seerema.shared.jpa.base.service.impl.ServiceData;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Implementation for Contact Service
 */
@Service
public class CustCommHistoryImpl
    extends AbstractEntityUserServiceImpl<CustCommHistory, CustCommHistoryDto>
    implements CustCommHistoryService {

  @Autowired
  private CustCommHistoryRepo _repo;

  @Override
  protected CrudRepository<CustCommHistory, Integer> getEntityRepo() {
    return _repo;
  }

  @Override
  protected Iterable<CustCommHistory> findAll() throws WsSrvException {
    return null;
  }

  @Override
  protected Class<CustCommHistoryDto> getEntityDtoClass() {
    return CustCommHistoryDto.class;
  }

  @Override
  protected String getEntityCreateErrorCode() {
    return ErrorCodes.ERROR_CREATE_CUST_COMM_HISTORY.name();
  }

  @Override
  protected String getEntityReadErrorCode() {
    return ErrorCodes.ERROR_READ_CUST_COMM_HISTORY.name();
  }

  @Override
  protected String getEntitiesReadErrorCode() {
    return ErrorCodes.ERROR_READ_CUST_COMM_HISTORIES.name();
  }

  @Override
  protected String getEntityUpdateErrorCode() {
    return ErrorCodes.ERROR_UPDATE_CUST_COMM_HISTORY.name();
  }

  @Override
  protected String getEntityDeleteErrorCode() {
    return ErrorCodes.ERROR_DELETE_CUST_COMM_HISTORY.name();
  }

  @Override
  public DataGoodResponse createEntity(CustCommHistoryDto entity,
      String userName) throws WsSrvException {
    // Find user
    User user = findUser(userName);

    // Insert user directly into the model since it not mapped automatically
    CustCommHistory model = insertUser(entity, user);

    return new DataGoodResponse(
        getEntityMapper().map(createEntity(model), CustCommHistoryDto.class));
  }

  @Override
  public DataGoodResponse updateEntity(CustCommHistoryDto entity,
      String userName, Boolean allowOverride) throws WsSrvException {
    ServiceData sdata = checkUserAccess(entity, userName, allowOverride);

    // Insert user directly into the model since it not mapped automatically
    CustCommHistory model = insertUser(entity, sdata.getUser());

    return new DataGoodResponse(
        getEntityMapper().map(updateEntity(model), CustCommHistoryDto.class));
  }

  @Override
  public List<CustCommHistoryDto> readMappedCustCommHistories(int entityId)
      throws WsSrvException {
    Iterable<CustCommHistory> entities;

    try {
      entities = _repo.findAllByEntityIdOrderByCreatedDesc(entityId);
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_READ_CUST_ID_COMM_HISTORIES.name(), e);
    }

    return getEntityMapper().map(entities, CustCommHistoryDto.class);
  }

  @Override
  public DataGoodResponse readCustCommHistories(int entityId)
      throws WsSrvException {
    return new DataGoodResponse(readMappedCustCommHistories(entityId));
  }

}
