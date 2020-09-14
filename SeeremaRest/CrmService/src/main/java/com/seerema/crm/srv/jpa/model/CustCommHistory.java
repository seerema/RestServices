package com.seerema.crm.srv.jpa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.seerema.shared.jpa.base.model.CommMedia;
import com.seerema.shared.jpa.status.model.AbstractEntityHistory;

/**
 * The persistent class for the cust_comm_history database table.
 * 
 */
@Entity
@Table(name = "cust_comm_history")
public class CustCommHistory extends AbstractEntityHistory
    implements Serializable {
  private static final long serialVersionUID = 1L;

  @Lob
  private String body;

  // uni-directional many-to-one association to CommMedia
  @ManyToOne(optional = false)
  private CommMedia commMedia;

  public String getBody() {
    return this.body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public CommMedia getCommMedia() {
    return commMedia;
  }

  public void setCommMedia(CommMedia commMedia) {
    this.commMedia = commMedia;
  }
}