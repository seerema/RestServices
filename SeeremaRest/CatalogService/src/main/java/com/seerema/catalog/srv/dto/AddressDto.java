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

import com.seerema.catalog.srv.jpa.model.Address;
import com.seerema.shared.dto.AbstractEntityDto;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

@DtoFor(Address.class)
public class AddressDto extends AbstractEntityDto {

  @NotEmpty
  @ModelItem
  @Size(max = 191)
  private String line1;

  @ModelItem
  @Size(max = 255)
  private String line2;

  @NotEmpty
  @ModelItem
  @Size(max = 25)
  private String zip;

  @NotNull
  @ModelItem
  private CityDto city;

  public AddressDto() {
  }

  public String getLine1() {
    return line1;
  }

  public void setLine1(String line1) {
    this.line1 = line1;
  }

  public String getLine2() {
    return line2;
  }

  public void setLine2(String line2) {
    this.line2 = line2;
  }

  public CityDto getCity() {
    return city;
  }

  public void setCity(CityDto city) {
    this.city = city;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }
}
