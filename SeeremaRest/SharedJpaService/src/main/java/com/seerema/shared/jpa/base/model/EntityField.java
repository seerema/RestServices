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

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The persistent class for the entity_field database table.
 */

@Entity
@Table(name = "entity_field", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "field_id", "entity_id" }) })
public class EntityField extends AbstractEntity {

  private String value;

  // bi-directional many-to-one association to Field
  @ManyToOne(optional = false)
  private Field field;

  // bi-directional many-to-one association to Company
  @ManyToOne(optional = false)
  private DbEntity entity;

  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Field getField() {
    return field;
  }

  public void setField(Field field) {
    this.field = field;
  }

  public DbEntity getEntity() {
    return entity;
  }

  public void setEntity(DbEntity entity) {
    this.entity = entity;
  }
}
