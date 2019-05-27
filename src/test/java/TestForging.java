import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobj.elements.models.Notification;
import pageobj.pages.models.DashboardPage;
import pageobj.pages.models.LoginPage;
import pageobj.pages.models.modals.UserProfileModal;

import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TransferQueue;

import static org.junit.jupiter.api.Assertions.*;


public class TestForging extends TestBase {

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
    @DisplayName("Start/Stop Forging on Standard wallet")
    @Order(1)
    void testForgingStandardWallet () throws InterruptedException {
        LoginPage loginPage = getPage(LoginPage.class);
        DashboardPage dashboardPage = getPage(DashboardPage.class);
        String oldForgingStatus;
        String newForgingStatus;

        log.info("Step 2: Switch to Login by Secret Phrase");
        loginPage.switchToNonActiveTab();

        log.info("Step 3: Enter Secret Phrase data");
        loginPage.enterSecretPhrase(testConfig.getStandartWallet().getPass());

        log.info("Step 4: Click on Submit Button");
        loginPage.clickSubmitBtn();

        log.info("Step 5: Check URL = dashboard");
        verifyURL("dashboard");

        log.info("Step 6: Verify Effective Balance");
        dashboardPage.openAccountDetails();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("tr:nth-child(6) td:last-child")));
        assertTrue(Double.valueOf(dashboardPage.getEffectiveBalance().replaceAll("Apollo", "").trim())>1000);
        loginPage.closeModalWindow();

        log.info("Step 7: Click General Settings");
        dashboardPage.clickGeneralSettings();
        Thread.sleep(3000);
        oldForgingStatus = dashboardPage.getForgingStatus();

        log.info("Step 8: Click on FORGING button");
        dashboardPage.clickForging();
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector("div[class=\"input-section p-0\"] a label"), oldForgingStatus));
        newForgingStatus = dashboardPage.getForgingStatus();

        log.info("Step 9: Verify Status has been changed");
        assertNotEquals(oldForgingStatus, newForgingStatus);

        log.info("Step 10: ");
        if (newForgingStatus.equals("Forging"))
        {
            dashboardPage.clickForging();
            wait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector("div[class=\"input-section p-0\"] a label"), newForgingStatus));
        }

        log.info("Step 11: Log Out");
        dashboardPage.clickAccountIconBtn().clickLogoutBtn();
        verifyURL("login");
    }

    @Test
    @DisplayName("Start/Stop Forging on Vault wallet")
    @Order(2)
    void testForgingVaultWallet () throws InterruptedException {
        LoginPage loginPage = getPage(LoginPage.class);
        DashboardPage dashboardPage = getPage(DashboardPage.class);
        String oldForgingStatus;
        String newForgingStatus;

        log.info("Step 2: Enter Account ID -> Vault Wallet");
        loginPage.enterAccountID(testConfig.getVaultWallet().getUser().substring(3));

        log.info("Step 3: Click on Submit Button");
        loginPage.clickSubmitBtn();

        log.info("Step 4: Check URL = dashboard");
        verifyURL("dashboard");

        log.info("Step 5: Verify Effective Balance");
        dashboardPage.openAccountDetails();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("tr:nth-child(6) td:last-child")));
        assertTrue(Double.valueOf(dashboardPage.getEffectiveBalance().replaceAll("Apollo", "").trim())>1000);
        loginPage.closeModalWindow();

        log.info("Step 6: Click General Settings");
        dashboardPage.clickGeneralSettings();
        Thread.sleep(3000);
        oldForgingStatus = dashboardPage.getForgingStatus();

        log.info("Step 7: Click on FORGING button and enter PASSWORD");
        dashboardPage.clickForging();
        loginPage.enterSecretPhrase(testConfig.getVaultWallet().getPass());
        loginPage.clickNextBtn();
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector("div[class=\"input-section p-0\"] a label"), oldForgingStatus));
        newForgingStatus = dashboardPage.getForgingStatus();

        log.info("Step 8: Verify Status has been changed");
        assertNotEquals(oldForgingStatus, newForgingStatus);

        log.info("Step 9: Change Forging Status to NOT FORGING");
        if (newForgingStatus.equals("Forging"))
        {
            dashboardPage.clickForging();
            wait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector("div[class=\"input-section p-0\"] a label"), newForgingStatus));
        }

    }




}
