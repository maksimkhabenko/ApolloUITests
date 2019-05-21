package pageobj.pages.utils;

import org.openqa.selenium.WebDriver;
import pageobj.pages.models.DashboardPage;
import pageobj.pages.models.LoginPage;

public class PageManager {
    private WebDriver webDriver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    public PageManager(WebDriver driver){
        webDriver = driver;
    }

    public LoginPage getloginPage() {
        if (loginPage == null){ loginPage = new LoginPage(webDriver);}
        return loginPage;
    }

    public DashboardPage getlDashboardPage() {
        if (dashboardPage == null){ dashboardPage = new DashboardPage(webDriver);}
        return dashboardPage;
    }

}
