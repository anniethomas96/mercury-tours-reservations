package pageobjects;

import common.BaseSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BaseSetup {

    private final Logger logger = LogManager.getLogger(BaseSetup.class);

    By oUserName = By.name("userName");
    By oPassword = By.name("password");
    By oLoginButton = By.name("login");

    public HomePage(WebDriver driver) { this.driver = driver; }

    public void setUserName(String userName) {
        logger.info("set username as " + userName);
        driver.findElement(oUserName).clear();
        driver.findElement(oUserName).sendKeys(userName);
    }

    public void setPassword(String password) {
        logger.info("set password as " + password);
        driver.findElement(oPassword).clear();
        driver.findElement(oPassword).sendKeys(password);
    }

    public void clickLoginButton() {
        logger.info("click login button");
        driver.findElement(oLoginButton).click();
    }

    public void openMercuryToursHomePage(String url){
        logger.debug("opening URL " + url);
        driver.get(url);
    }

    public void toursLogIn(String userName, String password){
        logger.info("login to Mercury Tours application");
        openMercuryToursHomePage(getURL());
        logIn(userName, password);
    }

    public void logIn(String userName, String password){
        setUserName(userName);
        setPassword(password);
        clickLoginButton();
    }
}
