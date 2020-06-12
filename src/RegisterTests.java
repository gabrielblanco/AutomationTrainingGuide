import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class RegisterTests extends BaseTest {

    /**
     * Variables.
     */
    private WebDriver driver;
    private String baseUrl;
    private BasePage basePage;

    /**
     * Locators
     * */
    By byRegisterLink = By.id("ctl00_LoginView_RegisterLink");
    By byRegisterTitle = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Create an Account'])[1]/following::h3[1]");
    By bySubmitButton = By.id("ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton");
    By byErrorFirstName = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstNameRequired");
    By byErrorLastName = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastNameRequired");
    By byErrorEmail = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_EmailRequired");
    By byErrorUserName = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserNameRequired");
    By byErrorPassword = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_PasswordRequired");
    By byErrorConfirmPassword = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPasswordRequired");
    By byErrorQuestionRequired = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_QuestionRequired");
    By byErrorAnswerRequired = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_AnswerRequired");
    By byErrorUserExist = By.id("ctl00_Main_InfoLabel");
    By byErrorPasswordNotMatch = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_PasswordCompare");
    By byFirstName = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstName");
    By byLastName = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastName");
    By byEmail = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Email");
    By byUserName = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserName");
    By byPassword = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Password");
    By byConfirmPassword = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPassword");
    By byQuestion = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Question");
    By byAnswer = By.id("ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Answer");

    @BeforeTest
    void beforeTestAlert(){
        System.out.println("Starting Execution");
    }

    @BeforeClass
    void beforeClassAlert(){
        System.out.println("Executing class: RegisterTests");
    }

    @BeforeMethod
    void setUp(){
        System.out.println("Creating Driver");
        driver = createLocalDriver("chrome");
        baseUrl = "http://qa-trainingw7:86/";
        basePage = new BasePage(driver);
        basePage.getBaseUrl(baseUrl);
        WebElement registerLink = basePage.findElement(byRegisterLink);
        basePage.clickOnElement(registerLink);
    }

    /**
     * Steps to check test case requirements.
     * @throws Exception
     */
    @Test
    public void testVerifyTheRegisterPageIsDisplayedCorrectlyWhenClickingOnRegisterLink() throws Exception {
        WebElement registerTitle = basePage.findElement(byRegisterTitle);
        assertEquals(basePage.getTextFromElement(registerTitle), "Registration");
    }

    /**
     * Steps to check test case requirements.
     * @throws Exception
     */
    @Test
    public void testVerifyAllTheMandatoryMessagesAreDisplayedWhenLeavingEmptyAllTheMandatoryFieldsAndClickingOnSubmitButton() throws Exception {
        WebElement submitButton = basePage.findElement(bySubmitButton);
        basePage.clickOnElement(submitButton);
        WebElement errorFirstName = basePage.findElement(byErrorFirstName);
        WebElement errorLastName = basePage.findElement(byErrorLastName);
        WebElement errorEmail = basePage.findElement(byErrorEmail);
        WebElement errorUserName = basePage.findElement(byErrorUserName);
        WebElement errorPassword = basePage.findElement(byErrorPassword);
        WebElement errorConfirmPassword = basePage.findElement(byErrorConfirmPassword);
        WebElement errorQuestionRequired = basePage.findElement(byErrorQuestionRequired);
        WebElement errorAnswerRequired = basePage.findElement(byErrorAnswerRequired);
        
        assertEquals(basePage.getTextFromElement(errorFirstName), "First name is required.");
        assertEquals(basePage.getTextFromElement(errorLastName), "Last name is required.");
        assertEquals(basePage.getTextFromElement(errorEmail), "Email is required.");
        assertEquals(basePage.getTextFromElement(errorUserName), "User name is required.");
        assertEquals(basePage.getTextFromElement(errorPassword), "Password is required.");
        assertEquals(basePage.getTextFromElement(errorConfirmPassword), "Confirm password is required.");
        assertEquals(basePage.getTextFromElement(errorQuestionRequired), "Security question is required.");
        assertEquals(basePage.getTextFromElement(errorAnswerRequired), "Security answer is required.");
    }

    /**
     * Steps to check test case requirements.
     * @throws Exception
     */
    @Test
    public void testVerifyAnErrorMessageIsDisplayedWhenCreatingAnUserWithAnUserNameThatIsAlreadyTaken() throws Exception {
        WebElement submitButton = basePage.findElement(bySubmitButton);
        WebElement firstName = basePage.findElement(byFirstName);
        WebElement lastName = basePage.findElement(byLastName);
        WebElement email = basePage.findElement(byEmail);
        WebElement userName = basePage.findElement(byUserName);
        WebElement password = basePage.findElement(byPassword);
        WebElement confirmPassword = basePage.findElement(byConfirmPassword);
        WebElement question = basePage.findElement(byQuestion);
        WebElement answer = basePage.findElement(byAnswer);

        basePage.typeOnElement(firstName, "Juan");
        basePage.typeOnElement(lastName, "Blanco");
        basePage.typeOnElement(email, "gabriel.blanco03@hotmail.com");
        basePage.typeOnElement(userName, "gduran");
        basePage.typeOnElement(password, "258JGD#");
        basePage.typeOnElement(confirmPassword, "258JGD#");
        basePage.typeOnElement(question, "My name?");
        basePage.typeOnElement(answer, "Gabriel");
        basePage.clickOnElement(submitButton);
        WebElement errorUserExist = basePage.findElement(byErrorUserExist);
        assertEquals(basePage.getTextFromElement(errorUserExist), "User name already exists. Please enter a different user name.");
    }

    /**
     * Steps to check test case requirements.
     * @throws Exception
     */
    @Test
    public void testVerifyAnErrorMessageIsDisplayedWhenSendingADifferentPasswordOnPasswordAndConfirmPasswordFields() throws Exception {
        WebElement password = basePage.findElement(byPassword);
        WebElement confirmPassword = basePage.findElement(byConfirmPassword);
        WebElement submitButton = basePage.findElement(bySubmitButton);

        basePage.typeOnElement(password, "12345JGD");
        basePage.typeOnElement(confirmPassword, "123JGD");
        basePage.clickOnElement(submitButton);
        WebElement errorPasswordNotMatch = basePage.findElement(byErrorPasswordNotMatch);
        assertEquals(basePage.getTextFromElement(errorPasswordNotMatch), "The password and confirmation password must match.");
    }

    /**
     * Tears down the driver after execution finized.
     * @throws Exception
     */
    @AfterMethod
    public void tearDown() throws Exception {
        System.out.println("Deleting Driver");
        driver.quit();
    }

    @AfterClass
    void afterClassAlert(){
        System.out.println("Completing execution class: RegisterTests");
    }

    @AfterTest
    void afterTestAlert(){
        System.out.println("Completing Execution");
    }
}
