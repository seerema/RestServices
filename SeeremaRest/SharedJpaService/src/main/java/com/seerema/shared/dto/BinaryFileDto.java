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

import javax.validation.constraints.Size;

import com.seerema.shared.jpa.base.model.BinaryFile;
import com.seerema.srv.shared.annotations.DtoFor;
import com.seerema.srv.shared.annotations.ModelItem;

/**
 * BinaryFile DTO
 */
@DtoFor(BinaryFile.class)
public class BinaryFileDto extends AbstractEntityDto {

  @ModelItem
  @Size(max = 50)
  private String fileName;

  private Long fileSize = 0L;

  @ModelItem
  @Size(max = 25)
  private String fileType;

  @ModelItem
  @Size(max = 255)
  private String description;

  public BinaryFileDto() {
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public Long getFileSize() {
    return fileSize;
  }

  public void setFileSize(Long fileSize) {
    this.fileSize = fileSize;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
