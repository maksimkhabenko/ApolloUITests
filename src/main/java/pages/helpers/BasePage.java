package pages.helpers;

import conf.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public abstract class BasePage implements IWebPage {
    public final WebDriverWait wait;
    protected WebDriver driver;
     public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,15);
        PageFactory.initElements(new CustomFieldDecorator(driver),  this);
    }


}
