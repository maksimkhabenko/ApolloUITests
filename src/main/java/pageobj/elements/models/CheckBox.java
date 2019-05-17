package pageobj.elements.models;

import org.openqa.selenium.WebElement;
import pageobj.elements.WebElementBase;

public class CheckBox extends WebElementBase {
    public CheckBox(WebElement webElement) {
        super(webElement);
    }

    @Override
    public String readText() {
        return super.readText();
    }

    public void setChecked(boolean value) {
        if (value != isChecked()) {
            webElement.click();
        }
    }

    public boolean isChecked() {
        return webElement.isSelected();
    }
}
