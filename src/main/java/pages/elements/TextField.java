package pages.elements;

import org.openqa.selenium.WebElement;

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
