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

package com.seerema.shared.jpa.base.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Abstract Entity with Name & System 
 */

@MappedSuperclass
public class AbstractEntitySysName extends AbstractEntity {

  @Column(name = "is_system")
  private String isSystem;

  @Column(nullable = false, unique = true)
  private String name;

  public String getIsSystem() {
    return this.isSystem;
  }

  public void setIsSystem(String isSystem) {
    this.isSystem = isSystem;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
