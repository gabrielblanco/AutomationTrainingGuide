package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegisterPage extends BasePage {

    // Register page locators
    @FindBy(tagName = "h2") WebElement registerTitle;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstName") WebElement firstNameInput;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastName") WebElement lastNameInput;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Email") WebElement emailInput;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserName") WebElement usernameInput;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Password") WebElement passwordInput;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPassword") WebElement confirmPasswordInput;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Question") WebElement securityQuestionInput;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_Answer") WebElement securityAnswerInput;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl___CustomNav0_StepNextButtonButton") WebElement submitBtn;

    // Error message locators
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_FirstNameRequired") WebElement firstNameErrorMsg;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_LastNameRequired") WebElement lastNameErrorMsg;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_EmailRequired") WebElement emailErrorMsg;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_UserNameRequired") WebElement usernameErrorMsg;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_PasswordRequired") WebElement passwordErrorMsg;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_ConfirmPasswordRequired") WebElement confirmPasswordErrorMsg;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_QuestionRequired") WebElement questionErrorMsg;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_AnswerRequired") WebElement answerErrorMsg;
    @FindBy(id = "ctl00_Main_InfoLabel") WebElement userExistErrorMsg;
    @FindBy(id = "ctl00_Main_CreateUserWizardControl_CreateUserStepContainer_PasswordCompare") WebElement passwordMatchErrorMsg;

    // Constructor method
    public RegisterPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Returns the title of the register title
    public String getPageTitle(){
        return getTextFromElement(registerTitle);
    }

    // Clicks over the submit button
    public void clickOnSubmit(){
        clickOnElement(submitBtn);
    }

    // Tries to make a new register
    public void newRegister(String name, String lastname, String email, String username, String password, String confirmPassword, String question, String answer) {
        typeOnElement(firstNameInput, name);
        typeOnElement(lastNameInput, lastname);
        typeOnElement(emailInput, email);
        typeOnElement(usernameInput, username);
        typeOnElement(passwordInput, password);
        typeOnElement(confirmPasswordInput, confirmPassword);
        typeOnElement(securityQuestionInput, question);
        typeOnElement(securityAnswerInput, answer);
        clickOnSubmit();
    }

    // Returns a boolean value in case of an error message is displayed during the registration process.
    public boolean verifyErrorMsg(String message){
        try{
            return (userExistErrorMsg.isDisplayed() && getTextFromElement(userExistErrorMsg).equals(message));
        } catch (Exception e) {
            System.out.println("The error message was not displayed or the text do not match.");
            return  false;
        }
    }

    // Validates if the password and current password matches.
    public boolean validatePassword(String password, String confirmPassword, String message) {
        try {
            typeOnElement(passwordInput, password);
            typeOnElement(confirmPasswordInput, confirmPassword);
            clickOnSubmit();
            return (passwordMatchErrorMsg.isDisplayed() && getTextFromElement(passwordMatchErrorMsg).equals(message));
        } catch (Exception e) {
            System.out.println("The error message was not displayed.");
            return  false;
        }
    }

    // Verifies if the error messages for the mandatory fields are displayed
    public boolean vaidateMandatoryFields(List<String> list) {
        try {
            return (getTextFromElement(firstNameErrorMsg).equals(list.get(0))
                    && getTextFromElement(lastNameErrorMsg).equals(list.get(1))
                    && getTextFromElement(emailErrorMsg).equals(list.get(2))
                    && getTextFromElement(usernameErrorMsg).equals(list.get(3))
                    && getTextFromElement(passwordErrorMsg).equals(list.get(4))
                    && getTextFromElement(confirmPasswordErrorMsg).equals(list.get(5))
                    && getTextFromElement(questionErrorMsg).equals(list.get(6))
                    && getTextFromElement(answerErrorMsg).equals(list.get(7)));
        } catch (Exception e) {
            System.out.println("Some mandatory fields are required. \n" + e);
            return false;
        }
    }
}
