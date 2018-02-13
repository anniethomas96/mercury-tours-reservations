package pageobjects;

import common.BaseSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightConfirmationPage extends BaseSetup{
    private final Logger logger = LogManager.getLogger(FlightConfirmationPage.class);

    By oBackToHome = By.xpath("//body//table//img[contains(@src,'home.gif')]");
    By oLogout = By.xpath("//body//table//img[contains(@src,'Logout.gif')]");
    By oBackToFlights = By.xpath("//body//table//img[contains(@src,'backtoflights.gif')]");
    By oDeliveryAddressHeader =By.xpath("//b[contains(text(),'Delivery Address')]") ;
    By oTotalTaxes =By.xpath("//font[contains(text(),'Total') and contains(text(),'Taxes:')]/following::b") ;
    By oTotalFlightCost = By.xpath("//font[contains(text(),'Total') and contains(text(),'Price (including taxes):')]/following::b");
    By oBillingAddress = By.xpath("//b[contains(text(),'Billed To')]/following::tr");
    By oDeliveryAddress = By.xpath("//b[contains(text(),'Delivery Address')]/following::tr");
    By oDepFlightDetails = By.xpath("//font[contains(text(),'Departing')]/following::tr");
    By oRetFlightDetails = By.xpath("//font[contains(text(),'Returning')]/following::tr");
    By oPassengerDetails = By.xpath("//b[contains(text(),'Passengers')]/following::tr");

    ////font[contains(text(),'itinerary has been booked')]

    //constructor for the class
    //parameters(1) - webdriver
    public FlightConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    //explicit wait method to wait 10 seconds till departure flights table is visible
    private void waitForPageToLoad(){
        new WebDriverWait(driver,10).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//table//img[contains(@src,'backtoflights.gif')]")));
    }

    //method to return to flights finder page
    public void backToFlights(){
        logger.debug("click on Back To Flights button");
        driver.findElement(oBackToFlights).click();
    }



    //method to retrieve departure flight details
    //output - String - flight details
    public String getDepartureFlightDetails(){
        waitForPageToLoad();
        String details =driver.findElement(oDepFlightDetails).getText();
        logger.debug("departure flight details is:" + details);
        return details;
    }

    //method to retrieve return flight details
    //output - String - flight details
    public String getReturnFlightDetails(){
        String details =driver.findElement(oRetFlightDetails).getText();
        logger.debug("return flight details :" + details);
        return details;
    }

    //method to retrieve passenger details
    //output - String - passenger details
    public String getPassengersDetails(){
        waitForPageToLoad();
        String passengers = driver.findElement(oPassengerDetails).getText();
        logger.debug("passenger details are :" + passengers);
        return passengers;
    }

    //method to retrieve billing details
    //output - String - billing details
    public String getBillingAddressDetails(){
        String address = driver.findElement(oBillingAddress).getText();
        logger.debug("billing address is :" + address);
        return address;
    }

    //method to retrieve delivery address details
    //output - String - delivery address
    public String getDeliveryAddressDetails(){
        String address = driver.findElement(oDeliveryAddress).getText();
        logger.debug("delivery address is :" + address);
        return address;
    }


    //method to retrieve return delivery address header (ticketless check)
    //output - String - delivery address header
    public String getDeliveryAddressHeader(){
        String header =driver.findElement(oDeliveryAddressHeader).getText();
        logger.debug("delivery address header is :" + header);
        return header;
    }

    //method to retrieve total taxes
    //output - String - total taxes
    public String getTotalTaxes(){
        String tax = driver.findElement(oTotalTaxes).getText();
        logger.debug("total taxes is" + tax);
        return tax;
    }
    //method to retrieve total flight price
    //output - String - total flight price
    public String getTotalPrice(){
        String price =  driver.findElement(oTotalFlightCost).getText();
        logger.debug("total flight cost is " + price);
        return price;
    }

    //method to navigate to home
    public void backToHome(){
        logger.debug("click on Back to Home button");
        driver.findElement(oBackToHome).click();
    }

    //method to logout from application
    public void logout(){
        logger.debug("logout from the application");
        driver.findElement(oLogout).click();
        new WebDriverWait(driver,10).until(
                ExpectedConditions.titleIs("Sign-on: Mercury Tours"));

    }

    //method to check text present in page. wait for page to load
    public Boolean checkTextPresentInPage(String searchFor){
        waitForPageToLoad();
        return super.checkTextPresentInPage(searchFor);
    }
}
