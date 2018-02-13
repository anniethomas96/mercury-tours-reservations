package flights;

import common.BaseSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.*;

import java.text.ParseException;

import static common.Utilities.*;
import static org.hamcrest.CoreMatchers.containsString;
import static pageobjects.FlightSummaryPage.*;
import static pageobjects.FlightSummaryPage.Meal;
import static pageobjects.SelectFlightPage.*;
import static pageobjects.FlightFinderPage.*;

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

    //Test to book a round trip, ticketless, first class flight for a single passenger
    @Test
    public void BookRoundTrip_FirstClass_SinglePassenger_TicketlessTravel() throws Exception {

        //test input data
        String fromDate = addDays(getCurrentDate(),10);
        String toDate = addDays(getCurrentDate(),30);
        Location fromLocation = Location.SYDNEY; Location toLocation = Location.LONDON;
        ServiceClass serviceClass = ServiceClass.FIRST;
        PreferedAirline airline = PreferedAirline.NO_PREFERENCE;
        FlightNames depFlightName = FlightNames.UNIFIED_AIRLINES_363;
        FlightNames retFlightName = FlightNames.BLUE_SKIES_AIRLINES_631;
        String billingStreetAddress = "10 Kent Street"; String deliveryStreetAddress = "15 George Street";
        String city = "Riverdale"; String zipcode = "94098";  String country = "UNITED STATES"; String state = "CA";
        CardType cardType = CardType.MASTERCARD;
        String cardNumber = "123456789012"; String cardExpiry = "12 2010"; String billerName = "Marc Jacob";
        String deptext = changeDateFormat(fromDate,"d-MMMM-yyyy","M/d/YYYY");
        String rettext = changeDateFormat(toDate,"d-MMMM-yyyy","M/d/YYYY");
        String passengerName1 = "Martha Jessop";
        Meal passenger1MealPref = Meal.NO_PREFERENCE;

        //login to mercury tours application
        mercuryHome.toursLogIn(getURL(), getUserName(),getPassword());

        //select round trip flight, flight preference and submit
        flightPage.setRoundTripFlightDetails(1,fromLocation, fromDate, toLocation, toDate);
        flightPage.setFlightPreferences(serviceClass,airline);
        flightPage.submit();

        //select departure and return flight
        //capture flight cost and time
        selectFlight.selectDeparture(depFlightName);
        selectFlight.selectReturn(retFlightName);

        String dep_cost = selectFlight.getDepartureFlightCost(depFlightName);
        String dep_time = selectFlight.getDepartureFlightTime(depFlightName);
        String ret_cost = selectFlight.getReturnFlightCost(retFlightName);
        String ret_time = selectFlight.getReturnFlightTime(retFlightName);

        selectFlight.submit();

        //in flight summary page
        //verify flight details
        //capture taxes and total price
        //enter passenger details
        //enter payment details
        //enter billing address
        //select ticketless travel
        //enter delivery address

        //verify departure flight details in summary page
        Assert.assertThat("Departing Flight Summary : check route",flightSummary.getDepartureFlightRoute(),
                containsString(fromLocation.value() + " to " + toLocation.value()));
        Assert.assertThat("Departing Flight Summary : check date",flightSummary.getDepartureFlightDate(),
                containsString(deptext));
        Assert.assertThat("Departing Flight Summary : check flight name",flightSummary.getDepartureFlightName(),
                containsString(depFlightName.value()));
        Assert.assertThat("Departing Flight Summary : check class",flightSummary.getDepartureFlightClass(),
                containsString("First"));
        //verify return flight details in summary page
        Assert.assertThat("Return Flight Summary : check route",flightSummary.getReturnFlightRoute(),
                containsString(toLocation.value() + " to " + fromLocation.value()));
        Assert.assertThat("Return Flight Summary : check date",flightSummary.getReturnFlightDate(),
                containsString(rettext));
        Assert.assertThat("Return Flight Summary : check flight name",flightSummary.getReturnFlightName(),
                containsString(retFlightName.value()));
        Assert.assertThat("Return Flight Summary : check class",flightSummary.getReturnFlightClass(),
                containsString("First"));

        //capture taxes and total price
        String taxes = flightSummary.getFlightTaxes();
        String totalprice = flightSummary.getFlightTotalPriceIncludingTaxes();

        flightSummary.setPassengerDetail(1, passengerName1, passenger1MealPref);
        flightSummary.setPaymentDetails(cardType, cardNumber, cardExpiry, billerName );
        flightSummary.setBillingAddress(billingStreetAddress, city, state, zipcode, country);
        flightSummary.setTicketlessTravel("ON");
        flightSummary.setDeliveryAddress(deliveryStreetAddress, city, state, zipcode, country);
        flightSummary.purchase();

        //flight confirmation page - check booking completed successfully
        Assert.assertTrue("check booking success message",
                flightConfirmation.checkTextPresentInPage("Your itinerary has been booked"));

        //flight confirmation page - verify flight departure details
        String depDetails = flightConfirmation.getDepartureFlightDetails();
        depDetails = depDetails.replace("\n","");
        Assert.assertThat("Departing Flight : check route",depDetails,
                containsString(fromLocation.value() + " to " + toLocation.value()));
        Assert.assertThat("Departing Flight : check flight class",depDetails,
                containsString("First"));

        deptext = deptext + " @ " + dep_time + " w/ " + depFlightName.value();
        Assert.assertThat("Departing Flight : check flight cost",depDetails,
                containsString(dep_cost + " each"));
        Assert.assertThat("Departing Flight : check flight date, cost and name",depDetails,
                containsString(deptext));

        //flight confirmation page - verify flight return details
        String retDetails = flightConfirmation.getReturnFlightDetails();
        retDetails = retDetails.replace("\n","");
        Assert.assertThat("Return Flight : check route",retDetails,
                containsString(toLocation.value() + " to " + fromLocation.value()));
        Assert.assertThat("Return Flight : check flight class",retDetails,
                containsString("First"));

        rettext = rettext + " @ " + ret_time + " w/ " + retFlightName.value();
        Assert.assertThat("Return Flight : check flight cost",retDetails,
                containsString(ret_cost + " each"));
        Assert.assertThat("Return Flight : check flight date, cost and name",retDetails,
                containsString(rettext));

        //flight confirmation page - verify passengers
        String passengers = flightConfirmation.getPassengersDetails();
        Assert.assertThat("Passengers",passengers,
                containsString("1 passenger"));


        //flight confirmation page - verify billing details
        String billing = flightConfirmation.getBillingAddressDetails();
        Assert.assertThat("Billing Address - Name",billing,
                containsString(billerName));
        Assert.assertThat("Billing Address - Street address",billing,
                containsString(billingStreetAddress));
        Assert.assertThat("Billing Address - city, state. zipcode",billing,
                containsString(city + ", " + state + ", " + zipcode));

        //flight confirmation page - verify ticketless travel
        Assert.assertThat("Ticketless travel - ",flightConfirmation.getDeliveryAddressHeader(),
                containsString("N/A for Ticketless Travel"));

        //flight confirmation page - verify delivery address details
        String delivery = flightConfirmation.getDeliveryAddressDetails();
        Assert.assertThat("Delivery Address - Street address",delivery,
                containsString(deliveryStreetAddress));
        Assert.assertThat("Delivery Address - city, state. zipcode",delivery,
                containsString(city + ", " + state + ", " + zipcode));

        //flight confirmation page - verify taxes and total price is same as in summary page
        Assert.assertThat("Total Taxes",flightConfirmation.getTotalTaxes(),
                containsString(taxes));
        Assert.assertThat("Total Price including Taxes",flightConfirmation.getTotalPrice(),
                containsString(totalprice));

        flightConfirmation.logout();

    }

}


