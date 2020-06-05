import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void getBaseUrl(String url) {
        try{
            driver.get(url);
        } catch (Exception e){
            System.out.println("Unable to find the specified URL: " + e.toString());
        }
    }

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

    public String getTextFromElement(WebElement element)
    {
        try{
            if(waitForElementVisible(element)){
                return element.getText();
            }
            return "";
        } catch (Exception e) {
            System.out.println("Unable to get text from element.");
            return "";
        }
    }

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

    public WebElement findElement(By element){
        return driver.findElement(element);
    }
}
