package pageobj.pages.utils;

import org.openqa.selenium.WebElement;
import pageobj.elements.IWebElements;

public class WrapperFactory {
    public static IWebElements createInstance(Class<IWebElements> clazz,
                                              WebElement element) {
        try {
            return clazz.getConstructor(WebElement.class).
                    newInstance(element);
        } catch (Exception e) {
            throw new AssertionError(
                    "WebElement can't be represented as " + clazz
            );
        }
    }
}
