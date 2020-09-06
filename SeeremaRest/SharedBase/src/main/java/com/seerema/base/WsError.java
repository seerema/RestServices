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

package com.seerema.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * POJO class for Web Service Error
 * 
 */
@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
public class WsError {

  // Error code
  private String id;

  @JsonIgnore
  // Error message
  private String message;

  // Error Info
  private String info;

  // Detail message(s)
  private String[] details;

  // Request Id
  private Long requestId;

  public WsError(String id) {
    this.id = id;
  }

  public WsError(String id, String message) {
    this(id);
    this.message = message;
  }

  public WsError(String id, String message, String info) {
    this(id, message);
    this.info = info;
  }

  public WsError(String id, String message, String info, String[] details) {
    this(id, message, info);
    this.details = details;
  }

  public String getId() {
    return id;
  }

  public String getMessage() {
    return message;
  }

  public String getInfo() {
    return info;
  }

  public String[] getDetails() {
    return details;
  }

  public void clearDetails() {
    this.details = null;
  }

  public void setRequestId(Long requestId) {
    this.requestId = requestId;
  }

  public Long getRequestId() {
    return requestId;
  }
}
