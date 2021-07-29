package com.blazeDemo.tests;

import com.blazeDemo.base.TestBase;
import com.blazeDemo.pages.HomePage;
import com.blazeDemo.util.TestUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestHomePage extends TestBase {
    HomePage homePage;

    public TestHomePage() {
        super();
    }

    //This method will perform all pre-requisites activity
    @BeforeMethod
    public void setUp() {
        TestBase.intialization();
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

        driver.quit();
    }

    @Test
    public void validateHomePage() {
        Assert.assertEquals(driver.getTitle(), prop.getProperty("titleOfHomePage"), "Home Page Title is mismatching");
        Assert.assertEquals(homePage.getHeaderOfHomePage(), prop.getProperty("headerOfHomePage"), "Home Page Header is mismatching");
    }

}
