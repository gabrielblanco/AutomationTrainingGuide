package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.URL;

public class BaseTest {

    // Web driver
    WebDriver driver;

    // Driver path variables
    final String googleDriverPath = "resources/chromedriver.exe";
    final String firefoxDriverPath = "resources/geckodriver.exe";

    // Allows to the user select the driver type of execution. Local or Remote.
    @Parameters({"nodeUrl"})
    public WebDriver createDriver(String baseUrl, String executionType, String browserType, String nodeUrl){
        try{
            if (executionType.equals("local")){
                driver = createLocalDriver(browserType);
                driver.get(baseUrl);
            } else if(executionType.equals("remote")){
                driver = createRemoteDriver(browserType, nodeUrl);
                driver.get(baseUrl);
            }
        } catch (Exception e){
            System.out.println("Execution type must be local or remote, you are sending: " + executionType);
        }
        return driver;
    }

    // Creates a local driver for the specified browser
    public WebDriver createLocalDriver(String browserType){
        try {
            if (browserType.equals("chrome")){
                System.out.println("Creating Chrome Driver");
                System.setProperty("webdriver.chrome.driver", googleDriverPath);
                driver = new ChromeDriver();
            } else if (browserType.equals("firefox")){
                System.out.println("Creating Firefox Driver");
                System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
                driver = new FirefoxDriver();
            }
        } catch (Exception e) {
            System.out.println("Unable to load the browser: " + browserType + "\n" + e);
        }
        return driver;
    }

    // Creates a remote driver for the specified browser
    public WebDriver createRemoteDriver(String browserType, String nodeUrl) {
        try{
            if (browserType.equals("chrome")){
                driver = new RemoteWebDriver(new URL(nodeUrl), new ChromeOptions());
            }else if (browserType.equals("Firefox")) {
                driver = new RemoteWebDriver(new URL(nodeUrl), new FirefoxOptions());
            }
        } catch (Exception e){
            System.out.println("Unable to execute the " + browserType);
        }
        return driver;
    }

}
