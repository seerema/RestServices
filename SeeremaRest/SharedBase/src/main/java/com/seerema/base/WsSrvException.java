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

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Web Service Exception
 * 
 */

@JsonRootName("error")
@JsonAutoDetect(fieldVisibility = Visibility.NONE,
    getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class WsSrvException extends Exception {

  // Default serial version uid
  private static final long serialVersionUID = 1L;

  // Request Id
  @JsonProperty("request_id")
  private Long rid;

  // Error object
  @JsonProperty("error")
  private WsError error;

  public WsSrvException(String errCode, String errInfo, String detailMsg) {
    this(errCode, errInfo,
        detailMsg != null ? new String[] { detailMsg } : null);
  }

  public WsSrvException(String errCode, Exception e) {
    this(errCode, "Exception type " + e.getClass().getName(),
        (e == null ? null
            : ((e.getMessage() != null) ? new String[] { e.getMessage() }
                : (e.getCause() != null && e.getCause().getMessage() != null
                    ? new String[] { e.getCause().getMessage() } : null))));
  }

  public WsSrvException(String errCode, Throwable t) {
    this(errCode, "Exception type " + t.getClass().getName(),
        (t == null ? null
            : ((t.getMessage() != null) ? new String[] { t.getMessage() }
                : (t.getCause() != null && t.getCause().getMessage() != null
                    ? new String[] { t.getCause().getMessage() } : null))));
  }

  public WsSrvException(String errCode, String detailMsg, Exception e) {
    this(errCode, null, (e == null ? new String[] { detailMsg }
        : ((e.getMessage() != null) ? new String[] { detailMsg, e.getMessage() }
            : (e.getCause() != null && e.getCause().getMessage() != null
                ? new String[] { detailMsg, e.getCause().getMessage() }
                : new String[] { detailMsg }))));
  }

  public WsSrvException(String errCode, Exception e, String errInfo) {
    this(errCode, errInfo,
        (e == null ? null
            : ((e.getMessage() != null) ? new String[] { e.getMessage() }
                : (e.getCause() != null && e.getCause().getMessage() != null
                    ? new String[] { e.getCause().getMessage() } : null))));
  }

  public WsSrvException(String errCode, String detailMsg, Throwable t) {
    this(errCode, null, (t == null ? new String[] { detailMsg }
        : ((t.getMessage() != null) ? new String[] { detailMsg, t.getMessage() }
            : (t.getCause() != null && t.getCause().getMessage() != null
                ? new String[] { detailMsg, t.getCause().getMessage() }
                : new String[] { detailMsg }))));
  }

  public WsSrvException(String errCode, Throwable t, String errInfo) {
    this(errCode, errInfo,
        (t == null ? null
            : ((t.getMessage() != null) ? new String[] { t.getMessage() }
                : (t.getCause() != null && t.getCause().getMessage() != null
                    ? new String[] { t.getCause().getMessage() } : null))));
  }

  public WsSrvException(String errCode, String errorInfo) {
    this(errCode, errorInfo, new String[] {});
  }

  public WsSrvException(String errCode, String[] detailMsg) {
    this(errCode, null, detailMsg);
  }

  public WsSrvException(String errCode, String errInfo, String[] detailMsg) {
    super(errCode + " - " + errInfo +
        (detailMsg != null ? " " + Arrays.toString(detailMsg) : ""));
    error = new WsError(errCode, getMessage(), errInfo, detailMsg);
  }

  @JsonIgnore
  public String getErrorInfo() {
    return error.getInfo();
  }

  @JsonIgnore
  public String[] getDetailMsgs() {
    return error.getDetails();
  }

  @JsonIgnore
  public WsError getError() {
    return error;
  }

  @JsonIgnore
  public String getErrorCode() {
    return error.getId();
  }

  @Override
  public String toString() {
    String res = "ERROR - " + getErrorCode();

    String info = getErrorInfo();

    String dmsg = "";
    String[] details = error.getDetails();

    if (details != null && details.length > 0) {
      dmsg = "; DETAILS - ";
      for (String msg : details)
        dmsg += "[" + msg + "]";
    }

    return res + ((info != null) ? "; INFO - " + info : "") + dmsg;
  }

  @JsonIgnore
  public String getFullMessageString() {
    return toString();
  }

  /**
   * @return the request id
   */
  public Long getRequestId() {
    return rid;
  }

  /**
   * @param requestId the request id
   */
  public void setRequestId(Long requestId) {
    this.rid = requestId;
    error.setRequestId(requestId);
  }
}
