package pageobjects;

import common.BaseSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlightConfirmationPage extends BaseSetup{
    private final Logger logger = LogManager.getLogger(FlightConfirmationPage.class);


    public FlightConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void backToFlights(){
        driver.findElement(By.xpath("//body//table//img[contains(@src,'backtoflights.gif')]")).click();
    }

    public String getDepartureFlightDetails(){
        return driver.findElement(By.xpath("//font[contains(text(),'Departing')]/following::tr")).getText();
    }

    public String getReturnFlightDetails(){
        return driver.findElement(By.xpath("//font[contains(text(),'Returning')]/following::tr")).getText();
    }

    public String getPassengersDetails(){
        return driver.findElement(By.xpath("//b[contains(text(),'Passengers')]/following::tr")).getText();
    }

    public String getBillingAddressDetails(){
        return driver.findElement(By.xpath("//font[contains(text(),'Billed To')]/following::tr")).getText();
    }

    public String getDeliveryAddressDetails(){
        return driver.findElement(By.xpath("//font[contains(text(),'Billed To')]/following::tr")).getText();
    }

    public String getDeliveryAddressHeader(){
        return driver.findElement(By.xpath("//font[contains(text(),'Billed To')]")).getText();
    }

    public String getTotalTaxes(){

        return driver.findElement(By.xpath("//font[contains(text(),'Total') and contains(text(),'Taxes:')]/following::b")).getText();
    }

    public String getTotalPrice(){
        return driver.findElement(By.xpath("//font[contains(text(),'Total') and contains(text(),'Price (including taxes):')]/following::b")).getText();
    }


    public void backToHome(){
        driver.findElement(By.xpath("//body//table//img[contains(@src,'home.gif')]")).click();
    }

    public void logout(){
        driver.findElement(By.xpath("//body//table//img[contains(@src,'Logout.gif')]")).click();
    }


}
