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

package com.seerema.catalog.srv.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.seerema.shared.jpa.base.model.AbstractEntity;

/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Table(name = "address", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "city_id", "line_1" }) })
public class Address extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "line_1", nullable = false)
  private String line1;

  @Column(name = "line_2")
  private String line2;

  @Column(nullable = false)
  private String zip;

  // bi-directional many-to-one association to City
  @ManyToOne(optional = false)
  private City city;

  public Address() {
  }

  public String getLine1() {
    return this.line1;
  }

  public void setLine1(String line1) {
    this.line1 = line1;
  }

  public String getLine2() {
    return this.line2;
  }

  public void setLine2(String line2) {
    this.line2 = line2;
  }

  public String getZip() {
    return this.zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public City getCity() {
    return this.city;
  }

  public void setCity(City city) {
    this.city = city;
  }
}