package com.blazeDemo.pages;

import com.blazeDemo.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightReservationPage extends TestBase {

    final String beforeAirlineXpath = "//td[text()='";
    final String afterAirlineXpath = "']//preceding-sibling::td//input";

    @FindBy(xpath = "//h3")
    WebElement flightReservationPageHeader;

    public FlightReservationPage() {
        PageFactory.initElements(driver, this);
    }

    public String getFlightReservationPageHeader() {
        return flightReservationPageHeader.getText();
    }

    /**
     * This method will choose the flight based on the airline which user chooses
     * It will use the following parameter and will return to Reservation fill up page
     * The xpath is handled in a dynamic way so that it can create the exact x-path on run time
     * @param airLineName
     * @return
     */
    public FlightReservationFillUpPage selectFlightByAirlineName(String airLineName) {
        driver.findElement(By.xpath(beforeAirlineXpath + airLineName + afterAirlineXpath)).click();
        return new FlightReservationFillUpPage();
    }
}
