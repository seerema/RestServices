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

package com.seerema.shared.jpa.base.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.seerema.shared.jpa.base.model.AbstractEntitySysName;

/**
 * The persistent class for the comm_media database table.
 * 
 */
@Entity
@Table(name = "comm_media")
public class CommMedia extends AbstractEntitySysName implements Serializable {
  private static final long serialVersionUID = 1L;

}