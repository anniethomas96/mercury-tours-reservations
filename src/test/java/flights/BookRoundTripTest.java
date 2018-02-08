package flights;

import common.BaseSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.HomePage;

public class BookRoundTripTest extends BaseSetup {

    private static final Logger logger = LogManager.getLogger(BookRoundTripTest.class);
    WebDriver driver;
    private HomePage mercuryHome;

    @Before
    public void setUp() throws Exception {
        driver = setBrowserDriver();
        mercuryHome = new HomePage(driver);
    }

    @After
    public void tearDown() throws Exception {
        //driver.quit();
    }

    @Test
    public void HelloWorld() {
        mercuryHome.toursLogIn(getUserName(),getPassword());

    }
}


