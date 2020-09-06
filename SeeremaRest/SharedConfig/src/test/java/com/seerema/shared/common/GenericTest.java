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

package com.seerema.shared.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

import org.junit.jupiter.api.Test;

import com.seerema.base.BaseUtils;
import com.seerema.shared.Constants;

/**
 * Test differen ID combinations
 * 
 */
public class GenericTest {

  public static final String[] BAD_ID_LIST = new String[] { "`", "!", "@", "#",
      "$", "%", "^", "&", "(", ")", "+", "=", "[", "]", "{", "}", "\"", "'" };

  public static final String[] BAD_ID_NAMES = new String[] { "@#~~&", "#*@``",
      "=&*$_", "($#~%", "%^=(_", "$`~`$", "#+_+(", "%!(^~", ")(%+_", "@`^()",
      "^_##_", "(+^=%", "=%^(_", "))=*%", "_#@`&", "*%=_%", "_*@((", "%%%##",
      "#((++", ")-^#=", ")%@+_", "*`(*+", "$!&_%", "##$&@", "-*(~(", "@)#!@",
      "~-=!-", "=^!+#", "=+_^+", "-=&+*", "*(_(-", "($~#-", "_=!%+", "*-#-^",
      "()&@!", "(+^*_", "_$_**", "_@)-#", "~~%%*", "~@!!(", "`#&=)", "*~=(!",
      "_*@_^", "!^%`%", "$)*_#", "``!-#", "-)_=^", "!@#_+", "@$#$=", ")~*(!",
      "_&(_*", "%(^)_", "))_=_", "*)+*_", "+$@-$", "&#_++", "^$+#)", "`&-@(",
      "@!-!+", "(=_$$", "^+^^~", "_^%~_", "%%&$@", ")~)%+", "(-=_+", "$^`(&",
      "&~~+@", "&++^@", "%%_*=", "^_-=)", "%(-_)", "~`^!_", "%*)+$", "$`@!&",
      "_%=@!", "+`&#@", "*~#$)", "$_*!=", "%_)@+", "#(&_-", "(!&=)", "#*&-^",
      "@_^=$", "^*%#)", "@)~#*", "=$@(`", "$=*#`", "_(##^", "^^@(+", "+$&@%",
      "$*$)&", "-`*+#", "*#~!`", "**`(&", "_$`+$", "#-#^*", "****`", "##@-=",
      "!@=%@", "=$%=$" };

  public static final String[] GOOD_ID_LIST = new String[] { "qWeRtY", "qq_QQ",
      "qq__QQ", "_q_", "_qq", "_qQ", "_qw", "_qW", "_Q_", "_Qq", "_QQ", "_Qw",
      "_QW", "_w_", "_wq", "_wQ", "_ww", "_wW", "_W_", "_Wq", "_WQ", "_Ww",
      "_WW", "q__", "q_q", "q_Q", "q_w", "q_W", "qq_", "qqq", "qqQ", "qqw",
      "qqW", "qQ_", "qQq", "qQQ", "qQw", "qQW", "qw_", "qwq", "qwQ", "qww",
      "qwW", "qW_", "qWq", "qWQ", "qWw", "qWW", "Q__", "Q_q", "Q_Q", "Q_w",
      "Q_W", "Qq_", "Qqq", "QqQ", "Qqw", "QqW", "QQ_", "QQq", "QQQ", "QQw",
      "QQW", "Qw_", "Qwq", "QwQ", "Qww", "QwW", "QW_", "QWq", "QWQ", "QWw",
      "QWW", "w__", "w_q", "w_Q", "w_w", "w_W", "wq_", "wqq", "wqQ", "wqw",
      "wqW", "wQ_", "wQq", "wQQ", "wQw", "wQW", "ww_", "wwq", "wwQ", "www",
      "wwW", "wW_", "wWq", "wWQ", "wWw", "wWW", "W__", "W_q", "W_Q", "W_w",
      "W_W", "Wq_", "Wqq", "WqQ", "Wqw", "WqW", "WQ_", "WQq", "WQQ", "WQw",
      "WQW", "Ww_", "Wwq", "WwQ", "Www", "WwW", "WW_", "WWq", "WWQ", "WWw",
      "WWW", "Qw-4", "_", "-", "A", "b", "5", "5-5", "Qwerty_Zxcvb-1" };

  public static final String[][] SETTER_NAMES =
      new String[][] { new String[] { "test.one", "setTestOne" },
          new String[] { "test.one_two", "setTestOneTwo" },
          new String[] { "param1", "setParam1" } };

  public static final String[][] PROP_NAMES =
      new String[][] { new String[] { "testOne", "test.one" },
          new String[] { "testOneTwo", "test.one_two" },
          new String[] { "param1", "param1" } };

  @Test
  public void testBadId() {
    for (String id : BAD_ID_LIST)
      assertEquals(false, Constants.ID_PATTERN.matcher(id).matches(),
          "ID: " + id);
  }

  @Test
  public void testGoodId() {
    for (String id : GOOD_ID_LIST)
      assertEquals(true, Constants.ID_PATTERN.matcher(id).matches(),
          "ID: " + id);
  }

  @Test
  public void testBeanSetterNames() {
    for (String[] name : SETTER_NAMES)
      assertEquals(name[1], BaseUtils.getBeanSetter(name[0]));
  }

  @Test
  public void testPropNames() {
    for (String[] name : PROP_NAMES)
      assertEquals(name[1], BaseUtils.getPropertyName(name[0], "."));
  }

  @Test
  public void testLocaleLang() {
    assertEquals(Locale.US.getLanguage(), "en");
    assertEquals(Locale.CANADA.getLanguage(), "en");
    assertEquals(Locale.CANADA_FRENCH.getLanguage(), "fr");
    assertEquals(new Locale("ru", "RU").getLanguage(), "ru");
    assertEquals(new Locale("es", "ES").getLanguage(), "es");
    assertEquals(new Locale("de", "DE").getLanguage(), "de");
  }
}
