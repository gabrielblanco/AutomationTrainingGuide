package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import utilities.BaseTest;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    // Variables
    private WebDriver driver;

    // Instances
    private LoginPage loginPage;

    // Prints an Starting Execution message
    @BeforeTest
    void beforeTestAlert(){
        System.out.println("Starting Execution");
    }

    // Prints the name of the class under testing
    @BeforeClass
    void beforeClassAlert(){
        System.out.println("Executing class: tests.LoginTests");
    }

    // Sets the driver up
    @BeforeMethod
    @Parameters({"baseUrl", "browserType", "executionType", "nodeUrl"})
    void setUp(String baseUrl, String browserType, String executionType, String nodeUrl){
        System.out.println("Creating Driver");
        driver = createDriver(baseUrl, executionType, browserType, nodeUrl);
        HomePage homePage = new HomePage(driver);
        loginPage = homePage.clickOnLogin();
    }

    // This test verifies if the main page elements have already loaded.
    @Test
    public void verifyAllTheElementsHaveAlreadyLoaded() {
        Assert.assertTrue(loginPage.verifyLoads());
    }

    // This test verify if the user is on the Login view
    @Test
    public void verifyLoginPageWhenClickingLoginLink(){
        Assert.assertEquals(loginPage.getPageTitle(), "Login");
    }

    // This test verify if the user is able to log in using valid credentials
    @Test
    public void verifyUserIsLoginWhenValidCredentials(){
        loginPage.logIn("gabrielduran", "258JGD#");
        assertTrue(loginPage.isUserLogged());
    }

    // This test verify if an error message is displayed when the user tries to login with invalid credentials.
    @Test
    public void verifyUserNotAbleLoginWhenInvalidCredentials(){
        loginPage.logIn("gduran", "258abc#");
        assertFalse(loginPage.isUserLogged());
        assertTrue(loginPage.verifyErrorMsg("Your login attempt was not successful. Please try again."));
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
        System.out.println("Completing execution class: tests.LoginTests");
    }

    // Prints a message when the test execution ends.
    @AfterTest
    void afterTestAlert(){
        System.out.println("Completing Execution");
    }
}
