package com.blazeDemo.tests;

import com.blazeDemo.base.TestBase;
import com.blazeDemo.pages.DestinationOfTheWeekPage;
import com.blazeDemo.pages.HomePage;
import com.blazeDemo.util.TestUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestDestinationOfTheWeekPage extends TestBase {
    HomePage homePage;
    DestinationOfTheWeekPage destinationOfTheWeekPage;

    public TestDestinationOfTheWeekPage() {
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

    /**
     * This Method will validate that whether the user has reached the correct Page or not
     * The expected title is getting called from properties file and it is getting compared here against the actual title
     */
    @Test
    public void testDestinationOfTheWeekPage() {
        destinationOfTheWeekPage = homePage.clickOnDestinationOfTheWeekLink();
        Assert.assertEquals(driver.getTitle(), prop.getProperty("destinationOfTheWeekPageTitle"), "Destination Page Title is mismatching");
    }

}
