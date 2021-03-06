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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.seerema.shared.jpa.base.model.AbstractEntityUser;

/**
 * The persistent class for the entity_status_history database table.
 */

@MappedSuperclass
public class AbstractEntityHistory extends AbstractEntityUser
    implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private Timestamp created;

  // uni-directional many-to-one association to Entity
  @ManyToOne(optional = false)
  @JoinColumn(name = "entity_id")
  private EntityEx entity;

  public AbstractEntityHistory() {
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

  public EntityEx getEntity() {
    return entity;
  }

  public void setEntity(EntityEx entity) {
    this.entity = entity;
  }

}