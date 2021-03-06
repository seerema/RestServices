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

package com.seerema.shared.jpa.status.service;

import com.seerema.base.WsSrvException;
import com.seerema.shared.dto.AbstractEntityDto;
import com.seerema.shared.jpa.base.model.BaseEntity;
import com.seerema.shared.jpa.base.service.BaseEntityUserService;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * Abstract Entity service interface
 *
 * @param <T1> Entity
 * @param <T2> EntityDto
 */
public interface EntityStatusService<T1 extends BaseEntity, T2 extends AbstractEntityDto>
    extends BaseEntityUserService<T1, T2> {

  DataGoodResponse readEntity(Integer id, String userName)
      throws WsSrvException;

  DataGoodResponse readEntities(String userName) throws WsSrvException;
}
