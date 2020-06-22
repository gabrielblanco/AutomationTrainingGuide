package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CategoriesPage;
import pages.HomePage;
import pages.LoginPage;

public class CategoriesTest extends BaseTest {

    // Variables
    private WebDriver driver;

    // Instances
    private LoginPage loginPage;
    private HomePage homePage;
    private CategoriesPage categoriesPage;

    // Prints an Starting Execution message
    @BeforeTest
    void beforeTestAlert(){
        System.out.println("Starting Execution");
    }

    // Prints the name of the class under testing
    @BeforeClass
    void beforeClassAlert(){
        System.out.println("Executing class: tests.CategoriesTest");
    }

    // Sets the driver up
    @BeforeMethod
    @Parameters({"baseUrl", "browserType", "executionType"})
    void setUp(String baseUrl, String browserType, String executionType){
        System.out.println("Creating Driver");
        driver = createDriver(baseUrl, executionType, browserType);
        homePage = new HomePage(driver);
        loginPage = homePage.clickOnLogin();
    }

    // Verifies the Antiques & Collectibles category is displayed on the Search view when click over the Antiques & Collectibles category
    @Test
    public void verifyAntiquesAndCollectiblesSearchViewWhenClickOverThatCategory(){
        homePage.clickOnHome();
        categoriesPage = homePage.clickOnCategory("Antiques & Collectibles");
        Assert.assertEquals(categoriesPage.getSearchFocus(), " Antiques & Collectibles");
    }

    // Verifies the Arts & Crafts category is displayed on the Search view when click over the Arts & Crafts category
    @Test
    public void verifyArtsAndCraftsSearchViewWhenClickOverThatCategory(){
        homePage.clickOnHome();
        categoriesPage = homePage.clickOnCategory("Arts & Crafts");
        Assert.assertEquals(categoriesPage.getSearchFocus(), " Arts & Crafts");
    }

    // Verifies the Auto category is displayed on the Search view when click over the Auto category
    @Test
    public void verifyAutoSearchViewWhenClickOverThatCategory(){
        homePage.clickOnHome();
        categoriesPage = homePage.clickOnCategory("Auto");
        Assert.assertEquals(categoriesPage.getSearchFocus(), " Auto");
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
        System.out.println("Completing execution class: tests.CategoriesTest");
    }

    // Prints a message when the test execution ends.
    @AfterTest
    void afterTestAlert(){
        System.out.println("Completing Execution");
    }

}
