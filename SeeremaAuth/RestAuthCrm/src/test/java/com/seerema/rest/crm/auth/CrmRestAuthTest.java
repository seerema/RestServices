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

package com.seerema.rest.crm.auth;

import com.seerema.crm.srv.shared.CrmConstants;
import com.seerema.rest.auth.base.SharedModRestAuthTest;
import com.seerema.rest.auth.base.SharedRestConstants;

/**
 * Test class for Crm Auth Rest API
 * 
 */

public class CrmRestAuthTest extends SharedModRestAuthTest {

  @Override
  protected String getBaseUrl() {
    return "/" + CrmConstants.MODULE_NAME;
  }

  @Override
  protected String getFieldJson() {
    return "{\"id\":1," + "\"name\":\"Item\",\"field_category\":{\"id\":1," +
        "\"name\":\"LL_TEST\",\"read_only\":false},\"read_only\":false}";
  }

  @Override
  protected String getFieldCategoryJson() {
    return "{\"id\":1,\"name\":\"LL_TEST\",\"read_only\":false}";
  }

  @Override
  protected String getEntityJson() {
    return "\\{\"id\":1,\"name\":\"Test\"," +
        "\"entity_fields\":\\[\\{\"id\":1,\"field\":\\{\"id\":1,\"name\":\"Item\"," +
        "\"field_category\":\\{\"id\":1,\"name\":\"LL_TEST\",\"read_only\":false\\}," +
        "\"read_only\":false\\},\"value\":\"Test\"\\}\\],\"field_cat\":\\{" +
        "\"id\":1,\"name\":\"LL_TEST\",\"read_only\":false\\},\"status\":\\{" +
        "\"id\":1,\"name\":\"LL_LEAD\"}," +
        "\"status_histories\":\\[\\{\"id\":1," +
        "\"user\":\\{\"id\":1,\"name\":\"foo\"\\}," +
        "\"status\":\\{\"id\":1,\"name\":\"LL_LEAD\"}," +
        SharedRestConstants.CREATED_FIELD_PATTERN + "\\}\\]" +
        ",\"owner_histories\":\\[\\{\"id\":1," +
        "\"user\":\\{\"id\":1,\"name\":\"foo\"}," +
        "\"owner\":\\{\"id\":1,\"name\":\"foo\"}," +
        SharedRestConstants.CREATED_FIELD_PATTERN + "\\}\\]" +
        ",\"user\":\\{\"id\":1,\"name\":\"foo\"\\}\\}";
  }

  @Override
  protected String getAllEntities() {
    return "^\\{\"result\":true,\"data\":\\[\\{\"id\":1,\"name\":\"Test\"," +
        "\"entity_fields\":\\[\\{\"id\":1,\"field\":\\{\"id\":1,\"name\":\"Item\"," +
        "\"field_category\":\\{\"id\":1,\"name\":\"LL_TEST\",\"read_only\":false\\}," +
        "\"read_only\":false\\},\"value\":\"Test\"\\}\\],\"field_cat\":\\{" +
        "\"id\":1,\"name\":\"LL_TEST\",\"read_only\":false\\},\"status\":\\{" +
        "\"id\":1,\"name\":\"LL_LEAD\"\\}," +
        "\"status_histories\":\\[\\{\"id\":1," +
        "\"user\":\\{\"id\":1,\"name\":\"foo\"\\}," +
        "\"status\":\\{\"id\":1,\"name\":\"LL_LEAD\"\\}," +
        SharedRestConstants.CREATED_FIELD_PATTERN + "\\}\\]" +
        ",\"owner_histories\":\\[\\{\"id\":1," +
        "\"user\":\\{\"id\":1,\"name\":\"foo\"}," +
        "\"owner\":\\{\"id\":1,\"name\":\"foo\"}," +
        SharedRestConstants.CREATED_FIELD_PATTERN + "\\}\\]" +
        ",\"user\":\\{\"id\":1,\"name\":\"foo\"\\}\\}\\]\\}$";
  }
}
