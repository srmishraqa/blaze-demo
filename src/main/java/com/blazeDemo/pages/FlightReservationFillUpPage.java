package com.blazeDemo.pages;

import com.blazeDemo.base.TestBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FlightReservationFillUpPage extends TestBase {

    @FindBy(xpath = "//input[@id='inputName']")
    WebElement name;

    @FindBy(xpath = "//input[@id='address']")
    WebElement address;

    @FindBy(xpath = "//input[@id='city']")
    WebElement city;

    @FindBy(xpath = "//input[@id='state']")
    WebElement state;

    @FindBy(xpath = "//input[@id='zipCode']")
    WebElement zipCode;

    @FindBy(xpath = "//select[@id='cardType']")
    WebElement cardType;

    @FindBy(xpath = "//input[@id='creditCardNumber']")
    WebElement creditCardNumber;

    @FindBy(xpath = "//input[@id='creditCardMonth']")
    WebElement creditCardMonth;

    @FindBy(xpath = "//input[@id='creditCardYear']")
    WebElement creditCardYear;

    @FindBy(xpath = "//input[@id='nameOnCard']")
    WebElement nameOnCard;

    @FindBy(xpath = "//input[@id='rememberMe']")
    WebElement rememberMeCheckBox;

    @FindBy(xpath = "//input[@value='Purchase Flight']")
    WebElement purchaseFlightBtn;

    public FlightReservationFillUpPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * This Method will fill up the the flight reservation form using following parameters
     *
     * @param name
     * @param streetAddress
     * @param city
     * @param state
     * @param zipCode
     * @param cardType
     * @param creditCardNumber
     * @param creditCardMonth
     * @param creditCardYear
     * @param nameOnTheCard
     * @param rememberMeFlag
     */
    public void fillFlightDetails(String name, String streetAddress, String city, String state, String zipCode, String cardType, String creditCardNumber, String creditCardMonth, String creditCardYear, String nameOnTheCard, String rememberMeFlag) {
        this.name.sendKeys(name);
        address.sendKeys(streetAddress);
        this.city.sendKeys(city);
        this.state.sendKeys(state);
        this.zipCode.sendKeys(zipCode);
        Select select = new Select(this.cardType);
        select.selectByVisibleText(cardType);
        this.creditCardNumber.sendKeys(creditCardNumber);
        this.creditCardMonth.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        this.creditCardMonth.sendKeys(creditCardMonth);
        this.creditCardYear.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        this.creditCardYear.sendKeys(creditCardYear);
        this.nameOnCard.sendKeys(nameOnTheCard);
        if (Boolean.parseBoolean(rememberMeFlag)) {
            rememberMeCheckBox.click();
        }
    }

    /**
     * This Method will submit the form to book a flight
     * This will return a booking confirmation
     */
    public ReservationConfirmationPage clickOnPurchaseFlightBtn() {
        purchaseFlightBtn.click();
        return new ReservationConfirmationPage();
    }


}
