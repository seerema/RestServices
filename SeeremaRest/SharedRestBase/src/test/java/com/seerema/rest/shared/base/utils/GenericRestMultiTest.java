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

package com.seerema.rest.shared.base.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.seerema.rest.shared.base.common.RestBaseConstants;
import com.seerema.shared.common.TestConstants;
import com.seerema.shared.rest.config.TestRestConstants;

/**
 * Abstract class for MultiThread Web Test
 * 
 */
public abstract class GenericRestMultiTest implements ITestInfoProvider {

  @Autowired
  public void setTestRestTemplate(TestRestTemplate restTemplate)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
      IllegalAccessException {
    setStaticValue(RestBaseConstants.REST_TEMPLATE_FIELD_NAME, restTemplate);
  }

  @Autowired
  public void setLogger(Logger log) throws NoSuchFieldException,
      SecurityException, IllegalArgumentException, IllegalAccessException {
    setStaticValue(RestBaseConstants.LOG_FIELD_NAME, log);
  }

  @LocalServerPort
  public void setLogger(int port) throws NoSuchFieldException,
      SecurityException, IllegalArgumentException, IllegalAccessException {
    setStaticValue(RestBaseConstants.LOCAL_PORT_FIELD_NAME, port);
  }

  @Test
  public void testConcurrentLoad() throws InterruptedException {
    int tnum = getThreadNum();
    TestRestConstants.done = new CountDownLatch(tnum);
    System.out.println("Concurrent " + getTestClass().getSimpleName() +
        " test for " + tnum + " Threads");

    long wtime = getWaitTime();

    // Create threads
    for (int i = 0; i < tnum; i++)
      new Thread(new GenericRestMultiTestRunner(this)).start();

    Thread.sleep(1000);
    TestRestConstants.start.countDown();

    if (!TestRestConstants.done.await(wtime, TimeUnit.MILLISECONDS))
      assertEquals(0, TestRestConstants.done.getCount(),
          TestRestConstants.done.getCount() + " thread(s) hasn't been " +
              "completed during " + wtime + " " + TimeUnit.MILLISECONDS);

    assertEquals(0, TestRestConstants.errCount, "Total " +
        TestRestConstants.errCount + " thread(s) completed with errors");
  }

  @Override
  public int getThreadNum() {
    return TestConstants.THREAD_NUM;
  }

  @Override
  public long getWaitTime() {
    return TestConstants.WAIT_TIME * 10;
  }

  protected void setStaticValue(String name, Object value)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
      IllegalAccessException {
    Class<?> clazz = getTestClass();

    // Find static parameter inside the class
    Field field = clazz.getDeclaredField(name);
    // Assign it
    field.setAccessible(true);
    field.set(clazz, value);
  }
}
