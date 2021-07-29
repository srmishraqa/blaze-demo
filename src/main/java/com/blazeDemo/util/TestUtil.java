package com.blazeDemo.util;

import java.io.File;
import java.io.IOException;

import com.blazeDemo.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class TestUtil extends TestBase {

    // Constants to use throughout the framework
    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICITLY_WAIT = 20;


    /**
     * This Method will take screenshot when any test gets failed
     * @throws IOException
     */

    public static void takeScreenshotAtEndOfTest() throws IOException {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File(System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".jpg"));
    }

}
