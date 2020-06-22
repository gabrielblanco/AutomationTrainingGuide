package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.BasePage;
import pages.HomePage;
import pages.RegisterPage;
import java.util.ArrayList;
import java.util.List;

public class RegisterTest extends BaseTest {

    // Variables.
    private WebDriver driver;
    private RegisterPage registerPage;

    // Prints an Starting Execution message
    @BeforeTest
    void beforeTestAlert(){
        System.out.println("Starting Execution");
    }

    // Prints the name of the class under testing
    @BeforeClass
    void beforeClassAlert(){
        System.out.println("Executing class: tests.RegisterTests");
    }

    // Sets the driver up
    @BeforeMethod
    @Parameters({"baseUrl", "browserType", "executionType"})
    void setUp(String baseUrl, String browserType, String executionType){
        System.out.println("Creating Driver");
        driver = createDriver(baseUrl, executionType, browserType);
        HomePage homePage = new HomePage(driver);
        registerPage = homePage.clickOnRegister();
    }

    // Steps to check test case requirements.
    @Test
    public void verifyTheRegisterPageIsDisplayedCorrectlyWhenClickingOnRegisterLink() throws Exception {
        Assert.assertEquals(registerPage.getPageTitle(), "Register");
    }

    // Steps to check test case requirements.
    @Test
    public void verifyAllTheMandatoryMessagesAreDisplayedWhenLeavingEmptyAllTheMandatoryFieldsAndClickingOnSubmitButton() throws Exception {
        registerPage.clickOnSubmit();
        List<String> mandatoryErrorMsgList = new ArrayList<>();
        mandatoryErrorMsgList.add("First name is required.");
        mandatoryErrorMsgList.add("Last name is required.");
        mandatoryErrorMsgList.add("Email is required.");
        mandatoryErrorMsgList.add("User name is required.");
        mandatoryErrorMsgList.add("Password is required.");
        mandatoryErrorMsgList.add("Confirm password is required.");
        mandatoryErrorMsgList.add("Security question is required.");
        mandatoryErrorMsgList.add("Security answer is required.");
        Assert.assertTrue(registerPage.vaidateMandatoryFields(mandatoryErrorMsgList));
    }

    // Steps to check test case requirements.
    @Test
    public void verifyAnErrorMessageIsDisplayedWhenCreatingAnUserWithAnUserNameThatIsAlreadyTaken() throws Exception {
        registerPage.newRegister("Juan", "Blanco", "gabriel.blanco03@hotmail.com", "gduran", "258JGD#", "258JGD#", "My name?", "Gabriel");
        Assert.assertTrue(registerPage.verifyErrorMsg("User name already exists. Please enter a different user name."));
    }

    // Steps to check test case requirements.
    @Test
    public void verifyAnErrorMessageIsDisplayedWhenSendingADifferentPasswordOnPasswordAndConfirmPasswordFields() throws Exception {
        Assert.assertTrue(registerPage.validatePassword("pass1", "confirmPass1", "The password and confirmation password must match."));
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
        System.out.println("Completing execution class: tests.RegisterTests");
    }

    // Prints a message when the test execution ends.
    @AfterTest
    void afterTestAlert(){
        System.out.println("Completing Execution");
    }
}
