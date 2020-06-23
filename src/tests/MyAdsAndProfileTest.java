package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAdsAndProfilePage;
import utilities.BaseTest;

public class MyAdsAndProfileTest extends BaseTest {

    // Variables
    private WebDriver driver;

    // Instances
    private LoginPage loginPage;
    private HomePage homePage;
    private MyAdsAndProfilePage myAdsAndProfilePage;

    // Prints an Starting Execution message
    @BeforeTest
    void beforeTestAlert(){
        System.out.println("Starting Execution");
    }

    // Prints the name of the class under testing
    @BeforeClass
    void beforeClassAlert(){
        System.out.println("Executing class: tests.MyAdsAndProfileTest");
    }

    // Sets the driver up
    @BeforeMethod
    @Parameters({"baseUrl", "browserType", "executionType", "nodeUrl"})
    void setUp(String baseUrl, String browserType, String executionType, String nodeUrl){
        System.out.println("Creating Driver");
        driver = createDriver(baseUrl, executionType, browserType, nodeUrl);
        homePage = new HomePage(driver);
        loginPage = homePage.clickOnLogin();
        myAdsAndProfilePage = homePage.clickOnMyAdsAndProfile();
    }

    // Verifies the My Ads & Profile page is displayed when click over the My Ads & Profile menu tab.
    @Test
    public void verifyMyAdsAndProfilePageIsDisplayedWhenClickOnMyAdsAndProfileNavMenu(){
        homePage.clickOnMyAdsAndProfile();
        loginPage.logIn("gabrielduran", "258JGD#");
        Assert.assertEquals(myAdsAndProfilePage.getPageTitle(), "My Ads & Profile");
    }

    // Verifies the Login page is displayed when click over the My Ads & Profile menu tab when the user is not logged in.
    @Test
    public void verifyLoginPageWhenClickOnMyAdsAndProfileAndTheUserIsNotLogged(){
        homePage.clickOnMyAdsAndProfile();
        Assert.assertTrue(loginPage.verifyLoads());
    }

    // Tears down the driver after execution finalize.
    @AfterMethod
    public void tearDown() throws Exception {
        System.out.println("Deleting Driver");
        driver.quit();
    }

    // Prints a message when the class execution ends.
    @AfterClass
    void afterClassAlert(){
        System.out.println("Completing execution class: tests.MyAdsAndProfileTest");
    }

    // Prints a message when the test execution ends.
    @AfterTest
    void afterTestAlert(){
        System.out.println("Completing Execution");
    }

}
