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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.seerema.shared.jpa.base.model.AbstractEntity;

/**
 * The persistent class for the status_history database table.
 */

@Entity
@Table(name = "status_history")
public class StatusHistory extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private Timestamp created;

  // bi-directional many-to-one association to Status
  @ManyToOne
  @JoinColumn(name = "status_id")
  private Status status;

  // bi-directional many-to-one association to Entity
  @ManyToOne
  @JoinColumn(name = "entity_id")
  private EntityEx entity;

  @Column(name = "user_name", nullable = false)
  private String userName;

  public StatusHistory() {
  }

  public Timestamp getCreated() {
    return created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  public void setCreated(LocalDateTime dts) {
    // Date always kept in UTC
    this.created = new Timestamp(dts.toInstant(ZoneOffset.UTC).toEpochMilli());
  }

  public Status getStatus() {
    return this.status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public EntityEx getEntity() {
    return entity;
  }

  public void setEntity(EntityEx entity) {
    this.entity = entity;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}