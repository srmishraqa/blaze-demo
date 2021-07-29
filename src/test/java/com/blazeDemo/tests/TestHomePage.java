package com.blazeDemo.tests;

import com.blazeDemo.base.TestBase;
import com.blazeDemo.pages.HomePage;
import com.blazeDemo.util.ExcelConnector;
import com.blazeDemo.util.TestUtil;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestHomePage extends TestBase {
    HomePage homePage;

    Logger logger = Logger.getLogger(TestHomePage.class);
    ExcelConnector excelConnector = new ExcelConnector(System.getProperty("user.dir")
            + "/src/main/java/com/blazeDemo/config/data.xlsx");


    public TestHomePage() {
        super();
    }

    //This method will perform all pre-requisites activity
    @BeforeMethod
    public void setUp() {
        TestBase.intialization();
        logger.info("TestBase Class Initialization method executed successfully");
        logger.info("Opening Chrome Browser successfully");
        logger.info("Test Execution Started");
        homePage = new HomePage();
    }

    /**
     * This method will perform all post execution activities
     * It will take screenshot in case there are any failures
     */

    @AfterMethod
    public void tearDown(ITestResult res) throws IOException {
        if (res.getStatus() == ITestResult.FAILURE || res.getStatus() == ITestResult.SUCCESS) {
            TestUtil.takeScreenshotAtEndOfTest();
        }
        logger.info("Test Execution Completed");
        driver.quit();
        logger.info("Quitting the browser");
    }

    @Test
    public void validateHomePage() {
        Assert.assertEquals(driver.getTitle(),
                excelConnector.getCellData("validation points", "titleOfHomePage", 2)
                , "Home Page Title is mismatching");
        logger.info("Home Page Title verified successfully");
        Assert.assertEquals(homePage.getHeaderOfHomePage(),
                excelConnector.getCellData("validation points", "headerOfHomePage", 2),
                "Home Page Header is mismatching");
    }

}
