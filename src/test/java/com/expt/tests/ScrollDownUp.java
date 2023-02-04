package com.expt.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ScrollDownUp {
    WebDriver driver;

    @Test
    public void scrollDown() throws InterruptedException {
        // scroll by element locator

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get("https://en.wikipedia.org/wiki/List_of_countries_by_GDP_(nominal)");
        WebElement sriLanka = driver.findElement(By.xpath("//a[@title='Economy of Sri Lanka']"));
        WebElement india = driver.findElement(By.xpath("//a[@title='Economy of India']"));

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView()",sriLanka);
        Thread.sleep(3000);

        jse.executeScript("arguments[0].scrollIntoView()",india);
        Thread.sleep(3000);

        driver.quit();
    }
}
