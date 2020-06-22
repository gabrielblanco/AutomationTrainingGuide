package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    // Web driver
    WebDriver driver;

    // Constructor method
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    // Get the base page URL
    public void getBaseUrl(String url) {
        try{
            driver.get(url);
        } catch (Exception e){
            System.out.println("Unable to find the specified URL: " + e.toString());
        }
    }

    // This method will type some text into a valid web element.
    public boolean typeOnElement(WebElement element, String text)
    {
        try {
            if (waitForElementEnabled(element)){
                element.clear();
                element.sendKeys(text);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Unable to type on element: " + e);
            return false;
        }
    }

    // Returns the text from an specific web element
    public String getTextFromElement(WebElement element)
    {
        try{
            return (waitForElementVisible(element)) ? element.getText() : "";
        } catch (Exception e) {
            System.out.println("Unable to get text from element.");
            return "";
        }
    }

    // Returns a boolean value if the web element is clickable
    public boolean clickOnElement(WebElement element) {
        try{
            if (waitForElementEnabled(element)) {
                element.click();
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Unable to click on element: " + e.toString());
            return false;
        }
    }

    // Validates if the web element is visible
    public boolean waitForElementVisible(WebElement element) {
        try{
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            System.out.println("The element is not visible: " + e.toString());
            return false;
        }
    }

    // Validates if a web element is enable
    public boolean waitForElementEnabled(WebElement element) {
        try{
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            System.out.println("The element continues disabled: " + e.toString());
            return false;
        }

    }

    // Validates if the web element is visible
    public boolean waitForElementNotVisible(WebElement element) {
        try{
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.invisibilityOf(element));
            return true;
        } catch (Exception e) {
            System.out.println("The element is always visible: " + e.toString());
            return false;
        }
    }

    // Returns the web element related to an specific locator.
    public WebElement findElement(By element){
        return driver.findElement(element);
    }
}
