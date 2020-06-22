package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationPage extends BasePage {

    // Web elements
    @FindBy(id = "ctl00_TopMenuRepeater_ctl00_MenuLink") WebElement homeLink;
    @FindBy(id = "ctl00_TopMenuRepeater_ctl01_MenuLink") WebElement postAndAdLink;
    @FindBy(id = "ctl00_TopMenuRepeater_ctl02_MenuLink") WebElement myAdsAndProfileLink;

    // Constructor method
    public NavigationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean verifyLoad(){
        try {
            return (waitForElementVisible(homeLink)
                    && waitForElementVisible(postAndAdLink)
                    && waitForElementVisible(myAdsAndProfileLink));
        } catch (Exception e){
            System.out.println("Some elements are not visible yet. \n" + e);
            return false;
        }
    }

    // Makes a click over the Home hyperlink
    public HomePage goToHome(){
        clickOnElement(homeLink);
        return new HomePage(driver);
    }

    // Makes a click over the Home hyperlink
    public HomePage goToPostAnAdd(){
        clickOnElement(postAndAdLink);
        return new HomePage(driver);
    }

    // Makes a click over the Home hyperlink
    public HomePage goToMyAdsAndProfile(){
        clickOnElement(myAdsAndProfileLink);
        return new HomePage(driver);
    }
}
