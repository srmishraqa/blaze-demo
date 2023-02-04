package com.expt.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestExpt {
    WebDriver driver;

    @BeforeClass
    public void preReq() throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
               System.getProperty("user.dir") + "/src/main/java/com/blazeDemo/config/config.properties");
        prop.load(fis);

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get(prop.getProperty("orangeUrl"));
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(prop.getProperty("username"));
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(prop.getProperty("password"));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".oxd-topbar-header-breadcrumb")).isDisplayed());


    }

    @Test
    public void searchUser() {
        System.out.println("login");
    }

}
