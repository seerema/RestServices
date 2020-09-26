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

package com.seerema.rest.auth.shared.common;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.util.ContextSelectorStaticBinder;
import ch.qos.logback.core.OutputStreamAppender;

/**
 * Test Constants
 *
 */
public class AuthSharedTestConstants {

  public static final String TEST_URL = "/foo";

  public static final String TEST_USER_NAME = "foo";

  public static final String TEST_USER_ROLES = "ROLE_SBS_USER,ROLE_FOO";

  public static final String TEST_MGR_NAME = "mgr";

  public static final String TEST_MGR_ROLES = "ROLE_SBS_MANAGER";

  public static final String TEST_ADMIN_NAME = "admin";

  public static final String TEST_ADMIN_ROLES = "ROLE_SBS_ADMIN";

  public static final ByteArrayOutputStream LOGS =
      new ByteArrayOutputStream(TEST_USER_NAME.length());

  public static final Logger LOG;

  static {
    // Build dynamic in-memory logger
    PatternLayoutEncoder encoder = new PatternLayoutEncoder();
    encoder.setPattern("%msg");
    encoder.setCharset(Charset.forName("UTF-8"));
    encoder.setContext(ContextSelectorStaticBinder.getSingleton()
        .getContextSelector().getDefaultLoggerContext());
    encoder.start();

    OutputStreamAppender<ILoggingEvent> appender =
        new OutputStreamAppender<ILoggingEvent>();
    appender.setName("streamAppender");
    appender.setContext(ContextSelectorStaticBinder.getSingleton()
        .getContextSelector().getDefaultLoggerContext());
    appender.setEncoder(encoder);
    appender.setOutputStream(LOGS);
    appender.start();

    ch.qos.logback.classic.Logger logger =
        ContextSelectorStaticBinder.getSingleton().getContextSelector()
            .getDefaultLoggerContext().getLogger("Test");
    logger.setLevel(Level.INFO);

    logger.addAppender(appender);

    LOG = LoggerFactory.getLogger("Test");
  }
}
