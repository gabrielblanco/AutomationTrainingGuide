import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    /**
     * Variables
     * */
    String baseUrl, nodeUrl;

    /**
     * Web driver
     * */
    WebDriver driver;

    /**
     * Driver path variables
     * */
    final String googleDriverPath = "resources/chromedriver.exe";
    final String firefoxDriverPath = "resources/geckodriver.exe";
    final String binaryPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";

    /**
     * Creates a local driver for the specified browser
    */
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

    /**
     * Creates a remote driver for the specified browser
     * */
    public WebDriver createRemoteDriver(String browserType) throws MalformedURLException {
        nodeUrl = "http://localhost:4444/wd/hub";
        try {
            if (browserType.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", googleDriverPath);
                ChromeOptions options = new ChromeOptions();
                options.setBinary(binaryPath);
                driver = new RemoteWebDriver(new URL(nodeUrl), options);
            } else if (browserType.equals("firefox")){
//                DesiredCapabilities capability = DesiredCapabilities.firefox();
//                capability.setBrowserName(browserType);
//                capability.setPlatform(Platform.WIN10);
//                driver = new RemoteWebDriver(new URL(nodeUrl), capability);
//                System.out.println("firefox");
            }
        } catch (Exception e) {
            System.out.println("Unable to load the browser: " + browserType + "\n" + e);
        }
        return driver;
    }
}
