/*
v * Seerema Business Solutions - http://www.seerema.com/
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.seerema.shared.jpa.base.model.AbstractEntity;

/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@Table(name = "country")
public class Country extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(unique = true, nullable = false)
  private String name;

  @Column(name = "region_name", nullable = false)
  private String regionName;

  @Column(name = "postal_name", nullable = false)
  private String postalName;

  @Column(name = "region_field", nullable = false)
  private String regionField;

  @Column(name = "addr_formatter", nullable = false)
  private String addrFormatter;

  // bi-directional many-to-one association to Region
  @OneToMany(mappedBy = "country")
  private List<Region> regions;

  public Country() {
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRegionName() {
    return regionName;
  }

  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }

  public List<Region> getRegions() {
    return this.regions;
  }

  public void setRegions(List<Region> regions) {
    this.regions = regions;
  }

  public Region addRegion(Region region) {
    getRegions().add(region);
    region.setCountry(this);

    return region;
  }

  public Region removeRegion(Region region) {
    getRegions().remove(region);
    region.setCountry(null);

    return region;
  }

  public String getPostalName() {
    return postalName;
  }

  public void setPostalName(String postalName) {
    this.postalName = postalName;
  }

  public String getRegionField() {
    return regionField;
  }

  public void setRegionField(String regionField) {
    this.regionField = regionField;
  }

  public String getAddrFormatter() {
    return addrFormatter;
  }

  public void setAddrFormatter(String addrFormatter) {
    this.addrFormatter = addrFormatter;
  }
}