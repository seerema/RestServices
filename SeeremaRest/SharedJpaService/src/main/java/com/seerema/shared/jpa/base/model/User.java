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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Table(name = "user")
public class User extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "name", nullable = false)
  private String name;

  public User() {
  }

  public User(String name) {
    this.name = name;
  }

  public User(int id) {
    setId(id);
  }

  public User(int id, String name) {
    setId(id);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof User))
      return false;

    User user = (User) obj;

    return user.getId().equals(getId()) && user.getName() != null &&
        user.getName().equals(name);
  }
}