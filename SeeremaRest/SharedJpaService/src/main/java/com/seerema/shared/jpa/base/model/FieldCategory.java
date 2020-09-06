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

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The persistent class for the field_category database table.
 */

@Entity
@Table(name = "field_category", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "module_id", "name" }) })
public class FieldCategory extends AbstractEntityModule
    implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "is_system")
  private String isSystem;

  @Column(nullable = false, unique = true)
  private String name;

  // bi-directional many-to-one association to Contact
  @OneToMany(mappedBy = "fieldCat")
  private List<DbEntity> entities;

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

  public List<DbEntity> getEntities() {
    return entities;
  }

  public void setEntities(List<DbEntity> entities) {
    this.entities = entities;
  }
}