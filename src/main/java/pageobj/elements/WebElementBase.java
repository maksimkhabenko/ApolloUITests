package pageobj.elements;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageobj.pages.utils.CustomFieldDecorator;

public class WebElementBase implements IWebElements {
    protected WebElement webElement;

    public WebElementBase(WebElement webElement) {
        this.webElement = webElement;
    }

    public WebElementBase(WebElement webElement, WebDriver webDriver) {
        this.webElement = webElement;
        PageFactory.initElements(new CustomFieldDecorator(webDriver),  this);
    }
    public void click(){

        if (webElement !=null) {
            webElement.click();
        }
        else {
            throw new ElementNotVisibleException(webElement.getText());
        }
    }

    public void writeText(String text){
        if (webElement !=null) {
            webElement.sendKeys(text);
        }
        else {
            throw new ElementNotVisibleException(webElement.getText());
        }
    }

    public String readText(){
        if (webElement !=null) {
           return webElement.getText();
        }
        else {
            throw new ElementNotVisibleException(webElement.getText());
        }
    }

}
