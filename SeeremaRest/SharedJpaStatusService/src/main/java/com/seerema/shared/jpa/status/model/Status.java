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

package com.seerema.shared.jpa.status.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.seerema.shared.jpa.base.model.AbstractEntityModule;

/**
 * The persistent class for the status database table.
 */

@Entity
@Table(name = "status", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "module_id", "name" }) })
public class Status extends AbstractEntityModule implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "is_system")
  private String isSystem;

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
