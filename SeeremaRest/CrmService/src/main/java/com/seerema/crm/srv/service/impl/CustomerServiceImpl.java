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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.seerema.base.WsSrvException;
import com.seerema.crm.srv.jpa.repo.CustomerRepo;
import com.seerema.crm.srv.service.CustomerService;
import com.seerema.crm.srv.shared.CrmConstants;
import com.seerema.crm.srv.shared.ErrorCodes;
import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.shared.jpa.status.service.impl.AbstractEntityStatusServiceImpl;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Implementation for Contact Service
 */
@Service
public class CustomerServiceImpl extends AbstractEntityStatusServiceImpl
    implements CustomerService {

  @Autowired
  private CustomerRepo _repo;

  @Override
  public DataGoodResponse findUserLeads(String username)
      throws WsSrvException {
    Iterable<EntityEx> quests;

    try {
      quests =
          _repo.findUserLeads(CrmConstants.CRM_CONTACT_STATUSES, username);
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_READ_USER_CONTACTS.name(), e);
    }

    return new DataGoodResponse(
        getEntityMapper().map(quests, getEntityDtoClass()));
  }

  @Override
  public DataGoodResponse findUserCustomers(String username)
      throws WsSrvException {
    Iterable<EntityEx> quests;

    try {
      quests =
          _repo.findUserCustomers(CrmConstants.CRM_CONTACT_STATUSES, username);
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_READ_USER_CLIENTS.name(), e);
    }

    return new DataGoodResponse(
        getEntityMapper().map(quests, getEntityDtoClass()));
  }

  @Override
  protected String getNewStatus() {
    return CrmConstants.CRM_NEW_STATUS;
  }

}