package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    // Home page locators
    @FindBy(tagName = "h3") WebElement categoriesTitle;
    @FindBy(id = "ctl00_Main_CategoryBrowser_TopCategoryList_ctl00_TopCategoryButton") WebElement antiquesCatTitle;
    @FindBy(id = "ctl00_Main_CategoryBrowser_TopCategoryList_ctl01_TopCategoryButton") WebElement artsCatTitle;
    @FindBy(id = "ctl00_Main_CategoryBrowser_TopCategoryList_ctl02_TopCategoryButton") WebElement autoCatTitle;
    @FindBy(id = "ctl00_Main_CategoryBrowser_TopCategoryList_ctl03_TopCategoryButton") WebElement electronicsCatTitle;
    @FindBy(id = "ctl00_Main_CategoryBrowser_TopCategoryList_ctl04_TopCategoryButton") WebElement gardenCatTitle;
    @FindBy(id = "ctl00_Main_CategoryBrowser_TopCategoryList_ctl05_TopCategoryButton") WebElement homeCatTitle;
    @FindBy(id = "ctl00_Main_CategoryBrowser_TopCategoryList_ctl06_TopCategoryButton") WebElement musicCatTitle;
    @FindBy(id = "ctl00_LoginView_MemberName") WebElement memberName;

    // Menu
    @FindBy(id = "ctl00_LoginView_LoginLink") WebElement loginLink;
    @FindBy(id = "ctl00_LoginView_RegisterLink") WebElement registerLink;

    // Top menu locators
    @FindBy(xpath = "// span[@id='ctl00_SecondBar_SiteMapPath']//span[3]") WebElement tabTitle;
    @FindBy(id = "ctl00_TopMenuRepeater_ctl00_MenuLink") WebElement homeLink;
    @FindBy(id = "ctl00_TopMenuRepeater_ctl01_MenuLink") WebElement postAndAdLink;
    @FindBy(id = "ctl00_TopMenuRepeater_ctl02_MenuLink") WebElement myAdsAndProfileLink;

    List<WebElement> categories = new ArrayList<>();

    // Constructor method
    public HomePage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        categories.add(antiquesCatTitle);
        categories.add(artsCatTitle);
        categories.add(autoCatTitle);
        categories.add(electronicsCatTitle);
        categories.add(gardenCatTitle);
        categories.add(homeCatTitle);
        categories.add(musicCatTitle);
    }

    // Verifies if the main web elements have already loaded
    public boolean verifyLoads(){
        try {
            return (waitForElementVisible(categoriesTitle)
                    && waitForElementEnabled(antiquesCatTitle)
                    && waitForElementEnabled(artsCatTitle)
                    && waitForElementEnabled(autoCatTitle)
                    && waitForElementEnabled(electronicsCatTitle)
                    && waitForElementEnabled(gardenCatTitle)
                    && waitForElementEnabled(homeCatTitle)
                    && waitForElementEnabled(musicCatTitle)
                    && waitForElementEnabled(homeLink)
                    && waitForElementEnabled(postAndAdLink)
                    && waitForElementEnabled(myAdsAndProfileLink));
        } catch (Exception e) {
            System.out.println("Some elements are not visible yet. \n" + e);
            return false;
        }
    }

    // Verifies if the member name is displayed
    public boolean verifyUsr(String username){
        try{
            return (memberName.isDisplayed() && getTextFromElement(memberName).equals(username));
        } catch (Exception e) {
            System.out.println("The username is not visible yet.");
            return true;
        }
    }

    // Returns the categories title
    public String getPageTitle(){
        return getTextFromElement(categoriesTitle);
    }

    // Clicks over the login link.
    public LoginPage clickOnLogin(){
        clickOnElement(loginLink);
        return new LoginPage(driver);
    }

    // Clicks over the register link.
    public RegisterPage clickOnRegister(){
        clickOnElement(registerLink);
        return new RegisterPage(driver);
    }

    // Clicks over the Home menu item
    public void clickOnHome(){
        clickOnElement(homeLink);
    }

    // Clicks over the Post An Ad menu item
    public PostAnAdPage clickOnPostAnAd(){
        clickOnElement(postAndAdLink);
        return new PostAnAdPage(driver);
    }

    // Clicks over the My Ads & Profile menu item
    public MyAdsAndProfilePage clickOnMyAdsAndProfile(){
        clickOnElement(myAdsAndProfileLink);
        return new MyAdsAndProfilePage(driver);
    }

    public String getTabTitle(){
        return getTextFromElement(tabTitle);
    }

    public CategoriesPage clickOnCategory(String category) {
        for (WebElement cat : categories) {
            if (category.equals(getTextFromElement(cat))) {
                clickOnElement(cat);
                break;
            }
        }
        return new CategoriesPage(driver);
    }
}
