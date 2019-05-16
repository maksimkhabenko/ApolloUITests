package pages.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObjectManager {
    private WebDriver driver;
    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public  <TPage extends BasePage> TPage GetPage (Class<TPage> pageClass) throws Exception {
        try {
            //Initialize the Page with its elements and return it.
            return PageFactory.initElements(driver,  pageClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
