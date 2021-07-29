package com.blazeDemo.tests;

import com.blazeDemo.base.TestBase;
import com.blazeDemo.pages.FlightReservationFillUpPage;
import com.blazeDemo.pages.FlightReservationPage;
import com.blazeDemo.pages.HomePage;
import com.blazeDemo.pages.ReservationConfirmationPage;
import com.blazeDemo.util.TestUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestFlightReservation extends TestBase {
    HomePage homePage;
    FlightReservationPage flightReservationPage;
    FlightReservationFillUpPage flightReservationFillUpPage;
    ReservationConfirmationPage reservationConfirmationPage;

    public TestFlightReservation() {
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
     * This method will perform end to end test from home page till the user successfully books a flight and gets confirmed with a booking id
     * The first sub section will fill up departure and arrival city to reach flight selection page
     * The second sub section will choose a flight from the displayed result based on the airline name
     * And then it will perform all the data filling up part to get a booking ID
     * The last subsection will perform the closure activity to get booking ID
     */
    @Test
    public void testFlightReservation() {

        homePage.chooseArrivalCity(prop.getProperty("departureCity"));
        homePage.chooseDepartureCity(prop.getProperty("arrivalCity"));
        flightReservationPage = homePage.clickOnFindFlightsBtn();
        Assert.assertEquals(driver.getTitle(), prop.getProperty("flightReservationPageTitle"));
        Assert.assertEquals(flightReservationPage.getFlightReservationPageHeader(), "Flights from " + prop.getProperty("departureCity") + " to " + prop.getProperty("arrivalCity") + ":");

        flightReservationFillUpPage = flightReservationPage.selectFlightByAirlineName(prop.getProperty("airLineName"));
        Assert.assertEquals(driver.getTitle(), prop.getProperty("reservationFillUpPageTitle"));
        flightReservationFillUpPage.fillFlightDetails(prop.getProperty("name"), prop.getProperty("address"), prop.getProperty("city"), prop.getProperty("state"), prop.getProperty("zipCode"), prop.getProperty("cardType"), prop.getProperty("cardNum"), prop.getProperty("month"), prop.getProperty("year"), prop.getProperty("nameOnTheCard"), prop.getProperty("rememberMeFlag"));

        reservationConfirmationPage = flightReservationFillUpPage.clickOnPurchaseFlightBtn();
        Assert.assertEquals(driver.getTitle(), prop.getProperty("confirmationPageTitle"));
        Assert.assertTrue(reservationConfirmationPage.validateId());
        System.out.println("The Booking confirmation ID is : " + reservationConfirmationPage.getBookingId());
    }
}
