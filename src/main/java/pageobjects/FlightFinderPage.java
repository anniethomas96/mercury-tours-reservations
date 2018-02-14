package pageobjects;

import common.BaseSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.util.List;

import static common.Utilities.getDayOfMonth;
import static common.Utilities.getMonthOfYear;

public class FlightFinderPage extends BaseSetup {

    private final Logger logger = LogManager.getLogger(FlightFinderPage.class);

    By oFlightTypeRoundTrip =By.xpath("//body//table//input[@name='tripType' and @value='roundtrip']");
    By oFlightTypeOneWay =By.xpath("//body//table//input[@name='tripType' and @value='oneway']");
    By oPassengersCount = By.name("passCount");
    By oDepartingFromLocation = By.name("fromPort");
    By oDepartingFromMonth = By.name("fromMonth");
    By oDepartingFromDay = By.name("fromDay");
    By oArrivingToLocation = By.name("toPort");
    By oReturnToMonth = By.name("toMonth");
    By oReturnToDay = By.name("toDay");
    By oServiceClassEconomy = By.xpath("//body//table//input[@name='servClass' and @value='Coach']");
    By oServiceClassBusiness = By.xpath("//body//table//input[@name='servClass' and @value='Business']");
    By oServiceClassFirst = By.xpath("//body//table//input[@name='servClass' and @value='First']");
    By oAirline = By.name("airline");
    By oContinue= By.xpath("//body//table//input[contains(@src,'continue.gif') and @name='findFlights']");

    //define location as an enum list to reduce errors
    public enum Location {
        LONDON("London"), SYDNEY("Sydney"), PARIS("Paris");
        private String loc;
        Location(String value) {
            this.loc = value;
        }
        public String value(){
            return loc;
        }
    }

    //define service class as an enum list to reduce errors
    public enum ServiceClass {
        ECONOMY("Economy Class"), FIRST("First Class"), BUSINESS("Business Class");
        private String service;
        ServiceClass(String value) {
            this.service = value;
        }
        public String value(){
            return service;
        }
    }

    //define prefered airline as an enum list to reduce errors
    public enum PreferedAirline {
        NO_PREFERENCE("No Preference"), BLUE_SKIES_AIRLINES("Blue Skies Airlines"),
        UNIFIED_AIRLINES("Unified Airlines"), PANGEA_AIRLINES("Pangea Airlines");
        private String airline;
        PreferedAirline(String value) {   this.airline = value;  }
        public String value() {             return airline;         }
    }

    //constructor for the class
    //parameters (1) - webdriver
     public FlightFinderPage(WebDriver driver) {
        this.driver = driver;
    }

    //explicit wait method to wait 10 seconds till roundtrip radio object is visible
    private void waitForPageToLoad(){
        new WebDriverWait(driver,10).until(
                ExpectedConditions.visibilityOfElementLocated(oFlightTypeRoundTrip));
    }


    //method to set flight preference - service class and airline
    //parameters(1) - String - service class can be Economy Or First or Business
    //parameters(2) - String - select from list of airlines available
    public void setFlightPreferences(ServiceClass serviceClass, PreferedAirline airline) {

        if (serviceClass.equals(ServiceClass.ECONOMY)){
            driver.findElement(oServiceClassEconomy).click();
            logger.debug("Service class - Economy selected");
        }else if(serviceClass.equals(ServiceClass.FIRST)){
            driver.findElement(oServiceClassFirst).click();
            logger.debug("Service class - First class selected");
        }else if(serviceClass.equals(ServiceClass.BUSINESS)){
            driver.findElement(oServiceClassBusiness).click();
            logger.debug("Service class - Business class selected");
        } else{
            logger.error("invalid service class provided [" + serviceClass + "]");
        }

        new Select(driver.findElement(oAirline)).selectByVisibleText(airline.value());
        logger.debug("Airline preference selected is : " + airline.value());
    }

    //method to set flight from date
    //parameters(1) - String - departure date
    private void setFlightFromDate(String fromDate) throws ParseException {
        String month =  getMonthOfYear(fromDate);
        String day = getDayOfMonth(fromDate);
        new Select(driver.findElement(oDepartingFromMonth)).selectByVisibleText(month);
        new Select(driver.findElement(oDepartingFromDay)).selectByVisibleText(day);
        logger.debug("set flight from month as [" + month +"] and date as [" + day +"]");
    }

