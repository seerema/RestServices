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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.seerema.catalog.srv.jpa.model.Region;
import com.seerema.shared.dto.AbstractEntityDto;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

@DtoFor(Region.class)
public class RegionDto extends AbstractEntityDto {

  @NotEmpty
  @ModelItem
  @Size(max = 50)
  private String name;

  @NotEmpty
  @ModelItem
  @Size(max = 25)
  private String shortName;

  @NotNull
  @ModelItem
  private CountryDto country;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public CountryDto getCountry() {
    return country;
  }

  public void setCountry(CountryDto country) {
    this.country = country;
  }
}
