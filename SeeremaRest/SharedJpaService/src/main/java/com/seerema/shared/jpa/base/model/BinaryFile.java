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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the binary_file database table.
 */

@Entity
@Table(name = "binary_file")
public class BinaryFile extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  private String description;

  @Column(name = "file_name")
  private String fileName;

  @Column(name = "file_size")
  private int fileSize;

  @Column(name = "file_type")
  private String fileType;

  //bi-directional many-to-one association to BfileCategory
  @ManyToOne(optional = false)
  @JoinColumn(name = "bfile_category_id")
  private BinaryFileCategory bfileCategory;

  public BinaryFile() {
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFileName() {
    return this.fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public int getFileSize() {
    return this.fileSize;
  }

  public void setFileSize(int fileSize) {
    this.fileSize = fileSize;
  }

  public String getFileType() {
    return this.fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public BinaryFileCategory getBfileCategory() {
    return this.bfileCategory;
  }

  public void setBfileCategory(BinaryFileCategory bfileCategory) {
    this.bfileCategory = bfileCategory;
  }

}