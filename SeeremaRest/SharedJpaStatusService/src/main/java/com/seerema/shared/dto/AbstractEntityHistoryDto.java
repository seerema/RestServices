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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seerema.srv.shared.annotations.ModelItem;

/**
 * The persistent class for the entity_status_history database table.
 *
 * @param <EntityExDto> Entity
 * @param <Status> Status
 */

public class AbstractEntityHistoryDto extends AbstractEntityDto {

  @NotNull
  @ModelItem
  private LocalDateTime created;

  @NotNull
  @ModelItem(setter = false)
  private EntityExDto entity;

  @ModelItem(getter = false)
  private UserDto user;

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

  public EntityExDto getEntity() {
    return entity;
  }

  public void setEntity(EntityExDto entity) {
    this.entity = entity;
  }

  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }
}
