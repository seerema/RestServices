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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The persistent class for the field database table.
 */

@Entity
@Table(name = "field", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "field_category_id", "name" }) })
public class Field extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "is_system")
  private String isSystem;

  @Column(nullable = false)
  private String name;

  // bi-directional many-to-one association to FieldCategory
  @ManyToOne(optional = false)
  @JoinColumn(name = "field_category_id")
  private FieldCategory fieldCat;

  // bi-directional many-to-one association to EntityField
  @OneToMany(mappedBy = "field")
  private List<EntityField> entityFields;

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

  public FieldCategory getFieldCat() {
    return fieldCat;
  }

  public void setFieldCat(FieldCategory fieldCat) {
    this.fieldCat = fieldCat;
  }

  public List<EntityField> getEntityFields() {
    return entityFields;
  }

  public void setEntityFields(List<EntityField> entityFields) {
    this.entityFields = entityFields;
  }
}