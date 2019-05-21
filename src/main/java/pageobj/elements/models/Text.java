package pageobj.elements.models;

import org.openqa.selenium.WebElement;
import pageobj.elements.WebElementBase;

public class Text extends WebElementBase {
    public Text(WebElement webElement) {
        super(webElement);
    }

    @Override
    public String readText() {
        return super.readText();
    }
}
