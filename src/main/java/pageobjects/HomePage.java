package pageobjects;

import common.BaseSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BaseSetup {

    private final Logger logger = LogManager.getLogger(BaseSetup.class);

    By oUserName = By.name("userName");
    By oPassword = By.name("password");
    By oLoginButton = By.name("login");

    //constructor for HomePage class
    //Parameter - webdriver
    public HomePage(WebDriver driver) { this.driver = driver; }

    //explicit wait method to wait 10 seconds till username object is visible
    private void waitForPageToLoad(){
        new WebDriverWait(driver,10).until(
                ExpectedConditions.visibilityOfElementLocated(oUserName));
    }

    //set username for login
    public void setUserName(String userName) {
        logger.debug("set username as " + userName);
        driver.findElement(oUserName).clear();
        driver.findElement(oUserName).sendKeys(userName);
    }

    //set password for login
    public void setPassword(String password) {
        logger.debug("set password as " + password);
        driver.findElement(oPassword).clear();
        driver.findElement(oPassword).sendKeys(password);
    }

    //click on login button
    public void clickLoginButton() {
        logger.debug("click login button");
        driver.findElement(oLoginButton).click();
    }

    //open mercury tours URL
    public void openMercuryToursHomePage(String url){
        logger.debug("opening URL " + url);
        driver.get(url);
    }

    //wrapper to invoke mercury tours url and login and used directly in tests
    //parameters (1) - String - url to login (available in setup.properties)
    //parameters (2) - String - username (available in setup.properties)
    //parameters (3) - String - password (available in setup.properties)
    public void toursLogIn(String url, String userName, String password){
        logger.debug("login to Mercury Tours application");
        openMercuryToursHomePage(url);
        logIn(userName, password);
    }

    //wrapper to login to mercury tours website
    //parameters (1) - String - username (available in setup.properties)
    //parameters (2) - String - password (available in setup.properties)
    public void logIn(String userName, String password){
        waitForPageToLoad();
        setUserName(userName);
        setPassword(password);
        clickLoginButton();
//            new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
//                    ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        }
}
