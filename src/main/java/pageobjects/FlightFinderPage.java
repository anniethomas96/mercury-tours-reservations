package pageobjects;

import common.BaseSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.ParseException;
import java.util.List;

import static common.Utilities.getDayOfMonth;
import static common.Utilities.getMonthOfYear;

public class FlightFinderPage extends BaseSetup {

    private final Logger logger = LogManager.getLogger(FlightFinderPage.class);

    By oFlightFinderImage = By.xpath("//body//table//img[contains(@src,'mast_flightfinder.gif')]");
    By oFlightTypeRoundTrip =By.xpath("//body//table//input[@name='tripType' and @value='roundtrip']");
    By oFlightTypeOneWay =By.xpath("//body//table//input[@name='tripType' and @value='oneway']");
    By oPassengersCount = By.name("passCount");
    By oDepartingFromLocation = By.name("fromPort");
    By oDepartingFromMonth = By.name("fromMonth");
    By oDepartingFromDay = By.name("fromDay");
    By oArrivingToLocation = By.name("toPort");
    By oArrivingToMonth = By.name("toMonth");
    By oArrivingToDay = By.name("toDay");
    By oServiceClassEconomy = By.xpath("//body//table//input[@name='servClass' and @value='Coach']");
    By oServiceClassBusiness = By.xpath("//body//table//input[@name='servClass' and @value='Business']");
    By oServiceClassFirst = By.xpath("//body//table//input[@name='servClass' and @value='First']");
    By oContinue= By.xpath("//body//table//input[contains(@src,'continue.gif') and @name='findFlights']");

     public FlightFinderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setFlightPreferences(String serviceClass, String airline) {

        if (serviceClass.equalsIgnoreCase("Economy Class")){
            driver.findElement(oServiceClassEconomy).click();
            logger.info("Service class - Economy selected");
        }else if(serviceClass.equalsIgnoreCase("First Class")){
            driver.findElement(oServiceClassFirst).click();
            logger.info("Service class - First class selected");
        }else if(serviceClass.equalsIgnoreCase("Business Class")){
            driver.findElement(oServiceClassBusiness).click();
            logger.info("Service class - Business class selected");
        }


        if (airline.equalsIgnoreCase("Blue Skies Airlines")){
            airline = "Blue Skies Airlines";
        }else if(airline.equalsIgnoreCase("Unified Airlines")){
            airline = "Unified Airlines";
        }else if(airline.equalsIgnoreCase("Pangea Airlines")){
            airline = "Pangea Airlines";
        } else if(airline.equalsIgnoreCase("No Preference")){
            airline = "No Preference";
        }

        new Select(driver.findElement(By.name("airline"))).selectByVisibleText(airline);
        logger.info("Airline preference selected is : " + airline);
    }

    private void setFlightFromDate(String fromDate) throws ParseException {
        String month =  getMonthOfYear(fromDate);
        String day = getDayOfMonth(fromDate);
        new Select(driver.findElement(By.name("fromMonth"))).selectByVisibleText(month);
        new Select(driver.findElement(By.name("fromDay"))).selectByVisibleText(day);
        logger.info("set flight from month as [" + month +"] and date as [" + day +"]");
    }

    private void setFlightToDate(String toDate) throws ParseException {
        String month =  getMonthOfYear(toDate);
        String day = getDayOfMonth(toDate);
        new Select(driver.findElement(By.name("toMonth"))).selectByVisibleText(getMonthOfYear(toDate));
        new Select(driver.findElement(By.name("toDay"))).selectByVisibleText(getDayOfMonth(toDate));
        logger.info("set flight to month as [" + month +"] and date as [" + day +"]");
    }

    public void setFlightDetails(int passengers, String fromLocation, String fromDate, String toLocation, String toDate) throws ParseException {
        driver.findElement(oFlightTypeRoundTrip).click();
        new Select(driver.findElement(By.name("passCount"))).selectByVisibleText(String.valueOf(passengers));
        new Select(driver.findElement(By.name("fromPort"))).selectByVisibleText(fromLocation);
        setFlightFromDate(fromDate);
        new Select(driver.findElement(By.name("toPort"))).selectByVisibleText(toLocation);
        setFlightToDate(toDate);
    }

    public void submit(){
        driver.findElement(oContinue).click();
    }

}
