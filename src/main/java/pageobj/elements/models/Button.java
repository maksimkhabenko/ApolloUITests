package pageobj.elements.models;

import org.openqa.selenium.WebElement;
import pageobj.elements.WebElementBase;

public class Button extends WebElementBase {
    public Button(WebElement webElement) {
        super(webElement);
    }

    @Override
    public String readText() {
        return super.readText();
    }

    @Override
    public void click() {
        super.click();
    }
}
