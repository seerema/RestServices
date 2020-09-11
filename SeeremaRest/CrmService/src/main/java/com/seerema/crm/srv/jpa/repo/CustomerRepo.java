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

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seerema.crm.srv.shared.CrmConstants;
import com.seerema.shared.jpa.status.model.EntityEx;

@Repository
public interface CustomerRepo extends CrudRepository<EntityEx, Integer> {

  @Query("SELECT e FROM EntityEx e, Status s, Module m, User u WHERE m.name = '" +
      CrmConstants.MODULE_NAME + "' AND s.module = m AND " +
      "s.name IN ?1 AND e.status = s AND e.user = u AND u.name = ?2 ORDER BY e.dbEntity.name")
  Iterable<EntityEx> findUserLeads(List<String> statuses, String userName);

  @Query("SELECT e FROM EntityEx e, Status s, Module m, User u WHERE m.name = '" +
      CrmConstants.MODULE_NAME + "' AND s.module = m AND " +
      "s.name NOT IN ?1 AND e.status = s AND e.user = u AND u.name = ?2 ORDER BY e.dbEntity.name")
  Iterable<EntityEx> findUserCustomers(List<String> statuses, String userName);
}
