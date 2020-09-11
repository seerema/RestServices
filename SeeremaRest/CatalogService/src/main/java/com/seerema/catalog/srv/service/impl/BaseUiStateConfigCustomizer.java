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

package com.seerema.catalog.srv.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.seerema.base.WsSrvException;
import com.seerema.catalog.srv.jpa.repo.AddressRepo;
import com.seerema.catalog.srv.jpa.repo.CityRepo;
import com.seerema.catalog.srv.jpa.repo.CountryRepo;
import com.seerema.catalog.srv.jpa.repo.RegionRepo;
import com.seerema.catalog.srv.shared.ErrorCodes;
import com.seerema.shared.jpa.base.repo.DbEntityRepo;
import com.seerema.shared.jpa.base.service.impl.BaseDbAccessService;
import com.seerema.shared.ui.config.service.UiConfigCutomizer;

/**
 * Customizer for BaseUiConfigCutomizer bean
 *
 */
@Service
public class BaseUiStateConfigCustomizer extends BaseDbAccessService
    implements UiConfigCutomizer {

  @Autowired
  private CountryRepo _country;

  @Autowired
  private RegionRepo _region;

  @Autowired
  private CityRepo _city;

  @Autowired
  private AddressRepo _address;

  @Autowired
  private DbEntityRepo _company;

  private String _state;

  @PostConstruct
  public void initState() throws WsSrvException {
    try {
      // Check if any record in Country table
      if (_country.count() == 0) {
        _state = "country";
        return;
      }

      // Check if any record in Region table
      if (_region.count() == 0) {
        _state = "region";
        return;
      }

      // Check if any record in City table
      if (_city.count() == 0) {
        _state = "city";
        return;
      }

      // Check if any record in Address table
      if (_address.count() == 0) {
        _state = "address";
        return;
      }

      // Check if any record in BusinessInfo table
      if (_company.count() == 0) {
        _state = "business_info";
        return;
      }
    } catch (DataAccessException e) {
      throw throwError(ErrorCodes.ERROR_INIT_STATE.name(), e);
    }
    // Empty means ALL SET
    _state = "";
  }

  @Override
  public String getPropByName(String key) throws WsSrvException {
    if (!key.equals("state_to_continue"))
      throw new WsSrvException(ErrorCodes.INVALID_KEY.name(),
          "Invalid key '" + key + "'");

    return _state;
  }
}
