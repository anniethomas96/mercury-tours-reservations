package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.core.IsNull;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
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
        return  getPropertyValue("url");
    }

    //Get username from the properties file
    public String getUserName()  {
        return  getPropertyValue("username");
    }

    //Get password from the properties file
    public String getPassword()  {
        return  getPropertyValue("password");
    }

    //Get build id from the properties file
    private String getBuildID()  {
        String build =getPropertyValue("build");
        logger.debug("build id is [" + build + "]");
        if (build == null){ build="temp"; }
        return  build;
    }

    //Get test browser from properties file
    public String getBrowser()  {
        return  getPropertyValue("browser");
    }

    //Get enable screenshot setting from properties file
    private Boolean getEnableScreenshot() {
        String screenshot = getPropertyValue("enable_screenshot");
        logger.debug("enable_screenshot setting is " + screenshot);
        if (screenshot.equalsIgnoreCase("true")){
            return true;
        } else { return false;}
    }

    //private method to retrieve property value from setup.properties
    private String getPropertyValue(String property){
        try{
            setupProp.load(new FileInputStream(propertiesPath));
        } catch (FileNotFoundException e){
            logger.info("the properties file does not exist - " + propertiesPath);
        } catch (IOException e){
            logger.info("the properties file needs to be checked - " + propertiesPath);
        }
        return setupProp.getProperty(property);
    }

    //Create selenium webdriver for required browser(specified in properties file).
    //output - WebDriver - driver to run selenium tests
    public WebDriver setBrowserDriver(){
        WebDriver driver;
        if (getBrowser().equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver", getToolsPath() + "\\geckodriver.exe");
            driver = new FirefoxDriver();
            logger.debug("Firefox browser driver created");
        } else {
            System.setProperty("webdriver.chrome.driver", getToolsPath() + "\\chromedriver.exe");
            driver = new ChromeDriver();
            logger.debug("Chrome browser driver created");
        }
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

    public void captureScreenshot(String fileName) {
        String fullFileName, folder;
        if (getEnableScreenshot()) {
            int randomNum = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
            fullFileName = "screenshot-" + fileName + randomNum + ".png";
            folder = "target/surefire-reports/" + getBuildID() + "/";
            try {
                new File(folder).mkdirs(); // Ensure directory is there
                FileOutputStream out = new FileOutputStream(folder + fullFileName);
                out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
                out.close();
                logger.info("Screenshot [{}] captured", fullFileName);
            } catch (Exception e) {
                logger.info("There was an issue in generating screenshot " + folder + fullFileName);
                logger.info(e.toString());

                // No need to crash the tests if the screenshot fails
            }
        }
    }
}
