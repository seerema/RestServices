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

package com.seerema.shared.ui.config.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.seerema.base.WsSrvException;
import com.seerema.shared.service.impl.BaseServiceImpl;
import com.seerema.shared.ui.config.UiConfig;
import com.seerema.shared.ui.config.service.UiConfigCutomizer;
import com.seerema.shared.ui.config.service.UiConfigService;

@Service("base_ui_srv")
public class UiConfigServiceImpl extends BaseServiceImpl
    implements UiConfigService {

  @Autowired
  @Qualifier("ui_cfg")
  private UiConfig _cfg;

  @Autowired(required = false)
  private UiConfigCutomizer _custom;

  @Override
  public Map<String, String> getUiConfig(String lst) {
    getLogger().debug("cfg request for '" + lst + "'");

    Map<String, String> map = new HashMap<String, String>();
    String[] arr = lst.split(",");

    if (_cfg == null)
      return map;

    for (String key : arr) {
      try {
        map.put(key, _cfg.getPropByName(key));
      } catch (Exception e) {
        // Check customizer
        if (_custom != null) {
          try {
            map.put(key, _custom.getPropByName(key));
          } catch (WsSrvException e1) {
            // Skip configuration parameter if missing in a bean 
            getLogger().error(e.getMessage());
          }
        } else {
          // Skip configuration parameter if missing in a bean 
          getLogger().error(e.getMessage());
        }
      }
    }

    return map;
  }
}
