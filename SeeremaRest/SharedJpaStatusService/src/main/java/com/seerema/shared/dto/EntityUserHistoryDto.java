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

import javax.validation.constraints.NotNull;

import com.seerema.shared.jpa.status.model.EntityUserHistory;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

/**
 * The dto class for the entity_user_history database table.
 *
 * @param <EntityExDto> Entity
 * @param <Status> Status
 */

@DtoFor(EntityUserHistory.class)
public class EntityUserHistoryDto extends AbstractEntityHistoryDto {

  @NotNull
  @ModelItem
  UserDto owner;

  public UserDto getOwner() {
    return owner;
  }

  public void setOwner(UserDto owner) {
    this.owner = owner;
  }
}
