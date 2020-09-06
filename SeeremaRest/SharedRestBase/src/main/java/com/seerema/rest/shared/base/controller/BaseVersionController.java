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

package com.seerema.rest.shared.base.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seerema.shared.config.RestConfig;
import com.seerema.shared.service.BaseService;

/**
 * Same as BaseController but added mapping for version.
 * Support both / and /version calls
 * 
 */

public class BaseVersionController
    extends BaseRestController<BaseService<RestConfig>, RestConfig> {

  @RequestMapping(value = { "/" }, method = RequestMethod.GET,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public String defaultHandler() {
    return getVersion();
  }

  @RequestMapping(value = { "/version" }, method = RequestMethod.GET,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public String version() {
    return getVersion();
  }
}
