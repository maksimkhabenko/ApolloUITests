package pageobj.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobj.pages.IWebPage;
import pageobj.pages.utils.CustomFieldDecorator;

public abstract class BasePage implements IWebPage {
    protected WebDriver driver;
    protected JavascriptExecutor executor;
     public BasePage(WebDriver driver) {
        this.driver = driver;
        this.executor = (JavascriptExecutor)driver;
        PageFactory.initElements(new CustomFieldDecorator(driver),  this);
    }



}
