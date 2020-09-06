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
 * The persistent class for the region database table.
 * 
 */
@Entity
@Table(name = "region", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "country_id", "name" }) })
public class Region extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "short_name")
  private String shortName;

  // bi-directional many-to-one association to City
  @OneToMany(mappedBy = "region")
  private List<City> cities;

  // bi-directional many-to-one association to Country
  @ManyToOne(optional = false)
  private Country country;

  public Region() {
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<City> getCities() {
    return this.cities;
  }

  public void setCities(List<City> cities) {
    this.cities = cities;
  }

  public City addCity(City city) {
    getCities().add(city);
    city.setRegion(this);

    return city;
  }

  public City removeCity(City city) {
    getCities().remove(city);
    city.setRegion(null);

    return city;
  }

  public Country getCountry() {
    return this.country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

}