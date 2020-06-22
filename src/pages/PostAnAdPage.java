package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostAnAdPage extends BasePage {

    // Locators
    @FindBy(xpath = "//span[@id='ctl00_SecondBar_SiteMapPath']//span[3]") WebElement pageTitle;

    // Constructor method
    public PostAnAdPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Returns the page title
    public String getPageTitle(){
        return getTextFromElement(pageTitle);
    }
}
