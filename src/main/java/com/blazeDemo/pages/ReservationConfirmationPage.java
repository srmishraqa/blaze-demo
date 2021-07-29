package com.blazeDemo.pages;

import com.blazeDemo.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReservationConfirmationPage extends TestBase {

    @FindBy(xpath = "//td[text()='Id']//following-sibling::td")
    WebElement bookingId;

    public ReservationConfirmationPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * This Method will validate Booking Id is displayed or not
     */
    public boolean validateId() {
        return bookingId.isDisplayed();
    }

    /**
     *This method will return the booking ID of the flight
     */
    public String getBookingId() {
        return bookingId.getText();
    }
}
