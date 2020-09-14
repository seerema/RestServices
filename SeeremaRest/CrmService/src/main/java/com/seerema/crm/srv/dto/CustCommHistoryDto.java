package com.seerema.crm.srv.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.seerema.crm.srv.jpa.model.CustCommHistory;
import com.seerema.shared.dto.AbstractEntityHistoryDto;
import com.seerema.shared.dto.CommMediaDto;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

/**
 * The persistent class for the cust_comm_history database table.
 * 
 */
@DtoFor(CustCommHistory.class)
public class CustCommHistoryDto extends AbstractEntityHistoryDto {

  @NotEmpty
  @ModelItem
  private String body;

  @NotNull
  @ModelItem
  private CommMediaDto commMedia;

  public CustCommHistoryDto() {
  }

  public String getBody() {
    return this.body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public CommMediaDto getCommMedia() {
    return commMedia;
  }

  public void setCommMedia(CommMediaDto commMedia) {
    this.commMedia = commMedia;
  }
}