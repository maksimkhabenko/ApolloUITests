package pages.elements;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementBase implements IWebElements {
    protected WebElement webElement;

    public WebElementBase(WebElement webElement) {
        this.webElement = webElement;
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
