package com.seerema.crm.srv.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.seerema.shared.dto.EntityExDto;
import com.seerema.srv.shared.annotations.ModelItem;

public class CustomerDto extends EntityExDto {

  @NotNull
  @ModelItem(getter = false)
  List<CustCommHistoryDto> custCommHistories;

  public CustomerDto() {
  }

  public CustomerDto(EntityExDto dto) {
    // Copy EntityExDto
    setId(dto.getId());
    setUser(dto.getUser());
    setName(dto.getName());
    setParent(dto.getParent());
    setStatus(dto.getStatus());
    setEntity(dto.getEntity());
    setFieldCat(dto.getFieldCat());
    setEntityFields(dto.getEntityFields());
    setOwnerHistories(dto.getOwnerHistories());
    setStatusHistories(dto.getStatusHistories());
  }

  public List<CustCommHistoryDto> getCustCommHistories() {
    return custCommHistories;
  }

  public void setCustCommHistories(List<CustCommHistoryDto> custCommHistories) {
    this.custCommHistories = custCommHistories;
  }
}
