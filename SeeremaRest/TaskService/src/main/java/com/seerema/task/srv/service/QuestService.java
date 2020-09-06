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

package com.seerema.task.srv.service;

import com.seerema.base.WsSrvException;
import com.seerema.shared.dto.EntityExDto;
import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.shared.jpa.status.service.EntityStatusService;
import com.seerema.shared.rest.response.DataGoodResponse;

public interface QuestService
    extends EntityStatusService<EntityEx, EntityExDto> {

  DataGoodResponse readUserActiveQuests(String username) throws WsSrvException;

  DataGoodResponse readAllActiveQuests() throws WsSrvException;
}
