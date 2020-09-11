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

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.seerema.shared.jpa.base.model.User;

/**
 * The persistent class for the entity_status_history database table.
 */

@Entity
@Table(name = "entity_status_history")
public class EntityStatusHistory extends AbstractEntityHistory
    implements Serializable {
  private static final long serialVersionUID = 1L;

  // uni-directional many-to-one association to Status
  @ManyToOne
  @JoinColumn(name = "status_id")
  private Status status;

  public EntityStatusHistory() {
  }

  public EntityStatusHistory(EntityEx entity, Status status, User user) {
    setEntity(entity);
    setUser(user);
    setCreated(new Timestamp(System.currentTimeMillis()));

    this.status = status;
  }

  public Status getStatus() {
    return this.status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
