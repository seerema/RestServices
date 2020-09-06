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

package com.seerema.catalog.srv.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerema.catalog.srv.dto.AddressDto;
import com.seerema.catalog.srv.dto.CityDto;
import com.seerema.catalog.srv.dto.CountryDto;
import com.seerema.catalog.srv.dto.RegionDto;
import com.seerema.catalog.srv.jpa.model.Address;
import com.seerema.catalog.srv.jpa.model.City;
import com.seerema.catalog.srv.jpa.model.Country;
import com.seerema.catalog.srv.jpa.model.Region;
import com.seerema.catalog.srv.jpa.repo.AddressRepo;
import com.seerema.catalog.srv.jpa.repo.CityRepo;
import com.seerema.catalog.srv.jpa.repo.CountryRepo;
import com.seerema.catalog.srv.jpa.repo.RegionRepo;
import com.seerema.shared.dto.EntityDto;
import com.seerema.shared.dto.EntityFieldDto;
import com.seerema.shared.jpa.base.model.DbEntity;
import com.seerema.shared.jpa.base.model.EntityField;
import com.seerema.shared.jpa.base.repo.DbEntityRepo;
import com.seerema.shared.jpa.base.repo.EntityFieldRepo;
import com.seerema.shared.jpa.base.repo.FieldCategoryRepo;
import com.seerema.shared.jpa.base.repo.FieldRepo;
import com.seerema.shared.jpa.base.service.BaseEntityService;
import com.seerema.shared.jpa.base.service.EntityService;

/**
 * Shared Catalog Test Units
 * Using @DirtiesContext to make sure database sequences resets for each test case
 */
@DirtiesContext
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:/test.properties")
public class SharedCatalogServiceTestUnits {

  @Autowired
  private CountryRepo countryRepo;

  @Autowired
  private BaseEntityService<Country, CountryDto> countrySrv;

  @Autowired
  private RegionRepo regionRepo;

  @Autowired
  private BaseEntityService<Region, RegionDto> regionSrv;

  @Autowired
  private CityRepo cityRepo;

  @Autowired
  private BaseEntityService<City, CityDto> citySrv;

  @Autowired
  private AddressRepo addrRepo;

  @Autowired
  private BaseEntityService<Address, AddressDto> addrSrv;

  @Autowired
  private DbEntityRepo dbEntityRepo;

  @Autowired
  private EntityService<DbEntity, EntityDto> entitySrv;

  @Autowired
  private FieldCategoryRepo fieldCategoryRepo;

  @Autowired
  private FieldRepo fieldRepo;

  @Autowired
  private EntityFieldRepo entityFieldRepo;

  @Autowired
  private BaseEntityService<EntityField, EntityFieldDto> entityFieldSrv;

  public BaseEntityService<Country, CountryDto> getCountrySrv() {
    return countrySrv;
  }

  public BaseEntityService<Region, RegionDto> getRegionSrv() {
    return regionSrv;
  }

  public BaseEntityService<City, CityDto> getCitySrv() {
    return citySrv;
  }

  public BaseEntityService<Address, AddressDto> getAddressSrv() {
    return addrSrv;
  }

  public EntityService<DbEntity, EntityDto> getEntitySrv() {
    return entitySrv;
  }

  public BaseEntityService<EntityField, EntityFieldDto> getEntityFieldSrv() {
    return entityFieldSrv;
  }

  @AfterEach
  private void clearData() {
    addrRepo.deleteAll();
    cityRepo.deleteAll();
    regionRepo.deleteAll();
    countryRepo.deleteAll();

    entityFieldRepo.deleteAll();
    dbEntityRepo.deleteAll();

    fieldRepo.deleteAll();
    fieldCategoryRepo.deleteAll();
  }
}
