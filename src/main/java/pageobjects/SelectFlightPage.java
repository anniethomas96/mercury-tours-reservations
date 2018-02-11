package pageobjects;

import common.BaseSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SelectFlightPage extends BaseSetup {

    private final Logger logger = LogManager.getLogger(SelectFlightPage.class);

    private String depTablexpath = "//form/table[1]";
    private String retTablexpath = "//form/table[2]";

    public SelectFlightPage(WebDriver driver) {
        this.driver = driver;
    }


    public void selectDeparture(String flightName){
////table//b/font[contains(text(),'RETURN')]//following::input[2]/following::b

        WebElement departureTable = driver.findElement(By.xpath("//form/table[1]"));
        int rowno = getWebTableRowNoWithRowText(departureTable,flightName);
        if (rowno > 0){
            driver.findElement(By.xpath("//form/table[1]//tr[" + (rowno-1) + "]//input")).click();
        }

  //      driver.findElement(By.xpath("//table//b/font[contains(text(),'DEPART')]/following::input[@type='radio'][" + index + "]")).click();
    }

    public void selectReturn(String flightName){

        WebElement returnTable = driver.findElement(By.xpath("//form/table[2]"));
        int rowno = getWebTableRowNoWithRowText(returnTable,flightName);
        if (rowno > 0){
            driver.findElement(By.xpath("//form/table[2]//tr[" + (rowno-1) + "]//input")).click();
        }
    //    driver.findElement(By.xpath("//table//b/font[contains(text(),'RETURN')]/following::input[@type='radio'][" + index + "]")).click();

    }


    public String getDepartureFlightTime(String flightName){

        int rowno = getWebTableRowNoWithRowText(depTablexpath,flightName);
        if (rowno > 0){
            logger.info("time is :" + driver.findElement(By.xpath(depTablexpath + "//tr[" + (rowno-1) + "]/td[3]")).getText());
            return driver.findElement(By.xpath(depTablexpath + "//tr[" + (rowno-1) + "]/td[3]")).getText();
        } else {
            return null;
        }
    }

    public String getDepartureFlightCost(String flightName){
        String cost;

        int rowno = getWebTableRowNoWithRowText(depTablexpath,flightName);
        if (rowno > 0){
            cost = driver.findElement(By.xpath(depTablexpath + "//tr[" + (rowno) + "]/td[1]")).getText();
            cost = cost.substring(cost.indexOf('$'),(cost.indexOf('(')-1));
            logger.info("cost is :" + cost + ":");
            return cost;
        } else {
            return null;
        }
    }


    public String getReturnFlightTime(String flightName){

        int rowno = getWebTableRowNoWithRowText(retTablexpath,flightName);
        if (rowno > 0){
            logger.info("time is :" + driver.findElement(By.xpath(retTablexpath + "//tr[" + (rowno-1) + "]/td[3]")).getText());
            return driver.findElement(By.xpath(retTablexpath + "//tr[" + (rowno-1) + "]/td[3]")).getText();
        } else {
            return null;
        }
    }

    public String getReturnFlightCost(String flightName){
        String cost;

        int rowno = getWebTableRowNoWithRowText(retTablexpath,flightName);
        if (rowno > 0){
            cost = driver.findElement(By.xpath(retTablexpath + "//tr[" + (rowno) + "]/td[1]")).getText();
            cost = cost.substring(cost.indexOf('$'),(cost.indexOf('(')-1));
            logger.info("cost is :" + cost + ":");
            return cost;
        } else {
            return null;
        }
    }

    public void submit(){
        driver.findElement(By.xpath("//body//table//input[contains(@src,'continue.gif') and @name='reserveFlights']")).click();
    }

    private int getWebTableRowNoWithRowText(String tableXpath, String searchFor){
        WebElement table = driver.findElement(By.xpath(tableXpath));
        return getWebTableRowNoWithRowText(table,searchFor);
    }

    private int getWebTableRowNoWithRowText(WebElement webtable, String sSearchFor){

        String result;

        List< WebElement > rows = webtable.findElements(By.tagName("tr"));
        for (int rnum=0;rnum<rows.size();rnum++){
//            logger.info("row number is " + rnum);
            result = "";
            List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));
            for (int cnum=0;cnum<columns.size();cnum++) {
                result = result + columns.get(cnum).getText();
            }
            //result = result.replaceAll("\n","");
//            logger.info("result is : " + result);
            if (result.contains(sSearchFor)) {
                return rnum;
            }
        }
        return -1;
    }
}
