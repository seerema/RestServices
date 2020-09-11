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

import com.seerema.shared.jpa.status.model.EntityStatusHistory;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

/**
 * The dto class for the entity_status_history database table.
 *
 * @param <EntityExDto> Entity
 * @param <Status> Status
 */

@DtoFor(EntityStatusHistory.class)
public class EntityStatusHistoryDto extends AbstractEntityHistoryDto {

  @NotNull
  @ModelItem
  StatusDto status;

  public StatusDto getStatus() {
    return status;
  }

  public void setStatus(StatusDto status) {
    this.status = status;
  }
}
