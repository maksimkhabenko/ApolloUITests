import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import pageobj.pages.models.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLogin extends TestBase {


    @Test
    public void test(){
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.logining(testConfig.getStandartWallet().getUser());
    }

    @Test
    @DisplayName("Log In by Secret Phrase")
    @Order(1)
    public void testLogInBySecretPhrase () throws Exception {
        LoginPage loginPage = pageManager.getloginPage();
        log.info("Step 2: Switch to Login by Secret Phrase");
        loginPage.switchToNonActiveTab();
        log.info("Step 3: Enter Secret Phrase data");
        loginPage.enterSecretPhrase("0");
        log.info("Step 4: Click on Submit Button");
        loginPage.clickSubmitBtn();
        log.info("Step 5: Check URL = dashboard");
        assertTrue(webDriver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    @DisplayName("NEGATIVE: Log in without any password (empty field)")
    @Order(5)
    public void testLogInByEmptySecretPhraseNegative() {
        LoginPage loginPage = pageManager.getloginPage();
        log.info("Step 2: Switch to Login by Secret Phrase");
        loginPage.switchToNonActiveTab();
        log.info("Step 3: Click on Submit Button");
        loginPage.clickSubmitBtn();
        log.info("Step 4: Check that Error Notification Secret Phrase is required is present");
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return loginPage.getNotificationTitle().equals("Error");
            }
        });
        assertEquals("Secret Phrase is required.",loginPage.getNotificationMessage());
    }
}
