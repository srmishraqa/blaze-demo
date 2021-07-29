package com.blazeDemo.pages;

import com.blazeDemo.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends TestBase {

    @FindBy(xpath = "//h1")
    WebElement homePageHeader;

    @FindBy(xpath = "//a[starts-with(text(),'destination of the week')]")
    WebElement destinationOfTheWeekLink;

    @FindBy(xpath = "//select[@name='fromPort']")
    WebElement departureCityDropdown;

    @FindBy(xpath = "//select[@name='toPort']")
    WebElement arrivalCityDropdown;

    @FindBy(xpath = "//input[@value='Find Flights']")
    WebElement findFlightsButton;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public void chooseDepartureCity(String departureCity) {
        Select select = new Select(arrivalCityDropdown);
        select.selectByVisibleText(departureCity);
    }

    public void chooseArrivalCity(String arrivalCity) {
        Select select = new Select(departureCityDropdown);
        select.selectByVisibleText(arrivalCity);
    }

    public FlightReservationPage clickOnFindFlightsBtn() {
        findFlightsButton.click();
        return new FlightReservationPage();
    }

    public DestinationOfTheWeekPage clickOnDestinationOfTheWeekLink() {
        destinationOfTheWeekLink.click();
        return new DestinationOfTheWeekPage();
    }

    public String getHeaderOfHomePage() {
        return homePageHeader.getText();
    }

}
