package com.expt.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FindOutBrokenLinks {
    WebDriver driver;

    @Test
    public void preReq() throws FileNotFoundException, IOException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        String homeURL = "https://www.zlti.com/";
        String actualURL = " ";
        int responseCode = 200;
        HttpURLConnection huc = null; // This will be used to validate

        driver.get(homeURL);
        List<WebElement> elements = driver.findElements(By.tagName("a")); // get all elements by anchor tag

        for (WebElement webEle : elements) {
            actualURL = webEle.getAttribute("href");// getting the element and searching for the link embedded in that element

            //If the actualURL is null or Empty, skip the steps after this.
            if (actualURL == null || actualURL.isEmpty()) {
                System.out.println("actualURL is not configured for Anchor Tag");
                continue;
            }

            //the actualURL belongs to the main domain, continue. If it belongs to a third party domain, skip the steps after this.
            if (!actualURL.startsWith(homeURL)) {
                System.out.println("actualURL belongs to another domain, skipping it.");
                continue;
            }

            /*
            * Send HTTP request
               Methods in the HttpURLConnection class will send HTTP requests and capture the HTTP response code.
               Therefore, the output of openConnection() method (URLConnection) is type casted to HttpURLConnection.

               If testers set Request type as “HEAD” instead of “GET”, only headers will be returned, not the document body.

               When the tester invokes the connect() method,
               the actual connection to the URL is established and the HTTP request is sent.
               Use the getResponseCode() method to get the HTTP response code for the previously sent HTTP request.

            * */

            try {
                huc = (HttpURLConnection) (new URL(actualURL).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                responseCode = huc.getResponseCode();

                if (responseCode >= 400) {
                    System.out.println(actualURL + " is a broken link");
                } else {
                    System.out.println(actualURL + " is a valid link");
                }

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        driver.quit();
    }

}

