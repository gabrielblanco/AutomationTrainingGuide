package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CategoriesPage extends BasePage {

    // Locators
    @FindBy(tagName = "h2") WebElement pageTitle;
    @FindBy(id = "ctl00_Main_CategoryDropDown_CategoryList") WebElement searchDropdown;

    // Constructor method
    public CategoriesPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Returns the page title
    public String getPageTitle(){
        return getTextFromElement(pageTitle);
    }

    public String getSearchFocus() {
        Select categoriesDropdown = new Select(searchDropdown);
        return getTextFromElement(categoriesDropdown.getFirstSelectedOption());
    }
}
