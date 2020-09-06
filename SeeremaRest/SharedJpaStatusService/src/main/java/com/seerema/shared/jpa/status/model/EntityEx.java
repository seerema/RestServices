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
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.seerema.shared.jpa.base.model.BaseEntity;
import com.seerema.shared.jpa.base.model.DbEntity;
import com.seerema.shared.jpa.base.model.EntityField;
import com.seerema.shared.jpa.base.model.FieldCategory;

/**
 * The persistent class for the entity_ex database table.
 */

@Entity
@Table(name = "entity_ex")
public class EntityEx implements BaseEntity, Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private Integer id;

  @Column(name = "user_name", nullable = false)
  private String userName;

  // uni-directional one-to-one association to main entity
  @OneToOne(optional = false)
  @JoinColumn(name = "entity_id")
  DbEntity dbEntity;

  // bi-directional one-to-many association to status
  @ManyToOne(optional = false)
  Status status;

  // bi-directional one-to-many association to statusHistory
  @OneToMany(mappedBy = "entity", cascade = CascadeType.REFRESH)

  // Delete automatically all history when entity deleted.
  @OnDelete(action = OnDeleteAction.CASCADE)
  List<StatusHistory> statusHistories;

  /*** NON-PRERSISTANT FIELD ***/
  @Transient
  private String name;

  @Transient
  private DbEntity parent;

  @Transient
  private List<EntityField> entityFields;

  @Transient
  private FieldCategory fieldCat;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public DbEntity getDbEntity() {
    return dbEntity;
  }

  public void setDbEntity(DbEntity dbEntity) {
    this.dbEntity = dbEntity;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public List<StatusHistory> getStatusHistories() {
    return statusHistories;
  }

  public void setStatusHistories(List<StatusHistory> statusHitories) {
    this.statusHistories = statusHitories;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getName() {
    return dbEntity.getName();
  }

  public DbEntity getParent() {
    return dbEntity.getParent();
  }

  public List<EntityField> getEntityFields() {
    return dbEntity.getEntityFields();
  }

  public FieldCategory getFieldCat() {
    return dbEntity.getFieldCat();
  }

  public void setName(String name) {
    dbEntity.setName(name);
  }

  public void setParent(DbEntity parent) {
    dbEntity.setParent(parent);
  }

  public void setEntityFields(List<EntityField> entityFields) {
    dbEntity.setEntityFields(entityFields);
  }

  public void setFieldCat(FieldCategory fieldCat) {
    dbEntity.setFieldCat(fieldCat);
  }
}
