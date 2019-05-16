package pages.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.LoginPage;

import java.util.HashMap;

public class PageManager {
    private WebDriver webDriver;
    private LoginPage loginPage;

    public PageManager(WebDriver driver){
        webDriver = driver;
    }

    public LoginPage getloginPage() {
        if (loginPage == null){ loginPage = new LoginPage(webDriver);}
        return loginPage;
    }

}
