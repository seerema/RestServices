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
 * The persistent class for the status_history database table.
 */

@Entity
@Table(name = "entity_user_history")
public class EntityUserHistory extends AbstractEntityHistory
    implements Serializable {
  private static final long serialVersionUID = 1L;

  // uni-directional many-to-one association to User
  @ManyToOne
  @JoinColumn(name = "owner_id")
  private User owner;

  public EntityUserHistory() {
  }

  public EntityUserHistory(EntityEx entity, User owner, User user) {
    setEntity(entity);
    setUser(user);
    setCreated(new Timestamp(System.currentTimeMillis()));

    this.owner = owner;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }
}
