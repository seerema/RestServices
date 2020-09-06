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

package com.seerema.shared.rest.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataGoodResponse extends BaseGoodResponse {

  private List<?> data;

  /**
   * Default constructor
   */
  public DataGoodResponse() {
  }

  public DataGoodResponse(List<?> data) {
    super();

    this.data = data != null ? data : new ArrayList<>();
  }

  public DataGoodResponse(Object data) {
    this(data != null ? Arrays.asList(new Object[] { data }) : null);
  }

  /**
   * @return the data
   */
  public List<?> getData() {
    return data;
  }

  /**
   * @param data the data to set
   */
  public void setData(List<?> data) {
    this.data = data;
  }
}
