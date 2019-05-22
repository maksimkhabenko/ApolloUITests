import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import pageobj.elements.models.Notification;
import pageobj.pages.models.DashboardPage;
import pageobj.pages.models.LoginPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLogin extends TestBase {

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        List<Notification> notifications = getPage(LoginPage.class).getNotificationsMessages();
        if (notifications.size() > 0 && notifications.get(0).getMeassage().equals("You are using up to date version")){
            notifications.get(0).click();
        }

    }

    @Test
    @DisplayName("Log In by Secret Phrase")
    @Order(1)
    public void testLogInBySecretPhrase () throws Exception {
        LoginPage loginPage = getPage(LoginPage.class);
        log.info("Step 2: Switch to Login by Secret Phrase");
        loginPage.switchToNonActiveTab();

        log.info("Step 3: Enter Secret Phrase data");
        loginPage.enterSecretPhrase("0");

        log.info("Step 4: Click on Submit Button");
        loginPage.clickSubmitBtn();

        log.info("Step 5: Check URL = dashboard");
        verifyURL("dashboard");
    }

    @Test
    @DisplayName("NEGATIVE: Log in without any password (empty field)")
    @Order(5)
    public void testLogInByEmptySecretPhraseNegative() {
        LoginPage loginPage = getPage(LoginPage.class);

        log.info("Step 2: Switch to Login by Secret Phrase");
        loginPage.switchToNonActiveTab();

        log.info("Step 3: Click on Submit Button");
        loginPage.clickSubmitBtn();

        log.info("Step 4: Check that Error Notification Secret Phrase is required is present");
        loginPage.getNotificationsMessages().stream().anyMatch(msg -> (msg.getMeassage().equals("Secret Phrase is required.")));
        assertTrue(loginPage.getNotificationsMessages().stream().anyMatch(msg -> (msg.getMeassage().equals("Secret Phrase is required."))));

    }

    @Test
    @DisplayName("Log In by Account ID")
    @Order(2)
    public void testLogInByAccountID() throws InterruptedException {
        LoginPage loginPage = getPage(LoginPage.class);
        DashboardPage dashboardPage = getPage(DashboardPage.class);

        log.info("Step 2: Log In by Account ID");
        loginPage.enterAccountID(testConfig.getStandartWallet().getUser().substring(3));

        log.info("Step 3: Click on Submit Button");
        loginPage.clickSubmitBtn();

        log.info("Step 4: Verify User Account Rs");
        dashboardPage.getUserAccountRs().equals(testConfig.getStandartWallet().getUser());

        log.info("Step 5: Log out");
        dashboardPage.clickAccountIconBtn().clickLogoutBtn();

        log.info("Step 6: Check Log In Page");
        verifyURL("login");

    }

    @Test
    @DisplayName("NEGATIVE: Log In by Invalid Account ID")
    @Order(6)
    public void testLogInByAccountIDNegative() {
        LoginPage loginPage = getPage(LoginPage.class);
        log.info("Step 2: Log In by Invalid Account ID");
        loginPage.enterAccountID("negativetest");
        log.info("Step 3: Press ENTER button");
        loginPage.clickSubmitBtn();
        log.info("Step 4: Check that Error Notification is present");
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return  loginPage.getNotificationsMessages().stream().anyMatch(msg -> (msg.getMeassage().equals("Incorrect \"account\"")));
            }
        });
        assertTrue(loginPage.getNotificationsMessages().stream().anyMatch(msg -> (msg.getMeassage().equals("Incorrect \"account\""))));
    }


}
