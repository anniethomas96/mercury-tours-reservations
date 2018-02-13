package pageobjects;

import common.BaseSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class SelectFlightPage extends BaseSetup {

    private final Logger logger = LogManager.getLogger(SelectFlightPage.class);

    private String depTablexpath = "//form/table[1]";
    private String retTablexpath = "//form/table[2]";
    By oDepartureTable = By.xpath(depTablexpath);
    By oReturnTable = By.xpath(retTablexpath);
    By oContinue = By.xpath("//body//table//input[contains(@src,'continue.gif') and @name='reserveFlights']");

    //define flight names as an enum list to reduce errors
    public enum FlightNames {
        BLUE_SKIES_AIRLINES_360("Blue Skies Airlines 360"),
        BLUE_SKIES_AIRLINES_361("Blue Skies Airlines 361"),
        PANGEA_AIRLINES_362("Pangaea Airlines 362"),
        UNIFIED_AIRLINES_363("Unified Airlines 363"),
        BLUE_SKIES_AIRLINES_630("Blue Skies Airlines 630"),
        BLUE_SKIES_AIRLINES_631("Blue Skies Airlines 631"),
        PANGEA_AIRLINES_632("Pangea Airlines 632"),
        UNIFIED_AIRLINES_633("Unified Airlines 633");
        private String FlightNames;
        FlightNames(String value) {
            this.FlightNames = value;
        }
        public String value(){
            return FlightNames;
        }
    }

    //constructor for the class
    //parameters(1) - webdriver
    public SelectFlightPage(WebDriver driver) {
        this.driver = driver;
    }

    //explicit wait method to wait 10 seconds till departure flights table is visible
    private void waitForPageToLoad(){
        new WebDriverWait(driver,10).until(
                ExpectedConditions.visibilityOfElementLocated(oDepartureTable));
    }

    //method to select departure flight by flight name
    //parameters(1) - String - flight name in full (case sensitive)
    public void selectDeparture(String flightName){
        waitForPageToLoad();
        WebElement departureTable = driver.findElement(oDepartureTable);
        int rowno = getWebTableRowNoWithRowText(departureTable,flightName);
        if (rowno > 0){
            driver.findElement(By.xpath(depTablexpath + "//tr[" + (rowno-1) + "]//input")).click();
            logger.debug("departure flight selected as " + flightName);
        } else {
            logger.error("could not find departure flight name in the list [" + flightName + "]");
        }
    }

    //method to select departure flight by flight name
    //parameters(1) - Enum FlightNames - flight name in full (case sensitive)
    public void selectDeparture(FlightNames flightName){
        waitForPageToLoad();
        WebElement departureTable = driver.findElement(oDepartureTable);
        int rowno = getWebTableRowNoWithRowText(departureTable,flightName.value());
        if (rowno > 0){
            driver.findElement(By.xpath(depTablexpath + "//tr[" + (rowno-1) + "]//input")).click();
            logger.debug("departure flight selected as " + flightName);
        } else {
            logger.error("could not find departure flight name in the list [" + flightName + "]");
        }
    }

    //method to select return flight by flight name
    //parameters(1) - String - flight name in full (case sensitive)
    public void selectReturn(String flightName){

        WebElement returnTable = driver.findElement(oReturnTable);
        int rowno = getWebTableRowNoWithRowText(returnTable,flightName);
        if (rowno > 0){
            driver.findElement(By.xpath(retTablexpath + "//tr[" + (rowno-1) + "]//input")).click();
            logger.debug("return flight selected as " + flightName);
        } else {
            logger.error("could not find return flight name in the list [" + flightName + "]");
        }
    }


    //method to select return flight by flight name
    //parameters(1) - String - flight name in full (case sensitive)
    public void selectReturn(FlightNames flightName){

        WebElement returnTable = driver.findElement(oReturnTable);
        int rowno = getWebTableRowNoWithRowText(returnTable,flightName.value());
        if (rowno > 0){
            driver.findElement(By.xpath(retTablexpath + "//tr[" + (rowno-1) + "]//input")).click();
            logger.debug("return flight selected as " + flightName.value());
        } else {
            logger.error("could not find return flight name in the list [" + flightName.value() + "]");
        }
    }


    //method to retrieve departure flight time by flight name
    //parameters(1) - FlightNames Enum - flight name in full (case sensitive)
    //output - String - flight time in 24 hour format e.g. 13:20
    public String getDepartureFlightTime(FlightNames flightName){

        int rowno = getWebTableRowNoWithRowText(depTablexpath,flightName.value());
        if (rowno > 0){
            String depFlightTime = driver.findElement(By.xpath(depTablexpath + "//tr[" + (rowno-1) + "]/td[3]")).getText();
            logger.debug("departure flight time is :" + depFlightTime);
            return depFlightTime;
        } else {
            logger.error("could not find departure flight name in the list [" + flightName.value() + "]");
            return null;
        }
    }

    //method to retrieve departure flight cost by flight name
    //parameters(1) - FlightNames Enum - flight name in full (case sensitive)
    //output - String - flight cost with a $ prefix
    public String getDepartureFlightCost(FlightNames flightName){
        int rowno = getWebTableRowNoWithRowText(depTablexpath,flightName.value());
        if (rowno > 0){
            String cost = driver.findElement(By.xpath(depTablexpath + "//tr[" + (rowno) + "]/td[1]")).getText();
            cost = cost.substring(cost.indexOf('$'),(cost.indexOf('(')-1));
            logger.info("departure flight cost is " + cost);
            return cost;
        } else {
            logger.error("could not find departure flight name in the list [" + flightName.value() + "]");
            return null;
        }
    }

    //method to retrieve return flight time by flight name
    //parameters(1) - FlightNames Enum - flight name in full (case sensitive)
    //output - String - flight time in 24 hour format e.g. 13:20
    public String getReturnFlightTime(FlightNames flightName){
        int rowno = getWebTableRowNoWithRowText(retTablexpath,flightName.value());
        if (rowno > 0){
            String retFlightTime = driver.findElement(By.xpath(retTablexpath + "//tr[" + (rowno-1) + "]/td[3]")).getText();
            logger.info("time is :" + retFlightTime);
            return retFlightTime;
        } else {
            logger.error("could not find return flight name in the list [" + flightName.value() + "]");
            return null;
        }
    }

    //method to retrieve return flight cost by flight name
    //parameters(1) - FlightNames Enum - flight name in full (case sensitive)
    //output - String - flight cost with a $ prefix
    public String getReturnFlightCost(FlightNames flightName){
        int rowno = getWebTableRowNoWithRowText(retTablexpath,flightName.value());
        if (rowno > 0){
            String cost = driver.findElement(By.xpath(retTablexpath + "//tr[" + (rowno) + "]/td[1]")).getText();
            cost = cost.substring(cost.indexOf('$'),(cost.indexOf('(')-1));
            logger.info("return flight cost is :" + cost);
            return cost;
        } else {
            logger.error("could not find return flight name in the list [" + flightName.value() + "]");
            return null;
        }
    }

    //method to click on continue button to progress from the page
    public void submit(){
        logger.debug("click on continue button");
        driver.findElement(oContinue).click();
    }

    //method to get table row no containing search text
    //parameters(1) - String - xpath of table to be searched
    //parameters(2) - String - search text
    //output - int - row number
    private int getWebTableRowNoWithRowText(String tableXpath, String searchFor){
        logger.debug("look for search text [" + searchFor + "] in webtable");
        WebElement table = driver.findElement(By.xpath(tableXpath));
        return getWebTableRowNoWithRowText(table,searchFor);
    }

    //method to get table row no containing search text
    //parameters(1) - WebElement - webtable to be searched
    //parameters(2) - String - search text
    //output - int - row number
    private int getWebTableRowNoWithRowText(WebElement webtable, String searchFor){
        String result;
        List< WebElement > rows = webtable.findElements(By.tagName("tr"));
        for (int rnum=0;rnum<rows.size();rnum++){
            logger.debug("row number is " + rnum);
            result = "";
            List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));
            for (int cnum=0;cnum<columns.size();cnum++) {
                result = result + columns.get(cnum).getText();
            }
            logger.debug("row text is : " + result);
            if (result.contains(searchFor)) {
                logger.debug("found search text [" + searchFor + "] in row [" + rnum + "]");
                return rnum;
            }
        }
        logger.debug("could not find search text [" + searchFor + "] in webtable");
        return -1;
    }
}
