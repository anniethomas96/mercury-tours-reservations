package pageobjects;

import common.BaseSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class FlightSummaryPage extends BaseSetup {

    private final Logger logger = LogManager.getLogger(FlightSummaryPage.class);


    public FlightSummaryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getDepartureFlightRoute(){
        return driver.findElement(By.xpath("//font[contains(text(),'Summary')]/following::table//tr[1]/td[1]")).getText();
    }

    public String getDepartureFlightDate(){
        return driver.findElement(By.xpath("//font[contains(text(),'Summary')]/following::table//tr[1]/td[2]")).getText();
    }

    public String getDepartureFlightName(){
        return driver.findElement(By.xpath("//font[contains(text(),'Summary')]/following::table//tr[3]/td[1]")).getText();
    }

    public String getDepartureFlightClass(){
        return driver.findElement(By.xpath("//font[contains(text(),'Summary')]/following::table//tr[3]/td[2]")).getText();
    }

    public String getDepartureFlightCost(){
        return driver.findElement(By.xpath("//font[contains(text(),'Summary')]/following::table//tr[3]/td[3]")).getText();
    }


    public String getReturnFlightRoute(){
        return driver.findElement(By.xpath("//font[contains(text(),'Summary')]/following::table//tr[4]/td[1]")).getText();
    }

    public String getReturnFlightDate(){
        return driver.findElement(By.xpath("//font[contains(text(),'Summary')]/following::table//tr[4]/td[2]")).getText();
    }

    public String getReturnFlightName(){
        return driver.findElement(By.xpath("//font[contains(text(),'Summary')]/following::table//tr[6]/td[1]")).getText();
    }

    public String getReturnFlightClass(){
        return driver.findElement(By.xpath("//font[contains(text(),'Summary')]/following::table//tr[6]/td[2]")).getText();
    }

    public String getReturnFlightCost(){
        return driver.findElement(By.xpath("//font[contains(text(),'Summary')]/following::table//tr[6]/td[3]")).getText();
    }

    public String getFlightPassengerCount(){
        return driver.findElement(By.xpath("//font[contains(text(),'Summary')]/following::table//tr[7]/td[2]")).getText();
    }

    public String getFlightTaxes(){
        return driver.findElement(By.xpath("//font[contains(text(),'Summary')]/following::table//tr[8]/td[2]")).getText();
    }

    public String getFlightTotalPriceIncludingTaxes(){
        return driver.findElement(By.xpath("//font[contains(text(),'Summary')]/following::table//tr[9]/td[2]")).getText();
    }

    public void setPassengerDetail(int passengerNo, String name, String meal){
        String xpathFirstName = "//input[@name='passFirst" + (passengerNo-1) + "']";
        String xpathLastName = "//input[@name='passLast" + (passengerNo-1) + "']";
        String fname = name.split(" ")[0];
        String lname = name.split(" ")[1];
        driver.findElement(By.xpath(xpathFirstName)).sendKeys(fname);
        driver.findElement(By.xpath(xpathLastName)).sendKeys(lname);
        new Select(driver.findElement(By.name("pass." + (passengerNo-1) + ".meal"))).selectByVisibleText(meal);
    }

    public void setPaymentDetails(String cardtype,String cardno, String expiry, String name ){
        new Select(driver.findElement(By.name("creditCard"))).selectByVisibleText(cardtype);
        driver.findElement(By.name("creditnumber")).sendKeys(cardno);
        String expMonth = expiry.split(" ")[0];
        String expYear = expiry.split(" ")[1];
        new Select(driver.findElement(By.name("cc_exp_dt_mn"))).selectByVisibleText(expMonth);
        new Select(driver.findElement(By.name("cc_exp_dt_yr"))).selectByVisibleText(expYear);
        String fname = name.split(" ")[0];
        String mname,lname;
        if (name.split(" ").length >2){
            mname = name.split(" ")[1];
            lname = name.split(" ")[2];
        } else{
            mname ="";
            lname = name.split(" ")[1];
        }
        driver.findElement(By.name("cc_frst_name")).sendKeys(fname);
        driver.findElement(By.name("cc_mid_name")).sendKeys(mname);
        driver.findElement(By.name("cc_last_name")).sendKeys(lname);
    }

    public void setTicketlessTravel(){
        driver.findElement(By.name("ticketLess")).click();
    }

    public void setBillingAddress(String streetaddress, String city, String state, String zipcode, String country){

        driver.findElement(By.name("billAddress1")).clear();
        driver.findElement(By.name("billAddress1")).sendKeys(streetaddress);
        driver.findElement(By.name("billCity")).clear();
        driver.findElement(By.name("billCity")).sendKeys(city);
        driver.findElement(By.name("billState")).clear();
        driver.findElement(By.name("billState")).sendKeys(state);
        driver.findElement(By.name("billZip")).clear();
        driver.findElement(By.name("billZip")).sendKeys(zipcode);
        new Select(driver.findElement(By.name("billCountry"))).selectByVisibleText(country);

    }

    public void setDeliveryAddress(String streetaddress, String city, String state, String zipcode, String country){
        driver.findElement(By.name("delAddress1")).clear();
        driver.findElement(By.name("delAddress1")).sendKeys(streetaddress);
        driver.findElement(By.name("delCity")).clear();
        driver.findElement(By.name("delCity")).sendKeys(city);
        driver.findElement(By.name("delState")).clear();
        driver.findElement(By.name("delState")).sendKeys(state);
        driver.findElement(By.name("delZip")).clear();
        driver.findElement(By.name("delZip")).sendKeys(zipcode);
        new Select(driver.findElement(By.name("delCountry"))).selectByVisibleText(country);
    }

    public void purchase(){
        driver.findElement(By.xpath("//body//table//input[contains(@src,'purchase.gif') and @name='buyFlights']")).click();
    }
}
