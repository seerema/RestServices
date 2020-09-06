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

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seerema.shared.jpa.status.model.StatusHistory;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

/**
 * The persistent class for the status_history database table.
 *
 * @param <EntityExDto> Entity
 * @param <Status> Status
 */

@DtoFor(StatusHistory.class)
public class StatusHistoryDto extends AbstractEntityDto {

  @NotNull
  @ModelItem
  LocalDateTime created;

  @NotNull
  @ModelItem
  StatusDto status;

  @NotNull
  @ModelItem(setter = false)
  EntityExDto entity;

  @ModelItem(getter = false)
  @Size(max = 63)
  private String userName;

  public LocalDateTime getCreated() {
    return created;
  }

  @JsonProperty("created")
  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  @JsonIgnore
  public void setCreated(Timestamp created) {
    this.created = LocalDateTime
        .ofInstant(Instant.ofEpochMilli(created.getTime()), ZoneOffset.UTC);
  }

  public StatusDto getStatus() {
    return status;
  }

  public void setStatus(StatusDto status) {
    this.status = status;
  }

  public EntityExDto getEntity() {
    return entity;
  }

  public void setEntity(EntityExDto entity) {
    this.entity = entity;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
