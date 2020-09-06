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

package com.seerema.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class with static Generic File utilities
 * 
 */
public class BaseUtils {

  // End Of File
  static final int EOF = -1;

  // Buffer size for I/O operations
  static final int DEFAULT_BUFFER_SIZE = 1024 * 8;

  /**
   * Check if directory exists and create if not
   * 
   * @param dir directory name
   * @return True if directory existed and False if not and was created
   * 
   * @throws IOException
   */
  public static boolean checkDirectory(String dir) throws IOException {
    File d = new File(dir);
    Boolean res = d.exists();
    if (!(res || d.mkdirs())) {
      res = null;
      throw new IOException(
          "Failed create directory '" + d.getAbsolutePath() + "");
    }
    return res;
  }

  /**
   * Check system path and if ~ present replace it with "user.home" system parameter
   * 
   * @param path Path to check
   * @return Path with first ~ character replaced
   */
  public static String checkSystemPath(String path) {
    return path.substring(0, 1).equals("~")
        // Replace ~ with user.home
        ? System.getProperty("user.home") + path.substring(1)
        // Use as is
        : path;
  }

  /**
   * Recursively delete directory content and directory itself
   * 
   * @param f File
   * 
   * @throws IOException
   */
  public static void delDirRecurse(File f) throws IOException {
    delDirRecurse(f, true);
  }

  /**
   * Recursively delete directory
   * 
   * @param f File
   * @param self Delete itself
   * 
   * @throws IOException
   */
  public static void delDirRecurse(File f, boolean self) throws IOException {
    File[] dlist = f.listFiles();
    if (dlist != null) {
      for (File d : dlist)
        if (d.isDirectory())
          delDirRecurse(d, true);
        else if (!d.delete())
          throw new IOException("Unable delete '" + d.getAbsolutePath() + "'");
    }

    if (self && !f.delete())
      throw new IOException("Unable delete '" + f.getAbsolutePath() + "'");
  }

  /**
   * Delete file
   * 
   * @param f File
   * 
   * @throws IOException
   */
  public static void deleteFile(File f) throws IOException {
    // 1. Delete directory
    if (!f.delete())
      throw new IOException(
          "Unable deleting file \\\"" + f.getAbsolutePath() + "\\\"");
  }

  /**
   * Get Bean getter name from property name
   * 
   * @param name property name
   * @return bean setter name
   */
  public static String getBeanGetter(String name) {
    return "get" + getBeanMethod(name);
  }

  /**
   * Get Bean setter name from property name
   * 
   * @param name property name
   * @return bean setter name
   */
  public static String getBeanSetter(String name) {
    return "set" + getBeanMethod(name);
  }

  /**
   * Get bean method from string with dots or undescore
   * 
   * @param name Input String
   * @return Bean method name
   */
  private static String getBeanMethod(String name) {
    String[] slist = name.split("[.|_]");

    String res = "";
    for (String sitem : slist)
      res += ucFirstChar(sitem);

    return res;
  }

  /**
   * Get Property name in snake_case from field name in camelCase
   * 
   * @param name field name
   * @return property name
   */
  public static String getPropertyName(String name, String delim) {
    // Split by capital letters
    StringBuilder str = new StringBuilder();
    List<String> slist = new ArrayList<String>();
    for (int i = 0; i < name.length(); i++) {
      char chr = name.charAt(i);
      if (chr == Character.toUpperCase(chr) && !(chr >= '0' && chr <= '9')) {
        slist.add(str.toString());
        str = new StringBuilder();
        str.append(Character.toLowerCase(chr));
      } else {
        str.append(chr);
      }
    }

    // Append last substring
    slist.add(str.toString());

    String res = slist.get(0);
    for (int i = 1; i < slist.size(); i++)
      res += ((i == 1) ? delim : "_") + ucLowerChar(slist.get(i));

    return res;
  }

  /**
   * Check string for non-empty conditions
   * 
   * @param s Input String
   * 
   * @return True if string is null or equals "" 
   */
  public static boolean isEmpty(String s) {
    return ((s == null) || s.equals(""));
  }

  /**
   * Check if string array is empty
   * 
   * @param arr Input Array of String
   * 
   * @return True if array is null or size equals 0
   */
  public static boolean isEmpty(String[] arr) {
    return ((arr == null) || (arr.length == 0));
  }

  /**
   * Read file into byte array
   * 
   * @param f File handle
   * @return byte array
   * 
   * @throws IOException
   */
  public static byte[] readFile(File f) throws IOException {
    return Files.readAllBytes(Paths.get(f.toURI()));
  }

  /**
   * Convert first character to lower case in input word
   * 
   * @param msg input word
   * @return word with first character lower case
   */
  public static String ucLowerChar(String msg) {
    char first = Character.toLowerCase(msg.charAt(0));
    return first + msg.substring(1);
  }

  /**
   * Convert first character to upper case in input word
   * 
   * @param msg input word
   * @return word with first character upper case
   */
  public static String ucFirstChar(String msg) {
    char first = Character.toUpperCase(msg.charAt(0));
    return first + msg.substring(1);
  }
}
