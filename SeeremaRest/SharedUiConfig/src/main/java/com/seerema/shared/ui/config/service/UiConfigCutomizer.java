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

package com.seerema.shared.ui.config.service;

import com.seerema.base.WsSrvException;

/**
 * BaseUI configuration customizer
 *
 */
public interface UiConfigCutomizer {

  String getPropByName(String key) throws WsSrvException;

}
