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

package com.seerema.rest.task.auth;

import com.seerema.rest.auth.base.SharedModRestAuthTest;
import com.seerema.rest.auth.base.SharedRestConstants;
import com.seerema.task.srv.shared.TaskConstants;

/**
 * Test class for Task Auth Rest API
 * 
 */

public class TaskRestAuthTest extends SharedModRestAuthTest {

  @Override
  protected String getBaseUrl() {
    return "/" + TaskConstants.MODULE_NAME;
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
        "\"entity_fields\":\\[\\{\"id\":1,\"field\":\\{\"id\":1," +
        "\"name\":\"Item\",\"field_category\":\\{\"id\":1,\"name\":\"LL_TEST\"," +
        "\"read_only\":false\\},\"read_only\":false\\},\"value\":\"Test\"\\}\\]," +
        "\"field_cat\":\\{\"id\":1,\"name\":\"LL_TEST\",\"read_only\":false\\}," +
        "\"status\":\\{\"id\":1\\,\"name\":\"LL_NEW_F\"},\"status_histories\":\\[\\{\"id\":1," +
        "\"status\":\\{\"id\":1\\,\"name\":\"LL_NEW_F\"},\"user_name\":\"foo\"," +
        SharedRestConstants.CREATED_FIELD_PATTERN +
        "\\}\\],\"user_name\":\"foo\"\\}";
  }

  @Override
  protected String getAllEntities() {
    return "^\\{\"result\":true,\"data\":\\[\\{\"id\":1,\"name\":\"Test\",\"entity_fields\":\\[\\{\"id\":1," +
        "\"field\":\\{\"id\":1,\"name\":\"Item\",\"field_category\":\\{" +
        "\"id\":1,\"name\":\"LL_TEST\",\"read_only\":false\\},\"read_only\":false\\}," +
        "\"value\":\"Test\"\\}\\],\"field_cat\":\\{\"id\":1,\"name\":\"LL_TEST\"," +
        "\"read_only\":false\\},\"status\":\\{\"id\":1,\"name\":\"LL_NEW_F\"\\}," +
        "\"status_histories\":\\[\\{\"id\":1,\"status\":\\{\"id\":1," +
        "\"name\":\"LL_NEW_F\"\\},\"user_name\":\"foo\"," +
        SharedRestConstants.CREATED_FIELD_PATTERN +
        "\\}\\],\"user_name\":\"foo\"\\}\\]\\}$";
  }
}
