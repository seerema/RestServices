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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.seerema.shared.jpa.base.model.AbstractEntity;

/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@Table(name = "city", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "region_id", "name" }) })
public class City extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String name;

  // bi-directional many-to-one association to Address
  @OneToMany(mappedBy = "city")
  private List<Address> addresses;

  // bi-directional many-to-one association to Region
  @ManyToOne(optional = false)
  private Region region;

  public City() {
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Address> getAddresses() {
    return this.addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public Address addAddress(Address address) {
    getAddresses().add(address);
    address.setCity(this);

    return address;
  }

  public Address removeAddress(Address address) {
    getAddresses().remove(address);
    address.setCity(null);

    return address;
  }

  public Region getRegion() {
    return this.region;
  }

  public void setRegion(Region region) {
    this.region = region;
  }

}