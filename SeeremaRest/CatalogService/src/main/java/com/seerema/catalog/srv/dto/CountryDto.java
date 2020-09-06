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

package com.seerema.catalog.srv.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.seerema.catalog.srv.jpa.model.Country;
import com.seerema.shared.dto.AbstractEntityDto;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

@DtoFor(Country.class)
public class CountryDto extends AbstractEntityDto {

  @NotEmpty
  @Size(max = 25)
  @ModelItem
  private String name;

  @NotEmpty
  @ModelItem
  @Size(max = 25)
  private String regionName;

  @NotEmpty
  @ModelItem
  @Size(max = 25)
  private String postalName;

  @NotEmpty
  @ModelItem
  @Size(max = 25)
  private String regionField;

  @NotEmpty
  @ModelItem
  @Size(max = 25)
  private String addrFormatter;

  public CountryDto() {
  }

  public String getName() {
    return name;
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
