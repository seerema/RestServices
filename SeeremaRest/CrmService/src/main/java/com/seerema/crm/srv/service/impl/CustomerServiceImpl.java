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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.seerema.base.WsSrvException;
import com.seerema.crm.srv.dto.CustCommHistoryDto;
import com.seerema.crm.srv.dto.CustomerDto;
import com.seerema.crm.srv.jpa.repo.CustomerRepo;
import com.seerema.crm.srv.service.CustCommHistoryService;
import com.seerema.crm.srv.service.CustomerService;
import com.seerema.crm.srv.shared.CrmConstants;
import com.seerema.crm.srv.shared.ErrorCodes;
import com.seerema.shared.dto.EntityDto;
import com.seerema.shared.dto.EntityExDto;
import com.seerema.shared.dto.ModuleDto;
import com.seerema.shared.jpa.base.model.DbEntity;
import com.seerema.shared.jpa.base.service.EntityService;
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

  @Autowired
  private CustCommHistoryService _hsrv;

  @Autowired
  @Qualifier("mod_" + CrmConstants.MODULE_NAME)
  private ModuleDto _mod;

  @Autowired
  @Qualifier("entity_" + CrmConstants.MODULE_NAME)
  private EntityService<DbEntity, EntityDto> _srv;

  @Override
  public DataGoodResponse findUserLeads(String username) throws WsSrvException {
    Iterable<EntityEx> list;

    try {
      list = _repo.findUserLeads(CrmConstants.CRM_CONTACT_STATUSES, username);
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_READ_USER_CONTACTS.name(), e);
    }

    return new DataGoodResponse(
        attachCommHistory(getEntityMapper().map(list, getEntityDtoClass())));
  }

  @Override
  public DataGoodResponse findUserCustomers(String username)
      throws WsSrvException {
    Iterable<EntityEx> list;

    try {
      list =
          _repo.findUserCustomers(CrmConstants.CRM_CONTACT_STATUSES, username);
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_READ_USER_CLIENTS.name(), e);
    }

    return new DataGoodResponse(
        attachCommHistory(getEntityMapper().map(list, getEntityDtoClass())));
  }

  @Override
  protected String getNewStatus() {
    return CrmConstants.CRM_NEW_STATUS;
  }

  @Override
  public List<EntityExDto> readMappedEntities() throws WsSrvException {
    return attachCommHistory(super.readMappedEntities());
  }

  @Override
  protected List<EntityExDto> readMappedEntities(String username)
      throws WsSrvException {
    return attachCommHistory(super.readMappedEntities(username));
  }

  private List<EntityExDto> attachCommHistory(List<EntityExDto> list)
      throws WsSrvException {
    // Quick check
    if (list == null)
      return null;

    List<EntityExDto> result = new ArrayList<>();

    for (EntityExDto dto : list) {
      CustomerDto cdto = new CustomerDto(dto);
      result.add(cdto);

      // Retrieve communication list (if any)
      List<CustCommHistoryDto> hlist =
          _hsrv.readMappedCustCommHistories(dto.getId());

      if (hlist == null)
        continue;

      cdto.setCustCommHistories(hlist);
    }

    return result;
  }

  @Override
  protected int getModuleId() {
    return _mod.getId();
  }

  @Override
  protected EntityService<DbEntity, EntityDto> getEntityService() {
    return _srv;
  }
}
