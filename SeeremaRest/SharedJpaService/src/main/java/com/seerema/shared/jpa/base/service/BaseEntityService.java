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
import com.seerema.shared.rest.response.BaseGoodResponse;
import com.seerema.shared.rest.response.DataGoodResponse;

/**
 * BaseEntity service interface
 *
 * @param <T1> Entity
 * @param <T2> EntityDto
 */
public interface BaseEntityService<T1 extends BaseEntity, T2 extends AbstractEntityDto> {

  public T1 createEntity(T1 entity) throws WsSrvException;

  public DataGoodResponse createEntity(T2 entity) throws WsSrvException;

  /**
   * Read raw entity by id
   * 
   * @param id
   * @return Entity
   * @throws WsSrvException
   */
  public T1 readRawEntity(Integer id) throws WsSrvException;

  /**
   * Read entity by id
   * 
   * @param id
   * @return Entity
   * @throws WsSrvException
   */
  public DataGoodResponse readEntity(Integer id) throws WsSrvException;

  /**
   * Read all entities
   * 
   * @return All Entities
   * @throws WsSrvException
   */
  public DataGoodResponse readEntities() throws WsSrvException;

  public T1 updateEntity(T1 entity) throws WsSrvException;

  public DataGoodResponse updateEntity(T2 entity) throws WsSrvException;

  public BaseGoodResponse deleteEntity(Integer id) throws WsSrvException;
}
