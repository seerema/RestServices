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
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the bfile_category database table.
 */

@Entity
@Table(name = "bfile_category")
public class BinaryFileCategory extends AbstractEntitySysName
    implements Serializable {
  private static final long serialVersionUID = 1L;

  //bi-directional many-to-one association to BinaryFile
  @OneToMany(mappedBy = "bfileCategory")
  private List<BinaryFile> binaryFiles;

  public BinaryFileCategory() {
  }

  public List<BinaryFile> getBinaryFiles() {
    return this.binaryFiles;
  }

  public void setBinaryFiles(List<BinaryFile> binaryFiles) {
    this.binaryFiles = binaryFiles;
  }

  public BinaryFile addBinaryFile(BinaryFile binaryFile) {
    getBinaryFiles().add(binaryFile);
    binaryFile.setBfileCategory(this);

    return binaryFile;
  }

  public BinaryFile removeBinaryFile(BinaryFile binaryFile) {
    getBinaryFiles().remove(binaryFile);
    binaryFile.setBfileCategory(null);

    return binaryFile;
  }

}