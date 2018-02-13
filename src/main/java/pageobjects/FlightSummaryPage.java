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

public class FlightSummaryPage extends BaseSetup {

    private final Logger logger = LogManager.getLogger(FlightSummaryPage.class);

    By oCreditCard = By.name("creditCard");
    By oCreditCardNumber = By.name("creditnumber");
    By oCreditCardExpiryMonth = By.name("cc_exp_dt_mn");
    By oCreditCardExpiryYear = By.name("cc_exp_dt_yr");
    By oCreditCardFirstName = By.name("cc_frst_name");
    By oCreditCardMiddleName = By.name("cc_mid_name");
    By oCreditCardLastName = By.name("cc_last_name");
    By oTicketlessTravel = By.name("ticketLess");
    String summaryTable = "//font[contains(text(),'Summary')]/following::table";
    By oDepartureFlightRoute = By.xpath(summaryTable + "//tr[1]/td[1]");
    By oDepartureFlightDate = By.xpath(summaryTable + "//tr[1]/td[2]");
    By oDepartureFlightName = By.xpath(summaryTable + "//tr[3]/td[1]");
    By oDepartureFlightClass = By.xpath(summaryTable + "//tr[3]/td[2]");
    By oDepartureFlightCost = By.xpath(summaryTable + "//tr[3]/td[3]");
    By oReturnFlightRoute = By.xpath(summaryTable + "//tr[4]/td[1]");
    By oReturnFlightDate = By.xpath(summaryTable + "//tr[4]/td[2]");
    By oReturnFlightName = By.xpath(summaryTable + "//tr[6]/td[1]");
    By oReturnFlightClass = By.xpath(summaryTable + "//tr[6]/td[2]");
    By oReturnFlightCost = By.xpath(summaryTable + "//tr[6]/td[3]");
    By oPassengerCount = By.xpath(summaryTable + "//tr[7]/td[2]");
    By oFlightTaxes = By.xpath(summaryTable + "//tr[8]/td[2]");
    By oFlightTotalPrice = By.xpath(summaryTable + "//tr[9]/td[2]");
    By oBillAddress1 = By.name("billAddress1");
    By oBillCity = By.name("billCity");
    By oBillState= By.name("billState");
    By oBillZip = By.name("billZip");
    By oBillCountry = By.name("billCountry");
    By oDeliveryAddress = By.name("delAddress1");
    By oDeliveryCity =By.name("delCity");
    By oDeliveryState = By.name("delState");
    By oDeliveryZipcode = By.name("delZip");
    By oDeliveryCountry = By.name("delCountry");
    By oSecurePurchase = By.xpath("//body//table//input[contains(@src,'purchase.gif') and @name='buyFlights']");


    public enum CardType {
        MASTERCARD("IK"), VISA("BA"), AMERICANEXPRESS("AX");
        private String card;
        CardType(String value) {
            this.card = value;
        }
        public String value(){
            return card;
        }
    }

    public enum Meal {
        NO_PREFERENCE("No preference"), DIABETIC("Diabetic"), HINDU("Hindu");
        private String mealpref;
        Meal(String value) {
            this.mealpref = value;
        }
        public String value(){
            return mealpref;
        }
    }

    //constructor for the class
    //parameters(1) - webdriver
    public FlightSummaryPage(WebDriver driver) {
        this.driver = driver;
    }

    //explicit wait method to wait 10 seconds till departure flights table is visible
    private void waitForPageToLoad(){
        new WebDriverWait(driver,10).until(
                ExpectedConditions.visibilityOfElementLocated(oCreditCard));
    }

    //method to retrieve departure flight route
    //output - String - flight route
    public String getDepartureFlightRoute(){
        waitForPageToLoad();
        String route = driver.findElement(oDepartureFlightRoute).getText();
        logger.debug("departure flight route is " + route);
        return route;
    }

    //method to retrieve departure flight date
    //output - String - flight date
    public String getDepartureFlightDate(){
        String fDate = driver.findElement(oDepartureFlightDate).getText();
        logger.debug("departure flight date is " + fDate);
        return fDate;
    }

    //method to retrieve departure flight name
    //output - String - flight name
    public String getDepartureFlightName(){
        String name = driver.findElement(oDepartureFlightName).getText();
        logger.debug("departure flight name is " + name);
        return name;
    }

    //method to retrieve departure flight class
    //output - String - flight class
    public String getDepartureFlightClass(){
        String fClass =driver.findElement(oDepartureFlightClass).getText();
        logger.debug("departure flight class is " + fClass);
        return fClass;
    }

