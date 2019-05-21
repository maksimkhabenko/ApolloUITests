package pageobj.elements.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobj.elements.WebElementBase;

public class Notification extends WebElementBase {
    public Notification(WebElement webElement) {
        super(webElement);
    }


    public String getTitle(){
        return webElement.findElement(By.className("title")).getText();
    }

    public String getMeassage(){
        return webElement.findElement(new By.ByClassName("message")).getText();
    }

    @Override
    public void click() {
        super.click();
    }
}
