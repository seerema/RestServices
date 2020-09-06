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

package com.seerema.srv.shared.mapper;

import java.util.ArrayList;
import java.util.List;

import com.seerema.srv.shared.ExtDto;
import com.seerema.srv.shared.ValueDto;

/**
 * Shared test utilities
 *
 */
class SharedTestUtils {

  public static ExtDto getTestExtDto() {
    ValueDto value = new ValueDto("test");
    List<ValueDto> values = new ArrayList<>();
    values.add(value);

    return new ExtDto(1, 2L, value, values);
  }
}
