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
import com.seerema.shared.jpa.base.model.AbstractEntity;

/**
 * Abstract Entity service interface
 *
 * @param <T1> Entity
 * @param <T2> EntityDto
 */
public interface EntityService<T1 extends AbstractEntity, T2 extends AbstractEntityDto>
    extends BaseEntityService<T1, T2> {

  public T1 createRawEntity(T1 entity) throws WsSrvException;
}
