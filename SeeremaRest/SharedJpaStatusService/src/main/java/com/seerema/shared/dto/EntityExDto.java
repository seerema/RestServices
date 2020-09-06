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

package com.seerema.shared.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

/**
 * The Abstract Entity Status DTO
 *
 * @param <T1> EntityDto
 * @param <StatusDto> StatusDto
 * @param <StatusHistoryDto> StatusHistoryDto
 * @param <EntityFieldDto> EntityFieldDto
 * @param <FieldCategoryDto> FieldCategoryDto
 */
@DtoFor(EntityEx.class)
public class EntityExDto extends EntityDto {

  @ModelItem("dbEntity")
  @JsonIgnore
  private EntityDto entity;

  // Status can be set by manager. For new entity the new status is not required
  // and injects directly
  @ModelItem
  private StatusDto status;

  @ModelItem(getter = false)
  private List<StatusHistoryDto> statusHistories;

  @ModelItem(getter = false)
  @Size(max = 63)
  private String userName;

  public StatusDto getStatus() {
    return status;
  }

  public void setStatus(StatusDto status) {
    this.status = status;
  }

  public List<StatusHistoryDto> getStatusHistories() {
    return statusHistories;
  }

  public void setStatusHistories(List<StatusHistoryDto> statusHistories) {
    this.statusHistories = statusHistories;
  }

  public void addStatusHistory(StatusHistoryDto statusHistory) {
    if (statusHistories == null)
      statusHistories = new ArrayList<>();

    statusHistories.add(statusHistory);
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public EntityDto getEntity() {
    // Add empty
    EntityDto dto = new EntityDto();
    dto.setId(getId());
    return dto;
  }

  public void setEntity(EntityDto entity) {
    this.entity = entity;
  }

}