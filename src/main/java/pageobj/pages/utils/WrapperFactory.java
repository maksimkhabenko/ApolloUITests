package pageobj.pages.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobj.elements.IWebElements;

public class WrapperFactory {
    public static IWebElements createInstance(Class<IWebElements> clazz,
                                              WebElement element, WebDriver driver) {
        try {
            IWebElements webElements = clazz.getConstructor(WebElement.class).newInstance(element);
            webElements.setDriver(driver);
            return webElements;
        } catch (Exception e) {
            throw new AssertionError(
                    "WebElement can't be represented as " + clazz
            );
        }
    }
}
