package com.blazeDemo.tests;

import com.blazeDemo.base.TestBase;
import com.blazeDemo.pages.FlightReservationFillUpPage;
import com.blazeDemo.pages.FlightReservationPage;
import com.blazeDemo.pages.HomePage;
import com.blazeDemo.pages.ReservationConfirmationPage;
import com.blazeDemo.util.ExcelConnector;
import com.blazeDemo.util.TestUtil;
import org.apache.log4j.Logger;
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

    Logger logger = Logger.getLogger(TestFlightReservation.class);
    ExcelConnector excelConnector = new ExcelConnector(System.getProperty("user.dir")
            + "/src/main/java/com/blazeDemo/config/data.xlsx");

    public TestFlightReservation() {
        super();
    }

    //This method will perform all pre-requisites activity
    @BeforeMethod
    public void setUp() {
        TestBase.intialization();
        logger.info("TestBase Class Initialization method executed successfully");
        logger.info("Opening Chrome Browser successfully");
        logger.info("Test Execution Started");
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
        logger.info("Test Execution Completed");
        driver.quit();
        logger.info("Quitting the browser");
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

        homePage.chooseArrivalCity(excelConnector.getCellData
                ("flight data", "departureCity", 2));

        homePage.chooseDepartureCity(excelConnector.getCellData
                ("flight data", "arrivalCity", 2));

        flightReservationPage = homePage.clickOnFindFlightsBtn();

        Assert.assertEquals(driver.getTitle(), excelConnector.getCellData
                ("validation points", "flightReservationPageTitle", 2));
        logger.info("Page Title asserted successfully");

        Assert.assertEquals(flightReservationPage.getFlightReservationPageHeader(), "Flights from " +
                excelConnector.getCellData("flight data", "departureCity", 2) + " to " +
                excelConnector.getCellData("flight data", "arrivalCity", 2) + ":");
        logger.info("Page Header asserted successfully");

        flightReservationFillUpPage = flightReservationPage.selectFlightByAirlineName
                (excelConnector.getCellData("flight data", "airLineName", 2));
        Assert.assertEquals(driver.getTitle(),
                excelConnector.getCellData("validation points", "reservationFillUpPageTitle", 2));
        logger.info("Form Page Title asserted successfully");

        flightReservationFillUpPage.fillFlightDetails(
                excelConnector.getCellData("flight data", "name", 2),
                excelConnector.getCellData("flight data", "address", 2),
                excelConnector.getCellData("flight data", "city", 2),
                excelConnector.getCellData("flight data", "state", 2),
                excelConnector.getCellData("flight data", "zipCode", 2),
                excelConnector.getCellData("flight data", "cardType", 2),
                excelConnector.getCellData("flight data", "cardNum", 2),
                excelConnector.getCellData("flight data", "month", 2),
                excelConnector.getCellData("flight data", "year", 2),
                excelConnector.getCellData("flight data", "nameOnTheCard", 2),
                excelConnector.getCellData("flight data", "rememberMeFlag", 2));
        logger.info("Form Page data filled successfully");

        reservationConfirmationPage = flightReservationFillUpPage.clickOnPurchaseFlightBtn();
        Assert.assertEquals(driver.getTitle(),
                excelConnector.getCellData("validation points", "confirmationPageTitle", 2));
        logger.info("Flight Booking is successful");
        logger.info("Confirmation Title verified successfully");
        Assert.assertTrue(reservationConfirmationPage.validateId());
        System.out.println("The Booking confirmation ID is : " + reservationConfirmationPage.getBookingId());
    }
}
