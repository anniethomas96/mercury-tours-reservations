package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSetup {

    private static final Logger logger = LogManager.getLogger(BaseSetup.class);

    private final String projectPath = System.getProperty("user.dir");
    //private final String inputDataPath = projectPath + File.separator + "data";
    //private final String testResultsPath = projectPath + File.separator + "results";
    private final String toolsPath = projectPath + File.separator + "tools";
    private final String propertiesPath = projectPath + File.separator + "setup.properties";

    private Properties setupProp = new Properties();

    protected WebDriver driver;

    public String getProjectPath (){
        return projectPath;
    }

    public String getToolsPath(){
        return toolsPath;
    }

    //Get application URL from properties file
    public String getURL()  {
        try{ //try moving to constructor
            setupProp.load(new FileInputStream(propertiesPath));
        } catch (FileNotFoundException e){
            logger.info("the properties file does not exist - " + propertiesPath);
        } catch (IOException e){
            logger.info("the properties file needs to be checked - " + propertiesPath);
        }
        return setupProp.getProperty("url");
    }

    //Get username from the properties file
    public String getUserName()  {
        try{ //try moving to constructor
            setupProp.load(new FileInputStream(propertiesPath));
        } catch (FileNotFoundException e){
            logger.info("the properties file does not exist - " + propertiesPath);
        } catch (IOException e){
            logger.info("the properties file needs to be checked - " + propertiesPath);
        }
        return setupProp.getProperty("username");
    }

    //Get password from the properties file
    public String getPassword()  {
        try{ //try moving to constructor
            setupProp.load(new FileInputStream(propertiesPath));
        } catch (FileNotFoundException e){
            logger.info("the properties file does not exist - " + propertiesPath);
        } catch (IOException e){
            logger.info("the properties file needs to be checked - " + propertiesPath);
        }
        return setupProp.getProperty("password");
    }

    //Create selenium webdriver for required browser(chrome).
    public WebDriver setBrowserDriver(){
//        System.setProperty("webdriver.gecko.driver", getToolsPath() + "\\geckodriver.exe");
//        WebDriver driver = new FirefoxDriver();
//        logger.info("Firefox browser driver created");

        System.setProperty("webdriver.chrome.driver", getToolsPath() + "\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        logger.info("Chrome browser driver created");

        return driver;
    }

    //method to check if a text is present in the page
    //parameters(1) - String - string to be searched
    //output - Boolean - true if text found. false if not
    public Boolean checkTextPresentInPage(String searchFor){
        String body = driver.findElement(By.xpath("//body")).getText().replace(" ","").replace("\n","");
        searchFor = searchFor.replace(" ","").replace("\n","");
        if (body.contains(searchFor)){
            return true;
        }
        return false;
    }

    //method to retrieve page title
    //output - String - page title
    public String getPageTitle(){
        String title = driver.getTitle();
        logger.debug("page title is [" + title + "]");
        return title;
    }

}