    //method to set flight return date
    //parameters(1) - String - return date
    private void setFlightToDate(String toDate) throws ParseException {
        String month =  getMonthOfYear(toDate);
        String day = getDayOfMonth(toDate);
        new Select(driver.findElement(oReturnToMonth)).selectByVisibleText(getMonthOfYear(toDate));
        new Select(driver.findElement(oReturnToDay)).selectByVisibleText(getDayOfMonth(toDate));
        logger.debug("set flight to month as [" + month +"] and date as [" + day +"]");
    }

    private Boolean checkFlightToMonthDisplayed(){
        return driver.findElement(oReturnToMonth).isDisplayed();
    }

    private Boolean checkFlightToDayDisplayed(){
        return driver.findElement(oReturnToDay).isDisplayed();
    }
    //method to set passenger count
    //parameters(1) - int - number of passengers in booking
    private void setPassengersCount(int passengers){
         if (passengers > 4) {
             logger.error("invalid passenger count [" + passengers + "]. maximum passengers per booking is 4.");
         }
        logger.debug("set passenger count as " + passengers);
        new Select(driver.findElement(oPassengersCount)).selectByVisibleText(String.valueOf(passengers));
    }

    //method to set flight type as round trip or one way. it selects one way if input does not contain ROUND
    //parameters(1) - String - flight type required (ROUND or ONE WAY)
    private void setFlightType(String flightType){
         if (flightType.toUpperCase().contains("ROUND")) {
             driver.findElement(oFlightTypeRoundTrip).click();
             logger.debug("set flight type as Round Trip");
         } else {
            driver.findElement(oFlightTypeOneWay).click();
             logger.debug("set flight type as One Way");
        }
    }

    //method to set departure location
    //parameters(1) - Location Enum - departure location enum
    private void setFlighArrivalLocation(Location loc){
        logger.debug("set arrival location as " + loc);
        WebElement locationSelect = driver.findElement(oArrivingToLocation);
        locationSelect.findElement(By.cssSelector("option[value='" + loc.value() + "']")).click();
    }

    //method to set departure location
    //parameters(1) - Location Enum - departure location enum
    private void setFlighDepartureLocation(Location loc){
        logger.debug("set departure location as " + loc);
        WebElement locationSelect = driver.findElement(oDepartingFromLocation);
        locationSelect.findElement(By.cssSelector("option[value='" + loc.value() + "']")).click();
    }

     //exposed method to book a round trip flight
    //parameters(1) - int - passenger count (allowed is 1 to 4)
    //parameters(2) - Enum Location - departure location
    //parameters(3) - String - departure date
    //parameters(4) - Enum Location - arrival location
    //parameters(5) - String - return date
    public void setRoundTripFlightDetails(int passengers, Location fromLocation, String fromDate, Location toLocation, String toDate) throws ParseException {
        waitForPageToLoad();
        setFlightType("Round Trip");
        setPassengersCount(passengers);
        setFlighDepartureLocation(fromLocation);
        setFlightFromDate(fromDate);
        setFlighArrivalLocation(toLocation);
        setFlightToDate(toDate);
        logger.debug("round trip flight details entered");
    }

    //exposed method to book a one way trip flight. if return date fields are displayed there is an error generated
    //parameters(1) - int - passenger count (allowed is 1 to 4)
    //parameters(2) - Enum Location - departure location
    //parameters(3) - String - departure date
    //parameters(4) - Enum Location - arrival location
    public void setOneWayTripFlightDetails(int passengers, Location fromLocation, String fromDate, Location toLocation) throws Exception {
        waitForPageToLoad();
        setFlightType("One Way");
        setPassengersCount(passengers);
        setFlighDepartureLocation(fromLocation);
        setFlightFromDate(fromDate);
        setFlighArrivalLocation(toLocation);
        logger.debug("one way trip flight details entered");
        if (checkFlightToMonthDisplayed()  || checkFlightToDayDisplayed() ) {
            logger.error("Return date field selection is displayed. One way trip should not display Return date");
            throw new Exception("return date fields are displayed for one way trip");
        }
    }

    //method to click on continue button
    public void submit(){
        driver.findElement(oContinue).click();
        logger.debug("click continue button");
    }

}
