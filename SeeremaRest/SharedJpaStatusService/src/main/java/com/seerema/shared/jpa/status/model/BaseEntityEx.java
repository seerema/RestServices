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

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.seerema.shared.jpa.base.model.BaseEntity;
import com.seerema.shared.jpa.base.model.DbEntity;
import com.seerema.shared.jpa.base.model.EntityField;
import com.seerema.shared.jpa.base.model.FieldCategory;
import com.seerema.shared.jpa.base.model.User;

/**
 * The persistent class for the entity_ex database table.
 */

@MappedSuperclass
public class BaseEntityEx implements BaseEntity {

  @Id
  private Integer id;

  public BaseEntityEx() {
  }

  public BaseEntityEx(int id) {
    setId(id);
  }

  // uni-directional many-to-one association to User
  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id")
  private User user;

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
  List<EntityStatusHistory> entityStatusHistories;

  // bi-directional one-to-many association to statusHistory
  @OneToMany(mappedBy = "entity", cascade = CascadeType.REFRESH)
  @OnDelete(action = OnDeleteAction.CASCADE)
  List<EntityUserHistory> ownerHistories;

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

  public List<EntityStatusHistory> getStatusHistories() {
    return entityStatusHistories;
  }

  public void setStatusHistories(List<EntityStatusHistory> statusHitories) {
    this.entityStatusHistories = statusHitories;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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

  public List<EntityUserHistory> getOwnerHistories() {
    return ownerHistories;
  }

  public void setOwnerHistories(List<EntityUserHistory> ownerHistories) {
    this.ownerHistories = ownerHistories;
  }
}
