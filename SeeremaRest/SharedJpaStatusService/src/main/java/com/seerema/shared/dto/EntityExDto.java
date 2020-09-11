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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seerema.shared.jpa.status.model.EntityEx;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

/**
 * The Abstract Entity Status DTO
 *
 * @param <T1> EntityDto
 * @param <StatusDto> StatusDto
 * @param <EntityStatusHistoryDto> StatusHistoryDto
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
  private List<EntityStatusHistoryDto> statusHistories;

  @ModelItem(getter = false)
  private List<EntityUserHistoryDto> ownerHistories;

  @ModelItem(getter = false)
  private UserDto user;

  public StatusDto getStatus() {
    return status;
  }

  public void setStatus(StatusDto status) {
    this.status = status;
  }

  public List<EntityStatusHistoryDto> getStatusHistories() {
    return statusHistories;
  }

  public void setStatusHistories(List<EntityStatusHistoryDto> statusHistories) {
    this.statusHistories = statusHistories;
  }

  public void addStatusHistory(EntityStatusHistoryDto statusHistory) {
    if (statusHistories == null)
      statusHistories = new ArrayList<>();

    statusHistories.add(statusHistory);
  }

  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
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

  public List<EntityUserHistoryDto> getOwnerHistories() {
    return ownerHistories;
  }

  public void setOwnerHistories(List<EntityUserHistoryDto> ownerHistories) {
    this.ownerHistories = ownerHistories;
  }

  public void addOwnerHistory(EntityUserHistoryDto ownerHistory) {
    if (ownerHistories == null)
      ownerHistories = new ArrayList<>();

    ownerHistories.add(ownerHistory);
  }
}