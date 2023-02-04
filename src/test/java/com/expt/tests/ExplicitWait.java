package com.expt.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExplicitWait {
    WebDriver driver;

    @Test
    public void explicitWait() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.get("https://www.ebay.com.au/");
        WebElement electronicsHyperLink = driver.findElement(By.xpath("//li/a[contains(@href,'/b/Electronics') and text()='Electronics']"));
        //WebElement homeAppliances = driver.findElement(By.xpath("(//li/a[contains(@href,'Home-Appliances') and text() = 'Home Appliances'])[1]"));

        Actions actions = new Actions(driver);
        actions.moveToElement(electronicsHyperLink).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("(//li/a[contains(@href,'Home-Appliances') and text() = 'Home Appliances'])[1]"))).click();

        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("Home-Appliances"),"URL Does not contain Home-Appliances");
        Thread.sleep(2000);

        driver.quit();
    }
}
