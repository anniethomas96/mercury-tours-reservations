package pageobjects;

import common.BaseSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlightFinderPage extends BaseSetup {

    private final Logger logger = LogManager.getLogger(FlightFinderPage.class);

    By oFlightFinderImage = By.xpath("//body//table//img[contains(@src,'mast_flightfinder.gif')]");
    By oFlightTypeRadio = By.name("tripType");
    By oPassengersCount = By.name("passCount");
    By oDepartingFromLocation = By.name("fromPort");
    By oDepartingFromMonth = By.name("fromMonth");
    By oDepartingFromDay = By.name("fromDay");
    By oArrivingToLocation = By.name("toPort");
    By oArrivingToMonth = By.name("toMonth");
    By oArrivingToDay = By.name("toDay");

    public FlightFinderPage(WebDriver driver) {
        this.driver = driver;
    }

}
