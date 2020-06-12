import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import static org.testng.Assert.assertEquals;

public class LoginTests extends BaseTest {

    // Variables
    private WebDriver driver;
    private String baseUrl;
    private BasePage basePage;

    // Locators
    By byLoginLink = By.id("ctl00_LoginView_LoginLink");
    By byLoginTitle = By.tagName("h2");
    By byUsernameInput = By.id("ctl00_Main_LoginConrol_UserName");
    By byPasswordInput = By.id("ctl00_Main_LoginConrol_Password");
    By byLoginButton = By.id("ctl00_Main_LoginConrol_LoginButton");
    By byLogoutTitle = By.id("ctl00_LoginView_MemberLoginStatus");
    By byLoginErrorMsg = By.xpath("//tbody[1]/tr[4]/td");

    @BeforeTest
    void beforeTestAlert(){
        System.out.println("Starting Execution");
    }

    @BeforeClass
    void beforeClassAlert(){
        System.out.println("Executing class: LoginTests");
    }

    @BeforeMethod
    void setUp(){
        System.out.println("Creating Driver");
        driver = createDriver("local", "chrome");
        baseUrl = "http://qa-trainingw7:86/";
        basePage = new BasePage(driver);
        basePage.getBaseUrl(baseUrl);
        WebElement loginLink = basePage.findElement(byLoginLink);
        basePage.clickOnElement(loginLink);
    }

    @Test
    public void verifyLoginPageWhenClickingLoginLink(){
        WebElement loginTitle = basePage.findElement(byLoginTitle);
        assertEquals(basePage.getTextFromElement(loginTitle), "Login");
    }

    @Test
    public void verifyUserIsLoginWhenValidCredentials(){
        WebElement usernameInput = basePage.findElement(byUsernameInput);
        WebElement passwordInput = basePage.findElement(byPasswordInput);
        WebElement loginButton = basePage.findElement(byLoginButton);
        basePage.typeOnElement(usernameInput, "gduran");
        basePage.typeOnElement(passwordInput, "258JGD#");
        basePage.clickOnElement(loginButton);
        WebElement logoutTitle = basePage.findElement(byLogoutTitle);
        basePage.waitForElementVisible(logoutTitle);
        assertEquals(basePage.getTextFromElement(logoutTitle), "Logout");
    }

    @Test
    public void verifyUserNotAbleLoginWhenInvalidCredentials(){
        WebElement usernameInput = basePage.findElement(byUsernameInput);
        WebElement passwordInput = basePage.findElement(byPasswordInput);
        WebElement loginButton = basePage.findElement(byLoginButton);
        basePage.typeOnElement(usernameInput, "gabrielduran");
        basePage.typeOnElement(passwordInput, "123456");
        basePage.clickOnElement(loginButton);
        WebElement loginErrorMsg = basePage.findElement(byLoginErrorMsg);
        assertEquals(basePage.getTextFromElement(loginErrorMsg), "Your login attempt was not successful. Please try again.");
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
        System.out.println("Completing execution class: LoginTests");
    }

    // Prints a message when the test execution ends.
    @AfterTest
    void afterTestAlert(){
        System.out.println("Completing Execution");
    }
}
