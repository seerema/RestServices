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

package com.seerema.rest.shared.base.utils;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Hold Web Response together with HTTP Code
 * 
 */
public class WebResponse {
  private int _code;
  private String _msg;
  private String _cookie;

  public WebResponse(int code) {
    this(code, null);
  }

  public WebResponse(String msg) {
    this(200, msg);
  }

  public WebResponse(HttpURLConnection conn) throws IOException {
    this(conn.getResponseCode(), conn.getResponseMessage());
  }

  public WebResponse(int code, String msg) {
    _code = code;
    _msg = msg;
  }

  public int getCode() {
    return _code;
  }

  public String getMsg() {
    return _msg;
  }

  public String getCookie() {
    return _cookie;
  }

  public void setCookie(String _cookie) {
    this._cookie = _cookie;
  }

  @Override
  public String toString() {
    return "[" + _code + "] - " + _msg;
  }
}