    //method to retrieve return flight cost
    //output - String - flight cost
    public String getDepartureFlightCost(){
        String cost =driver.findElement(oDepartureFlightCost).getText();
        logger.debug("departure flight cost is " + cost);
        return cost;
    }

    //method to retrieve return flight route
    //output - String - flight route
    public String getReturnFlightRoute(){
        String route =driver.findElement(oReturnFlightRoute).getText();
        logger.debug("return flight route is " + route);
        return route;
    }
    //method to retrieve return flight date
    //output - String - flight date
    public String getReturnFlightDate(){
        String rDate =driver.findElement(oReturnFlightDate).getText();
        logger.debug("return flight date is " + rDate);
        return rDate;
    }
    //method to retrieve return flight name
    //output - String - flight name
    public String getReturnFlightName(){
        String name = driver.findElement(oReturnFlightName).getText();
        logger.debug("return flight name is " + name);
        return name;
    }
    //method to retrieve return flight class
    //output - String - flight class
    public String getReturnFlightClass(){
        String rClass = driver.findElement(oReturnFlightClass).getText();
        logger.debug("return flight class is " + rClass);
        return rClass;
    }

    //method to retrieve return flight cost
    //output - String - flight cost
    public String getReturnFlightCost(){
        String cost =driver.findElement(oReturnFlightCost).getText();
        logger.debug("return flight cost is " + cost);
        return cost;
    }

    //method to retrieve passenger count
    //output - String - passenger count
    public String getFlightPassengerCount(){
        String count =driver.findElement(oPassengerCount).getText();
        logger.debug("passenger count is " + count);
        return count;
    }

    //method to retrieve flight taxes
    //output - String - flight tax
    public String getFlightTaxes(){
        String tax =driver.findElement(oFlightTaxes).getText();
        logger.debug("flight tax is " + tax);
        return tax;
    }
    //method to retrieve flight total price inclusive tax
    //output - String - flight total price
    public String getFlightTotalPriceIncludingTaxes(){
        String totalprice =driver.findElement(oFlightTotalPrice).getText();
        logger.debug("total flight price inclusive tax is " + totalprice);
        return totalprice;
    }

    //method to set passenger name and meal preference
    //parameters(1) - int - index of passenger to be updated - starts from 1
    //parameters(2) - String - Full Name. does not accomodate middle name
    //parameters(3) - Meal Enum - meal preference
    public void setPassengerDetail(int passengerNo, String name, Meal meal){
        waitForPageToLoad();
        if (passengerNo < 1){
            logger.error("invalid passenger index [" + passengerNo + "]. index should start from 1");
        }
        String xpathFirstName = "//input[@name='passFirst" + (passengerNo-1) + "']";
        String xpathLastName = "//input[@name='passLast" + (passengerNo-1) + "']";
        String fname = name.split(" ")[0];
        String lname = name.split(" ")[1];
        logger.debug("enter passenger first name as " + fname);
        driver.findElement(By.xpath(xpathFirstName)).sendKeys(fname);
        logger.debug((" enter passenger surname as " + lname));
        driver.findElement(By.xpath(xpathLastName)).sendKeys(lname);
        logger.debug("select meal preference as " + meal);
        new Select(driver.findElement(By.name("pass." + (passengerNo-1) + ".meal"))).selectByVisibleText(meal.value());
    }

    //method to select card
    //parameters(1) - CardType Enum - card type e.g. Mastercard/Visa etc
    private void selectCardType(CardType cardType){
        logger.debug("enter card type as " + cardType.value());
        WebElement card = driver.findElement(oCreditCard);
        card.findElement(By.cssSelector("option[value='" + cardType.value() + "']")).click();
//        new Select(driver.findElement(oCreditCard)).selectByVisibleText(cardtype.value());
    }

