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

package com.seerema.shared.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Manifest;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;

import com.seerema.shared.service.RestInfo;

/**
 * Web App Info class
 * 
 */

public class AppRestInfo implements RestInfo {

  @Autowired
  @Qualifier("log")
  private Logger _log;

  // Non Maven flag
  private boolean _non_maven;

  // Detected version
  private String _version;

  // App name
  private String _name;

  public static final String MANIFEST_PATH = "/META-INF/MANIFEST.MF";

  /**
   * Default constructor
   * 
   * @param name Application name
   */
  public AppRestInfo(String name) {
    _name = name;
  }

  /**
   * Open and check version from Manifest.mf
   * 
   * @param ctx
   *          Servlet Context
   * @throws IOException
   */
  @PostConstruct
  public void init() {
    String classPath = this.getClass()
        .getResource(this.getClass().getSimpleName() + ".class").toString();

    InputStream is = null;
    try {
      if (classPath.startsWith("jar")) {
        is = new URL(classPath.substring(0, classPath.lastIndexOf("!") + 1) +
            MANIFEST_PATH).openStream();
      } else {
        // Find correct class-path
        Enumeration<URL> resources =
            getClass().getClassLoader().getResources("META-INF/MANIFEST.MF");
        File dir = new File("");
        while (resources.hasMoreElements()) {
          URL url = resources.nextElement();
          if (url.toString().startsWith("file:" + dir.getAbsolutePath())) {
            is = url.openStream();
            break;
          }
        }
      }
    } catch (IOException e) {
      _log.error("Error reading Version from MANIFEST.MF: " + e.getMessage());
    }

    _non_maven = (is == null);

    if (!_non_maven) {
      Manifest m;
      try {
        m = new Manifest(is);
        _version = m.getMainAttributes().getValue("Implementation-Version");
      } catch (IOException e) {
        _version = "Unknown";
      }
    }

    _log.info("Starting Web Service " + (_name != null ? _name : "local") +
        (hasMavenVersion() ? " v." + _version : ""));
  }

  public boolean hasMavenVersion() {
    return !(_non_maven || StringUtils.isEmpty(_version));
  }

  private String getVersion() {
    return _version;
  }

  @Override
  public String getBuildVersion() {
    return getVersion();
  }

  @Override
  public String getRestVersion() {
    return getVersion();
  }
}
