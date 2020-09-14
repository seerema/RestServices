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

package com.seerema.crm.srv.service;

import java.util.List;

import com.seerema.base.WsSrvException;
import com.seerema.crm.srv.dto.CustCommHistoryDto;
import com.seerema.crm.srv.jpa.model.CustCommHistory;
import com.seerema.shared.jpa.base.service.BaseEntityUserService;
import com.seerema.shared.rest.response.DataGoodResponse;

public interface CustCommHistoryService
    extends BaseEntityUserService<CustCommHistory, CustCommHistoryDto> {

  List<CustCommHistoryDto> readMappedCustCommHistories(int entityId)
      throws WsSrvException;

  DataGoodResponse readCustCommHistories(int entityId) throws WsSrvException;
}
