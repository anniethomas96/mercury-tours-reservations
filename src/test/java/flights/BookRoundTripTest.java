package flights;

import common.BaseSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static common.Utilities.*;
import static org.hamcrest.CoreMatchers.containsString;

public class BookRoundTripTest extends BaseSetup {

    private static final Logger logger = LogManager.getLogger(BookRoundTripTest.class);
    WebDriver driver;
    private HomePage mercuryHome;
    private FlightFinderPage flightPage;
    private SelectFlightPage selectFlight;
    private FlightSummaryPage flightSummary;
    private FlightConfirmationPage flightConfirmation;

    @Before
    public void setUp() throws Exception {
        driver = setBrowserDriver();
        mercuryHome = new HomePage(driver);
        flightPage = new FlightFinderPage(driver);
        selectFlight = new SelectFlightPage(driver);
        flightSummary = new FlightSummaryPage(driver);
        flightConfirmation = new FlightConfirmationPage(driver);
    }

    @After
    public void tearDown() throws Exception {
        //driver.quit();
    }

    @Test
    public void BookRoundTrip_FirstClass() throws InterruptedException, ParseException {

        String fromDate = addDays(getCurrentDate(),10);
        String toDate = addDays(getCurrentDate(),30);
        String fromLocation = "Sydney";
        String toLocation = "London";
        String depFlightName = "Unified Airlines 363";
        String retFlightName = "Blue Skies Airlines 631";
        String billingStreetAddress = "10 Kent Street";
        String deliveryStreetAddress = "15 George Street";
        String city = "Riverdale";
        String zipcode = "94098";
        String country = "UNITED STATES";
        String state = "CA";
        String billerName = "Marc Jacob";


        mercuryHome.toursLogIn(getUserName(),getPassword());
        Thread.sleep(15000);
        flightPage.setFlightDetails(1,fromLocation, fromDate, toLocation, toDate);
        flightPage.setFlightPreferences("First Class","No Preference");
        flightPage.submit();
        selectFlight.selectDeparture(depFlightName);
        selectFlight.selectReturn(retFlightName);
        String dep_cost = selectFlight.getDepartureFlightCost(depFlightName);
        String dep_time = selectFlight.getDepartureFlightTime(depFlightName);
        String ret_cost = selectFlight.getReturnFlightCost(retFlightName);
        String ret_time = selectFlight.getReturnFlightTime(retFlightName);
        selectFlight.submit();
        Thread.sleep(3000);

        String deptext = changeDateFormat(fromDate,"d-MMMM-yyyy","M/d/YYYY");
        String rettext = changeDateFormat(toDate,"d-MMMM-yyyy","M/d/YYYY");

        Assert.assertThat("Departing Flight Summary : check route",flightSummary.getDepartureFlightRoute(),
                containsString(fromLocation + " to " + toLocation));
        Assert.assertThat("Departing Flight Summary : check date",flightSummary.getDepartureFlightDate(),
                containsString(deptext));
        Assert.assertThat("Departing Flight Summary : check flight name",flightSummary.getDepartureFlightName(),
                containsString(depFlightName));
        Assert.assertThat("Departing Flight Summary : check class",flightSummary.getDepartureFlightClass(),
                containsString("First"));

        Assert.assertThat("Return Flight Summary : check route",flightSummary.getReturnFlightRoute(),
                containsString(toLocation + " to " + fromLocation));
        Assert.assertThat("Return Flight Summary : check date",flightSummary.getReturnFlightDate(),
                containsString(rettext));
        Assert.assertThat("Return Flight Summary : check flight name",flightSummary.getReturnFlightName(),
                containsString(retFlightName));
        Assert.assertThat("Return Flight Summary : check class",flightSummary.getReturnFlightClass(),
                containsString("First"));


        String taxes = flightSummary.getFlightTaxes();
        String totalprice = flightSummary.getFlightTotalPriceIncludingTaxes();

        flightSummary.setPassengerDetail(1, "Ariana Grande", "No preference");
//        flightSummary.setPassengerDetail(2, "Jennifer Lopez", "No preference");
        flightSummary.setPaymentDetails("MasterCard", "1235 5689 12234", "12 2010", billerName );
        flightSummary.setBillingAddress(billingStreetAddress, city, state, zipcode, country);
        flightSummary.setTicketlessTravel();
        flightSummary.setDeliveryAddress(deliveryStreetAddress, city, state, zipcode, country);
        flightSummary.purchase();

        //verify flight departure details in the flight summary page
        String depDetails = flightConfirmation.getDepartureFlightDetails();
        depDetails = depDetails.replace("\n","");
        logger.info(depDetails);
        Assert.assertThat("Departing Flight : check route",depDetails,
                containsString(fromLocation + " to " + toLocation));
        Assert.assertThat("Departing Flight : check flight class",depDetails,
                containsString("First"));

        deptext = deptext + " @ " + dep_time + " w/ " + depFlightName;
        logger.info(deptext);
        Assert.assertThat("Departing Flight : check flight cost",depDetails,
                containsString(dep_cost + " each"));
        Assert.assertThat("Departing Flight : check flight date, cost and name",depDetails,
                containsString(deptext));

        //verify flight return details in the flight summary page
        String retDetails = flightConfirmation.getReturnFlightDetails();
        retDetails = retDetails.replace("\n","");
        logger.info(retDetails);
        Assert.assertThat("Return Flight : check route",retDetails,
                containsString(toLocation + " to " + fromLocation));
        Assert.assertThat("Return Flight : check flight class",retDetails,
                containsString("First"));

        rettext = rettext + " @ " + ret_time + " w/ " + retFlightName;
        logger.info(rettext);
        Assert.assertThat("Return Flight : check flight cost",retDetails,
                containsString(ret_cost + " each"));
        Assert.assertThat("Return Flight : check flight date, cost and name",retDetails,
                containsString(rettext));

        //verify passengers
        String passengers = flightConfirmation.getPassengersDetails();
        Assert.assertThat("Passengers",passengers,
                containsString("1 passenger"));

        logger.info("tax " + flightConfirmation.getTotalTaxes());
        logger.info("price " + flightConfirmation.getTotalPrice());

        //verify billing details
        String billing = flightConfirmation.getBillingAddressDetails();
        Assert.assertThat("Billing Address - Name",billing,
                containsString(billerName));
        Assert.assertThat("Billing Address - Street address",billing,
                containsString(billingStreetAddress));
        Assert.assertThat("Billing Address - city, state. zipcode",billing,
                containsString(city + ", " + state + ", " + zipcode));

        //verify ticketless travel
        Assert.assertThat("Ticketless travel - ",flightConfirmation.getDeliveryAddressHeader(),
                containsString("N/A for Ticketless Travel"));

        //verify delivery address details
        String delivery = flightConfirmation.getDeliveryAddressDetails();
        Assert.assertThat("Delivery Address - Street address",delivery,
                containsString(deliveryStreetAddress));
        Assert.assertThat("Delivery Address - city, state. zipcode",delivery,
                containsString(city + ", " + state + ", " + zipcode));

//verify taxes and total price is same as in summary page
        Assert.assertThat("Total Taxes",flightConfirmation.getTotalTaxes(),
                containsString(taxes));
        Assert.assertThat("Total Price including Taxes",flightConfirmation.getTotalPrice(),
                containsString(totalprice));

    }
}


