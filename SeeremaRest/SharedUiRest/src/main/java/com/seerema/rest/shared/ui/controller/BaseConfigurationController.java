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

package com.seerema.rest.shared.ui.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.seerema.rest.shared.base.controller.BaseController;
import com.seerema.shared.ui.config.service.UiConfigService;

public class BaseConfigurationController extends BaseController {

  @Autowired
  @Qualifier("base_ui_srv")
  private UiConfigService _bs;

  @RequestMapping(value = "/cfg", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, String> getConfig(@RequestParam("lst") String lst) {
    return _bs.getUiConfig(lst);
  }
}