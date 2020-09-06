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

package com.seerema.shared.jpa.base.repo;

import org.springframework.stereotype.Repository;

import com.seerema.shared.jpa.base.model.Module;

@Repository
public interface ModuleRepo
    extends org.springframework.data.repository.Repository<Module, Integer> {

  Module findByName(String name);
}
