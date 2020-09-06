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

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * The persistent class for the entity database table.
 */

@Entity
@Table(name = "entity")
public class DbEntity extends AbstractEntityModule {

  @Column(unique = true, nullable = false)
  private String name;

  // bi-directional many-to-one association to Company
  @ManyToOne
  @JoinColumn(name = "parent_id")
  private DbEntity parent;

  // bi-directional many-to-one association to Company
  @OneToMany(mappedBy = "parent")
  private List<DbEntity> entities;

  // bi-directional many-to-one association to CompanyField
  @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL,
      orphanRemoval = true)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<EntityField> entityFields;

  // bi-directional many-to-one association to FieldCategory
  @ManyToOne(optional = false)
  @JoinColumn(name = "field_cat_id")
  private FieldCategory fieldCat;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DbEntity getParent() {
    return parent;
  }

  public void setParent(DbEntity parent) {
    this.parent = parent;
  }

  public List<DbEntity> getEntities() {
    return entities;
  }

  public void setEntities(List<DbEntity> entities) {
    this.entities = entities;
  }

  public List<EntityField> getEntityFields() {
    return entityFields;
  }

  public void setEntityFields(List<EntityField> entityFields) {
    this.entityFields = entityFields;
  }

  public FieldCategory getFieldCat() {
    return fieldCat;
  }

  public void setFieldCat(FieldCategory fieldCat) {
    this.fieldCat = fieldCat;
  }
}
