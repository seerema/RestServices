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

package com.seerema.shared;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;

/**
 * Files Filter for list of extensions
 * 
 */
public class ExtListFileFilter implements FilenameFilter {

  // Minimal length of file with extension
  private int _mlen = Integer.MAX_VALUE;

  private final HashSet<String> _extl = new HashSet<String>();

  public ExtListFileFilter(String[] extList) {
    for (int i = 0; i < extList.length; i++) {
      String ext = extList[i];
      int len = ext.length();

      _extl.add(ext);
      if (len < _mlen)
        _mlen = len;
    }

    _mlen += 2;
  }

  @Override
  public boolean accept(File dirName, String fileName) {
    int len = fileName.length();

    if (len < _mlen)
      return false;

    // Extract file extension
    int pos = fileName.lastIndexOf(".");
    if (pos <= 0 || (len - pos) < (_mlen - 2))
      return false;

    String ext = fileName.substring(pos + 1);

    return _extl.contains(ext);
  }
}