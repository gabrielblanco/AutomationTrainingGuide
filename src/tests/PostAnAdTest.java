package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.PostAnAdPage;

public class PostAnAdTest extends BaseTest{

    // Variables
    private WebDriver driver;

    // Instances
    private LoginPage loginPage;
    private HomePage homePage;
    private PostAnAdPage postAnAdPage;

    // Prints an Starting Execution message
    @BeforeTest
    void beforeTestAlert(){
        System.out.println("Starting Execution");
    }

    // Prints the name of the class under testing
    @BeforeClass
    void beforeClassAlert(){
        System.out.println("Executing class: tests.PostAnAdTest");
    }

    // Sets the driver up
    @BeforeMethod
    @Parameters({"baseUrl", "browserType", "executionType"})
    void setUp(String baseUrl, String browserType, String executionType){
        System.out.println("Creating Driver");
        driver = createDriver(baseUrl, executionType, browserType);
        homePage = new HomePage(driver);
        loginPage = homePage.clickOnLogin();
        postAnAdPage = homePage.clickOnPostAnAd();
    }

    // Verifies the Post An Add page is loading when click over the Post An Ad and the user is logged in.
    @Test
    public void verifyPostAnAdPageIsDisplayedWhenClickOverPostAnAddWithTheUserLogged(){
        homePage.clickOnPostAnAd();
        loginPage.logIn("gabrielduran", "258JGD#");
        Assert.assertEquals(postAnAdPage.getPageTitle(), "Post an Ad");
    }

    // Verifies the Login Page is loading when click over the Post An Ad and the user is not logged.
    @Test
    public void verifyLoginPageIsDisplayedWhenClickOverPostAnAddWithoutTheUserLogged(){
        homePage.clickOnPostAnAd();
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
        System.out.println("Completing execution class: tests.PostAnAdTest");
    }

    // Prints a message when the test execution ends.
    @AfterTest
    void afterTestAlert(){
        System.out.println("Completing Execution");
    }
}
