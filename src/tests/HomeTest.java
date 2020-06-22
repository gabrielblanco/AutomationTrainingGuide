package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

public class HomeTest extends BaseTest{

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
    @Parameters({"baseUrl", "browserType", "executionType"})
    void setUp(String baseUrl, String browserType, String executionType){
        System.out.println("Creating Driver");
        driver = createDriver(baseUrl, executionType, browserType);
        homePage = new HomePage(driver);
    }

    /**
     * Home Page Test Cases
     * */
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

    /**
     * Categories Test Cases
     * */
    // Verifies the Antiques & Collectibles category is displayed on the Search view when click over the Antiques & Collectibles category
//    @Test
//    public void verifyAntiquesAndCollectiblesSearchViewWhenClickOverThatCategory(){
//        homePage.clickOnCategory("Antiques & Collectibles");
//        Assert.assertEquals(homePage.getSearchFocus(), "Antiques & Collectibles");
//    }
//
//    // Verifies the Arts & Crafts category is displayed on the Search view when click over the Arts & Crafts category
//    @Test
//    public void verifyArtsAndCraftsSearchViewWhenClickOverThatCategory(){
//        Assert.assertEquals(homePage.getSearchFocus(), "Arts & Crafts");
//    }
//
//    // Verifies the Auto category is displayed on the Search view when click over the Auto category
//    @Test
//    public void verifyAutoSearchViewWhenClickOverThatCategory(){
//        Assert.assertEquals(homePage.getSearchFocus(), "Auto");
//    }

    /**
     * Post An Ad Test Cases
     * */
    // Verifies the Post An Add page is loading when click over the Post An Ad and the user is logged in.
    @Test
    public void verifyPostAnAdPageIsDisplayedWhenClickOverPostAnAddWithTheUserLogged(){
        loginPage = homePage.clickOnLogin();
        loginPage.logIn("gabrielduran", "258JGD#");
        homePage.clickOnPostAnAd();
        Assert.assertEquals(homePage.getTabTitle(), "Post an Ad");
    }

    // Verifies the Login Page is loading when click over the Post An Ad and the user is not logged.
    @Test
    public void verifyLoginPageIsDisplayedWhenClickOverPostAnAddWithoutTheUserLogged(){
        homePage.clickOnPostAnAd();
        loginPage = homePage.clickOnLogin();
        Assert.assertTrue(loginPage.verifyLoads());
    }

    /**
     * My Ads & Profile Test Cases
     * */
    // Verifies the My Ads & Profile page is displayed when click over the My Ads & Profile menu tab.
    @Test
    public void verifyMyAdsAndProfilePageIsDisplayedWhenClickOnMyAdsAndProfileNavMenu(){
        homePage.clickOnMyAdsAndProfile();
        loginPage.logIn("gabrielduran", "258JGD#");
        Assert.assertEquals(homePage.getTabTitle(), "My Ads & Profile");
    }

    // Verifies the Login page is displayed when click over the My Ads & Profile menu tab when the user is not logged in.
    @Test
    public void verifyLoginPageWhenClickOnMyAdsAndProfileAndTheUserIsNotLogged(){
        homePage.clickOnMyAdsAndProfile();
        loginPage = homePage.clickOnLogin();
        Assert.assertTrue(loginPage.verifyLoads());
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
