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

package com.seerema.rest.catalog.auth;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

import com.seerema.catalog.srv.dto.AddressDto;
import com.seerema.catalog.srv.dto.CityDto;
import com.seerema.catalog.srv.dto.CountryDto;
import com.seerema.catalog.srv.dto.RegionDto;
import com.seerema.catalog.srv.shared.CatalogConstants;
import com.seerema.rest.auth.base.SharedRestTestUnits;
import com.seerema.rest.shared.base.utils.CommonWebTestUtils;
import com.seerema.shared.dto.CommMediaDto;
import com.seerema.shared.dto.EntityDto;
import com.seerema.shared.dto.EntityFieldDto;
import com.seerema.shared.dto.FieldCategoryDto;
import com.seerema.shared.dto.FieldDto;

/**
 * Test Configuration class for Base Rest API
 * 
 */

public class CatalogRestAuthTest extends SharedRestTestUnits {

  private final static String BASE_URL = "/" + CatalogConstants.MODULE_NAME;
  private final static String[] AUTH_URL = new String[] { "/", "/cfg" };

  private final static String[] API_LIST =
      new String[] { "/countries", "/regions", "/cities", "/addresses",
          "/entities", "/field_cat", "/field" };

  @Test
  public void testAuthAccessOK() throws IOException {
    checkAuthUrlAccessOk("/", CommonWebTestUtils.readAppVersion());
    checkAuthUrlAccessOk("/cfg?lst=lang", "{\"lang\":\"en\"}");
  }

  @Test
  public void testCatalogs() throws Exception {
    prepUserSecuritySession(10000);
    HttpHeaders headers = prepHttpHeaders();

    // Create country
    CountryDto country = new CountryDto();
    country.setName("Test");
    country.setRegionName("LL_TEST");
    country.setPostalName("LL_ZIP");
    country.setRegionField("name");
    country.setAddrFormatter("test_addr_formatter");
    checkEntity(
        new RequestEntity<CountryDto>(
            country, headers, HttpMethod.PUT, new URI(BASE_URL + "/country")),
        "country",
        "{\"id\":1,\"name\":\"Test\",\"region_name\":\"LL_TEST\"," +
            "\"postal_name\":\"LL_ZIP\",\"region_field\":\"name\"," +
            "\"addr_formatter\":\"test_addr_formatter\"}");
    country.setId(1);

    // Create Region
    RegionDto region = new RegionDto();
    region.setName("Test");
    region.setShortName("TT");
    region.setCountry(country);
    checkEntity(
        new RequestEntity<RegionDto>(
            region, headers, HttpMethod.PUT, new URI(BASE_URL + "/region")),
        "region",
        "{\"id\":1,\"name\":\"Test\",\"short_name\":\"TT\",\"country\":{" +
            "\"id\":1,\"name\":\"Test\",\"region_name\":\"LL_TEST\"," +
            "\"postal_name\":\"LL_ZIP\",\"region_field\":\"name\"," +
            "\"addr_formatter\":\"test_addr_formatter\"}}");
    region.setId(1);

    // Create City
    CityDto city = new CityDto();
    city.setName("Test");
    city.setRegion(region);
    checkEntity(
        new RequestEntity<CityDto>(city, headers, HttpMethod.PUT,
            new URI(BASE_URL + "/city")),
        "city",
        "{\"id\":1," +
            "\"name\":\"Test\",\"region\":{\"id\":1,\"name\":\"Test\"," +
            "\"short_name\":\"TT\",\"country\":{\"id\":1,\"name\":\"Test\"," +
            "\"region_name\":\"LL_TEST\",\"postal_name\":\"LL_ZIP\"," +
            "\"region_field\":\"name\",\"addr_formatter\":\"test_addr_formatter\"}}" +
            "}");
    city.setId(1);

    // Create Address
    AddressDto address = new AddressDto();
    address.setLine1("Street");
    address.setZip("123456");
    address.setCity(city);
    checkEntity(
        new RequestEntity<AddressDto>(
            address, headers, HttpMethod.PUT, new URI(BASE_URL + "/address")),
        "address",
        "{\"id\":1," + "\"line1\":\"Street\",\"zip\":\"123456\",\"city\":{" +
            "\"id\":1,\"name\":\"Test\",\"region\":{" +
            "\"id\":1,\"name\":\"Test\",\"short_name\":\"TT\",\"country\":{" +
            "\"id\":1,\"name\":\"Test\",\"region_name\":\"LL_TEST\"," +
            "\"postal_name\":\"LL_ZIP\",\"region_field\":\"name\"," +
            "\"addr_formatter\":\"test_addr_formatter\"}}}}");
    address.setId(1);

    // Create Field Category
    FieldCategoryDto fcat = new FieldCategoryDto();
    fcat.setName("LL_TEST");
    checkEntity(
        new RequestEntity<FieldCategoryDto>(fcat, headers, HttpMethod.PUT,
            new URI(BASE_URL + "/field_cat")),
        "field_cat", "{\"id\":1,\"name\":\"LL_TEST\",\"read_only\":false}");
    fcat.setId(1);

    // Create Field
    FieldDto field = new FieldDto();
    field.setName("Item");
    field.setFieldCategory(fcat);
    checkEntity(
        new RequestEntity<FieldDto>(
            field, headers, HttpMethod.PUT, new URI(BASE_URL + "/field")),
        "field",
        "{\"id\":1," +
            "\"name\":\"Item\",\"field_category\":{\"id\":1,\"name\":\"LL_TEST\"," +
            "\"read_only\":false},\"read_only\":false}");
    field.setId(1);

    // Create BusinessInfo with EntityField
    EntityFieldDto cfield = new EntityFieldDto();
    cfield.setValue("Test");
    cfield.setField(field);

    EntityDto company = new EntityDto();
    company.setName("Test");
    List<EntityFieldDto> list = new ArrayList<>();
    list.add(cfield);
    company.setEntityFields(list);
    company.setFieldCat(fcat);

    checkEntity(
        new RequestEntity<EntityDto>(
            company, headers, HttpMethod.PUT, new URI(BASE_URL + "/entity")),
        "entity",
        "{\"id\":1,\"name\":\"Test\",\"entity_fields\":[{\"id\":1,\"field\":{" +
            "\"id\":1,\"name\":\"Item\",\"field_category\":{" +
            "\"id\":1,\"name\":\"LL_TEST\",\"read_only\":false}," +
            "\"read_only\":false},\"value\":\"Test\"}]," +
            "\"field_cat\":{\"id\":1,\"name\":\"LL_TEST\",\"read_only\":false}}");
    company.setId(1);

    // Create comm_media
    CommMediaDto cm = new CommMediaDto();
    cm.setName("zz");
    checkEntity(
        new RequestEntity<CommMediaDto>(cm, headers, HttpMethod.PUT,
            new URI(BASE_URL + "/comm_media")),
        "comm_media", "{\"id\":1,\"name\":\"zz\",\"read_only\":false}");

    // Drop all entities in reverse order
    for (String name : new String[] { "entity", "address", "city", "region",
        "country", "comm_media" })
      dropEntity(name);
  }

  @Override
  protected String[] getAuthUrls() {
    // Combine entities list with authentication urls
    String[] urls = new String[AUTH_URL.length + API_LIST.length];

    for (int i = 0; i < API_LIST.length; i++)
      urls[i] = API_LIST[i];

    for (int i = 0; i < AUTH_URL.length; i++)
      urls[API_LIST.length + i] = AUTH_URL[i];

    return urls;
  }

  @Override
  protected String getBaseUrl() {
    return BASE_URL;
  }
}
