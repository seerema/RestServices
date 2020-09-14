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

package com.seerema.shared.jpa.base.service;

import com.seerema.base.WsSrvException;
import com.seerema.shared.dto.AbstractEntityDto;
import com.seerema.shared.jpa.base.model.BaseEntity;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * BaseEntity service interface
 *
 * @param <T1> Entity
 * @param <T2> EntityDto
 */
public interface BaseEntityUserService<T1 extends BaseEntity, T2 extends AbstractEntityDto>
    extends BaseEntityService<T1, T2> {

  DataGoodResponse createEntity(T2 entity, String userName)
      throws WsSrvException;

  DataGoodResponse updateEntity(T2 entity, String userName,
      Boolean allowOverride) throws WsSrvException;
}
