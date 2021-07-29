package com.blazeDemo.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTestFail {
    String str = "";
    Logger logger = Logger.getLogger(SampleTestFail.class);

    @Test
    public void failedTestCase() throws InterruptedException {
        logger.info("Sample Test Case Execution started");
        Assert.assertTrue(str.contains("Test"), "String mismatched");
        logger.info("Sample Test Case Execution completed");

    }
}
