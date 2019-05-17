package pageobj.elements.models;

import org.openqa.selenium.WebElement;
import pageobj.elements.WebElementBase;

public class Notification extends WebElementBase {
    public Notification(WebElement webElement) {
        super(webElement);
    }

    @Override
    public String readText() {
        System.out.println(super.readText());
        if (super.readText().contains("You are using up to date version")){
            super.click();
        }
        return super.readText();
    }
}
