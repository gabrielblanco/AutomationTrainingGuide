package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.BasePage;

public class LoginPage extends BasePage {

    // Login view locators
    @FindBy(tagName = "h2") WebElement loginTitle;
    @FindBy(id = "ctl00_LoginView_MemberLoginStatus") WebElement logoutTitle;
    @FindBy(id = "ctl00_Main_LoginConrol_UserName") WebElement userNameInput;
    @FindBy(id = "ctl00_Main_LoginConrol_Password") WebElement userPasswordInput;
    @FindBy(id = "ctl00_Main_LoginConrol_LoginButton") WebElement loginBtn;
    @FindBy(xpath = "//tbody[1]/tr[4]/td") WebElement errorMessage;

    // Top menu locators
    @FindBy(id = "ctl00_TopMenuRepeater_ctl00_MenuLink") WebElement homeLink;
    @FindBy(id = "ctl00_TopMenuRepeater_ctl01_MenuLink") WebElement postAndAdLink;
    @FindBy(id = "ctl00_TopMenuRepeater_ctl02_MenuLink") WebElement myAdsAndProfileLink;

    // Constructor method
    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Try to make a login
    public void logIn(String user, String pass){
        try {
            typeOnElement(userNameInput, user);
            typeOnElement(userPasswordInput, pass);
            clickOnElement(loginBtn);
        } catch (Exception e) {
            System.out.println("Unable to complete the login. \n" + e);
        }
    }

    // Verifies if an error message is displayed
    public boolean verifyErrorMsg(String message){
        try{
            return (errorMessage.isDisplayed() && getTextFromElement(errorMessage).equals(message));
        } catch (Exception e) {
            System.out.println("The error message was not displayed or the text do not match.");
            return  false;
        }
    }

    // Returns the View title
    public String getPageTitle(){
        return getTextFromElement(loginTitle);
    }

    // Verifies if the user is logged in
    public boolean isUserLogged(){
        return (getTextFromElement(logoutTitle).equals("Logout"));
    }

    // Verifies if the main web elements have already loaded
    public boolean verifyLoads(){
        try {
            return (waitForElementVisible(userNameInput)
                    && waitForElementVisible(userPasswordInput)
                    && waitForElementVisible(loginBtn)
                    && waitForElementVisible(homeLink)
                    && waitForElementVisible(postAndAdLink)
                    && waitForElementVisible(myAdsAndProfileLink));
        } catch (Exception e) {
            System.out.println("Some elements are not visible yet. \n" + e);
            return false;
        }
    }
}
