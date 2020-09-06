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

package com.seerema.task.srv.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.task.srv.shared.TaskConstants;

@Repository
public interface QuestRepo extends CrudRepository<EntityEx, Integer> {

  @Query("SELECT e FROM EntityEx e, Status s, Module m WHERE m.name = '" +
      TaskConstants.MODULE_NAME + "' AND s.module = m AND " +
      "s.name IN ?1 AND e.status = s AND e.userName = ?2 ORDER BY e.dbEntity.name")
  Iterable<EntityEx> findUserActiveQuests(List<String> statuses,
      String userName);

  @Query("SELECT e FROM EntityEx e, Status s, Module m WHERE m.name = '" +
      TaskConstants.MODULE_NAME + "' AND s.module = m AND " +
      "s.name IN ?1 AND e.status = s ORDER BY e.dbEntity.name")
  Iterable<EntityEx> findAllActiveQuests(List<String> statuses);
}
