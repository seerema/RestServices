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

package com.seerema.shared.jpa.status.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seerema.shared.jpa.status.model.EntityEx;

@Repository
public interface EntityExRepo extends CrudRepository<EntityEx, Integer> {

  Iterable<EntityEx> findAllByDbEntityModuleId(int moduleId);

  Optional<EntityEx> findByIdAndUserName(Integer id, String username);

  Iterable<EntityEx> findAllByUserName(String username);
}
