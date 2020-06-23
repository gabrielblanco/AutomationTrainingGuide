package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import utilities.BaseTest;

public class HomeTest extends BaseTest {

    // Web driver
    WebDriver driver;

    // Instances
    HomePage homePage;
    LoginPage loginPage;

    // Prints an Starting Execution message
    @BeforeTest
    void beforeTestAlert(){
        System.out.println("Starting Execution");
    }

    // Prints the name of the class under testing
    @BeforeClass
    void beforeClassAlert(){
        System.out.println("Executing class: tests.HomeTest");
    }

    // Sets the driver up
    @BeforeMethod
    @Parameters({"baseUrl", "browserType", "executionType", "nodeUrl"})
    void setUp(String baseUrl, String browserType, String executionType, String nodeUrl){
        System.out.println("Creating Driver");
        driver = createDriver(baseUrl, executionType, browserType, nodeUrl);
        homePage = new HomePage(driver);
    }

    // Tis test case verify if the Home Page main elements are visible when loading the app
    @Test
    public void verifyHomePageLoad(){
        Assert.assertTrue(homePage.verifyLoads());
    }

    // Tis test case verify if the HomePage main elements are visible when click over the Home menu tab
    @Test
    public void verifyHomePageLoadWhenClickOnHome(){
        homePage.clickOnHome();
        Assert.assertTrue(homePage.verifyLoads());
    }

    // Tears down the driver after execution finalize.
    @AfterMethod
    public void tearDown() {
        System.out.println("Deleting Driver");
        driver.quit();
    }

    // Prints a message when the class execution ends.
    @AfterClass
    void afterClassAlert(){
        System.out.println("Completing execution class: tests.HomeTest");
    }

    // Prints a message when the test execution ends.
    @AfterTest
    void afterTestAlert(){
        System.out.println("Completing Execution");
    }
}
