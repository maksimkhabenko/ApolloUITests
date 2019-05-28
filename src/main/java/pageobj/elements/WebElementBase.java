package pageobj.elements;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobj.pages.utils.CustomFieldDecorator;

public class WebElementBase implements IWebElements {
    protected WebElement webElement;
    private WebDriver webDriver;
    private JavascriptExecutor executor;
    private WebDriverWait wait;

    public WebElementBase(WebElement webElement) {
        this.webElement = webElement;
    }

    public WebElementBase(WebElement webElement, WebDriver webDriver) {
        this.webElement = webElement;
        this.webDriver  = webDriver;
        PageFactory.initElements(new CustomFieldDecorator(webDriver),  this);
    }

    public void click(){
        wait.until(ExpectedConditions.visibilityOf(webElement));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
         if (webElement != null) {
             try {
               webElement.click();
             }catch (Exception e){
                // e.printStackTrace();
                 executor.executeScript("arguments[0].click();", webElement);
             }

         } else {
             throw new ElementNotVisibleException(webElement.getText());
         }
    }

    public void writeText(String text){
        wait.until(ExpectedConditions.visibilityOf(webElement));
        if (webElement !=null) {
            webElement.click();
            webElement.clear();
            webElement.sendKeys(text);
        }
        else {
            throw new ElementNotVisibleException(webElement.getText());
        }
    }

    public String readText(){
        wait.until(ExpectedConditions.visibilityOf(webElement));
        if (webElement !=null) {
           return webElement.getText();
        }
        else {
            throw new ElementNotVisibleException(webElement.getText());
        }
    }

    public WebElement getWebElement() {
        return webElement;
    }

    @Override
    public void setDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.executor = (JavascriptExecutor)webDriver;
        this.wait = new WebDriverWait(webDriver,15);
    }
}