    //method to set card payment details
    //parameters(1) - Enum CardType - card type e.g. Mastercard/Visa etc
    //parameters(2) - String - card no
    //parameters(3) - String - expiry date
    //parameters(4) - String - name on card. can contain middle name
    public void setPaymentDetails(CardType cardtype,String cardno, String expiry, String name ){
        selectCardType(cardtype);
        logger.debug("set card number as " + cardno);
        driver.findElement(oCreditCardNumber).sendKeys(cardno);
        String expMonth = expiry.split(" ")[0];
        String expYear = expiry.split(" ")[1];
        logger.debug("set expiry as " + expMonth + " " + expYear);
        new Select(driver.findElement(oCreditCardExpiryMonth)).selectByVisibleText(expMonth);
        new Select(driver.findElement(oCreditCardExpiryYear)).selectByVisibleText(expYear);
        String fname = name.split(" ")[0];
        String mname,lname;
        if (name.split(" ").length >2){
            mname = name.split(" ")[1];
            lname = name.split(" ")[2];
        } else{
            mname ="";
            lname = name.split(" ")[1];
        }
        logger.debug("set first name as " + fname);
        driver.findElement(oCreditCardFirstName).sendKeys(fname);
        logger.debug("set middle name as " + mname);
        driver.findElement(oCreditCardMiddleName).sendKeys(mname);
        logger.debug("set last name as " + lname);
        driver.findElement(oCreditCardLastName).sendKeys(lname);
    }


    //method to set ticketless travel checkbox
    //parameters(1) - String - contains ON/OFF
    public void setTicketlessTravel(String selector) throws Exception {
        //todo - check conditions
        if (!selector.equalsIgnoreCase("ON") && !selector.equalsIgnoreCase("OFF")){
            logger.error("invalid selector [" + selector +"] for ticketless travel");
            throw new Exception("invalid selector [" + selector +"] for ticketless travel");
            //flagging exception because the error would otherwise remain undetected. invalid selector data will be accepted
        }
        Boolean isSelected = driver.findElement(oTicketlessTravel).isSelected();
        logger.debug("ticketless travel should be set as " + selector);
        if ((selector.toUpperCase().contains("ON")) && !(isSelected)) {
            logger.debug("ticketless travel box will be checked");
            driver.findElement(oTicketlessTravel).click();
        }else if ((selector.toUpperCase().contains("OFF")) && (isSelected)) {
            logger.debug("ticketless travel box will be unchecked");
            driver.findElement(oTicketlessTravel).click();
        }

    }

    //method to set billing address of payment
    //parameters(1) - String - Street Address
    //parameters(2) - String - city name
    //parameters(3) - String - state
    //parameters(4) - String - Zipcode
    //parameters(5) - String - country (must exist in list in application)
    public void setBillingAddress(String streetaddress, String city, String state, String zipcode, String country){
        logger.debug("clear and enter address 1 data : " + streetaddress);
        driver.findElement(oBillAddress1).clear();
        driver.findElement(oBillAddress1).sendKeys(streetaddress);
        logger.debug("clear and enter city name : " + city);
        driver.findElement(oBillCity).clear();
        driver.findElement(oBillCity).sendKeys(city);
        logger.debug("clear and enter state name : " + state);
        driver.findElement(oBillState).clear();
        driver.findElement(oBillState).sendKeys(state);
        logger.debug("clear and enter zipcode : " + zipcode );
        driver.findElement(oBillZip).clear();
        driver.findElement(oBillZip).sendKeys(zipcode);
        logger.debug("select country [" + country + "] from list");
        new Select(driver.findElement(oBillCountry)).selectByVisibleText(country.toUpperCase());

    }


    //method to set delivery address of payment
    //parameters(1) - String - Street Address
    //parameters(2) - String - city name
    //parameters(3) - String - state
    //parameters(4) - String - Zipcode
    //parameters(5) - String - country (must exist in list in application)
    public void setDeliveryAddress(String streetaddress, String city, String state, String zipcode, String country){
        logger.debug("clear and enter address 1 data : " + streetaddress);
        driver.findElement(oDeliveryAddress).clear();
        driver.findElement(oDeliveryAddress).sendKeys(streetaddress);
        logger.debug("clear and enter city name : " + city);
        driver.findElement(oDeliveryCity).clear();
        driver.findElement(oDeliveryCity).sendKeys(city);
        logger.debug("clear and enter state name : " + state);
        driver.findElement(oDeliveryState).clear();
        driver.findElement(oDeliveryState).sendKeys(state);
        driver.findElement(oDeliveryZipcode).clear();
        logger.debug("clear and enter zipcode : " + zipcode );
        driver.findElement(oDeliveryZipcode).sendKeys(zipcode);
        logger.debug("select country [" + country + "] from list");
        new Select(driver.findElement(oDeliveryCountry)).selectByVisibleText(country.toUpperCase());
    }

    //method to click on secure purchase button
    public void purchase(){
        logger.debug("click on secure purchase button");
        driver.findElement(oSecurePurchase).click();
    }
}
