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

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import java.util.List;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.launcher.listeners.TestExecutionSummary.Failure;

import com.seerema.shared.rest.config.TestRestConstants;

/**
 * Multi Thread Test Runner
 * 
 */
public class GenericRestMultiTestRunner implements Runnable {

  private ITestInfoProvider _tp;

  public GenericRestMultiTestRunner(ITestInfoProvider testProvider) {
    _tp = testProvider;
  }

  @Override
  public void run() {
    try {
      TestRestConstants.start.await();
    } catch (InterruptedException e) {
      fail(e.getMessage());
    }

    try {
      LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
          .request().selectors(selectClass(_tp.getTestClass())).build();
      Launcher launcher = LauncherFactory.create();
      SummaryGeneratingListener listener = new SummaryGeneratingListener();
      launcher.registerTestExecutionListeners(listener);

      launcher.execute(request);
      TestExecutionSummary res = listener.getSummary();

      long tcnt = res.getTestsFoundCount();
      if (tcnt == 0)
        throw new Exception("No tests found in " + _tp.getClass());

      long fcnt = res.getTestsFailedCount();

      if (fcnt != 0) {
        TestRestConstants.errCount++;
        System.err.println(fcnt + " errors detected.");
        List<Failure> list = res.getFailures();
        for (int i = 0; i < list.size(); i++)
          System.err.println("\t ERROR #" + i + " - " +
              list.get(i).getException().getClass().getName() + ": " +
              list.get(i).getException() + ";");

        System.out.println();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      TestRestConstants.errCount++;
    }

    TestRestConstants.done.countDown();
  }

}
