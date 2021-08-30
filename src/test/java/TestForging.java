import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import pageobj.elements.models.Notification;
import pageobj.pages.models.DashboardPage;
import pageobj.pages.models.LoginPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
public class TestForging extends TestBase {

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        List<Notification> notifications = getPage(LoginPage.class).getNotificationsMessages();
        if (notifications.size() > 0 && notifications.get(0).getMessage().equals("You are using up to date version")){
            notifications.get(0).click();
        }

    }

    @Test
    @DisplayName("Start/Stop Forging on Standard wallet")
    @Order(1)
    void testForgingStandardWallet ()  {
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

        assertTrue(Double.parseDouble(dashboardPage.getEffectiveBalance().replaceAll("Apollo", "").trim())>1000);
        loginPage.closeModalWindow();

        log.info("Step 7: Click General Settings");
        dashboardPage.clickGeneralSettings();
        oldForgingStatus = dashboardPage.getForgingStatus();

        log.info("Step 8: Click on FORGING button");
        dashboardPage.clickForging();

        try {
            wait.until((ExpectedCondition<Boolean>) d -> !dashboardPage.getForgingStatus().equals(oldForgingStatus));
        }catch (Exception ignored) {
        }finally {
            newForgingStatus = dashboardPage.getForgingStatus();
            log.info("Step 9: Verify Status has been changed");
            assertNotEquals(oldForgingStatus, newForgingStatus);
        }

        log.info("Step 10: ");
        if (newForgingStatus.equals("Forging"))
        {
            dashboardPage.clickForging();
            try {
                wait.until((ExpectedCondition<Boolean>) d -> !dashboardPage.getForgingStatus().equals("Forging"));
            }catch (Exception ignored){}
        }

        log.info("Step 11: Log Out");
        dashboardPage.clickAccountIconBtn().clickLogoutBtn();
        verifyURL("login");
    }

    @Test
    @DisplayName("Start/Stop Forging on Vault wallet")
    @Order(2)
    void testForgingVaultWallet ()  {
        LoginPage loginPage = getPage(LoginPage.class);
        DashboardPage dashboardPage = getPage(DashboardPage.class);
        String oldForgingStatus;
        String newForgingStatus;

        importSCFile(loginPage,testConfig.getVaultWallet().getUser(),testConfig.getVaultWallet().getPass());

        if (loginPage.getNotificationsMessages().stream().anyMatch(msg -> (msg.getMessage().equals("Vault wallet for account was not import : Already exist")))){
            loginPage.closeModalWindow();
        }

        log.info("Step 2: Enter Account ID -> Vault Wallet");
        loginToWallet(loginPage,testConfig.getVaultWallet().getUser());

        log.info("Step 3: Check URL = dashboard");
        verifyURL("dashboard");

        log.info("Step 4: Verify Effective Balance");
        dashboardPage.openAccountDetails();
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("tr:nth-child(6) td:last-child")));
        try {
            wait.until((ExpectedCondition<Boolean>) d -> Double.parseDouble(dashboardPage.getEffectiveBalance().replaceAll("Apollo", "").trim())>1000);
        }catch (Exception ignored){}
        assertTrue(Double.parseDouble(dashboardPage.getEffectiveBalance().replaceAll("Apollo", "").trim())>1000);
        loginPage.closeModalWindow();

        log.info("Step 5: Click General Settings");
        dashboardPage.clickGeneralSettings();
        
        oldForgingStatus = dashboardPage.getForgingStatus();

        log.info("Step 6: Click on FORGING button and enter PASSWORD");
        dashboardPage.clickForging();
        loginPage.enterSecretPhrase(testConfig.getVaultWallet().getPass());
        loginPage.clickNextBtn();

        try {
            wait.until((ExpectedCondition<Boolean>) d -> !dashboardPage.getForgingStatus().equals(oldForgingStatus));
        }catch (Exception ignored){}

        newForgingStatus = dashboardPage.getForgingStatus();

        log.info("Step 7: Verify Status has been changed");
        assertNotEquals(oldForgingStatus, newForgingStatus);

        if (newForgingStatus.equals("Forging"))
        {
            dashboardPage.clickForging();
            try {
                wait.until((ExpectedCondition<Boolean>) d -> !dashboardPage.getForgingStatus().equals("Forging"));
            }catch (Exception ignored){}
        }

    }




}
