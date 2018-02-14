package flights;

import common.BaseSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import pageobjects.*;
import static pageobjects.SelectFlightPage.*;
import static pageobjects.FlightFinderPage.*;
import static common.Utilities.addDays;
import static common.Utilities.getCurrentDate;

public class OneWayTripTest extends BaseSetup {

    private static final Logger logger = LogManager.getLogger(BookRoundTripTest.class);
    private WebDriver driver;
    private HomePage mercuryHome;
    private FlightFinderPage flightPage;
    private SelectFlightPage selectFlight;
    private FlightSummaryPage flightSummary;
    private FlightConfirmationPage flightConfirmation;

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            logger.info("Starting test: " + description.getMethodName());
        }
        protected void failed(Throwable e , Description description) {
            logger.error("Fail - Please check exception generated for test [{}]", description.getMethodName());
            mercuryHome.captureScreenshot(description.getMethodName());
            tearDown();
        }
        protected void succeeded(Description description) {
            logger.info("Completed successfully test: " + description.getMethodName());
            tearDown();
        }
    };

    @Before
    public void setUp() throws Exception {
        driver = setBrowserDriver();
        mercuryHome = new HomePage(driver);
        flightPage = new FlightFinderPage(driver);
        selectFlight = new SelectFlightPage(driver);
        flightSummary = new FlightSummaryPage(driver);
        flightConfirmation = new FlightConfirmationPage(driver);
    }

    public void tearDown() {
        driver.quit();
    }

    /**
     * Test to book a one way trip, ticketless, economy class flight for a single passenger
     * @throws Exception
     */
    @Test
    public void OneWayTrip_EconomyClass_SinglePassenger_TicketlessTravel() throws Exception {

        //test input data
        String fromDate = addDays(getCurrentDate(),10);
        String toDate = addDays(getCurrentDate(),30);
        Location fromLocation = Location.SYDNEY; Location toLocation = Location.LONDON;

        //login to mercury tours application
        mercuryHome.toursLogIn(getURL(), getUserName(),getPassword());

        //select round trip flight, flight preference and submit
        flightPage.setOneWayTripFlightDetails(1,fromLocation, fromDate, toLocation);

        //haven't proceeded further as one way cannot be tested
    }

}
