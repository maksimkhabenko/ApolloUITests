package pageobj.elements.models;

import org.openqa.selenium.WebElement;
import pageobj.elements.WebElementBase;

public class TextField extends WebElementBase {
    public TextField(WebElement webElement) {
        super(webElement);
    }

    @Override
    public void writeText(String text) {
        super.writeText(text);
    }
    public void clear(){webElement.clear();}
}
