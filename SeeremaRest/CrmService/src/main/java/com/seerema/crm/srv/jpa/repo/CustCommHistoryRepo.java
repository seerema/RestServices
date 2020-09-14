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

package com.seerema.crm.srv.jpa.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seerema.crm.srv.jpa.model.CustCommHistory;

@Repository
public interface CustCommHistoryRepo
    extends CrudRepository<CustCommHistory, Integer> {

  Iterable<CustCommHistory> findAllByEntityIdOrderByCreatedDesc(int entityId);
}
