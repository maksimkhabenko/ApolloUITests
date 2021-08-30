import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import pageobj.elements.models.Notification;
import pageobj.pages.models.DashboardPage;
import pageobj.pages.models.LoginPage;
import pageobj.pages.models.modals.SendMoneyModal;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
public class TestSendMoney extends TestBase {

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
    @DisplayName("SendMoney from Standard Wallet")
    @Order(1)
    void testSendMoneyStandardWallet () {
        LoginPage loginPage = getPage(LoginPage.class);
        DashboardPage dashboardPage = getPage(DashboardPage.class);
        SendMoneyModal sendMoneyModal;
        int newAmount;
        String amountAtm = String.valueOf((int) (Math.random() * 100)+1);
        String feeAtm = "5";

        log.info("Step 2: Switch to Login by Secret Phrase");
        loginPage.switchToNonActiveTab();

        log.info("Step 3: Enter Secret Phrase");
        loginPage.enterSecretPhrase(testConfig.getStandartWallet().getPass());

        log.info("Step 4: Click on Submit Button");
        loginPage.clickSubmitBtn();

        log.info("Step 5: Check URL = dashboard");
        verifyURL("dashboard");
        int oldAmount = Integer.valueOf(dashboardPage.getAmount().replaceAll(" APL", "").replaceAll(",", ""));
        newAmount = oldAmount - Integer.valueOf(amountAtm) - Integer.valueOf(feeAtm);

        log.info("Step 6: Click on SendMoney Button");
        sendMoneyModal = dashboardPage.clickSendMoneyFromDashboard();

        log.info("Step 7: Fill the fields");
        sendMoneyModal.fillSendMoneyModal(testConfig.getVaultWallet().getUser(),amountAtm, feeAtm, testConfig.getStandartWallet().getPass());

        log.info("Step 8: Verify transaction is completed");
        wait.until((ExpectedCondition<Boolean>) d -> Integer.valueOf(dashboardPage.getAmount().replaceAll(" APL", "").replaceAll(",", "")) == newAmount);
        wait.until((ExpectedCondition<Boolean>) d -> Integer.valueOf(dashboardPage.getLastTransactionAmount(0).replaceAll("-", "")) == Integer.valueOf(amountAtm));
    }

    @Test
    @DisplayName("SendMoney from Vault Wallet -> Click on button in Header")
    @Order(2)
    void testSendMoneyVaultWallet () {
        LoginPage loginPage = getPage(LoginPage.class);
        DashboardPage dashboardPage = getPage(DashboardPage.class);
        SendMoneyModal sendMoneyModal;
        int newAmount;
        String amountAtm = String.valueOf((int) (Math.random() * 100)+1);
        String feeAtm = "5";

        log.info("Step 2: Log In by Account ID");
        loginPage.enterAccountID(testConfig.getVaultWallet().getUser().substring(3));

        log.info("Step 3: Click on Submit Button");
        loginPage.clickSubmitBtn();

        log.info("Step 5: Check URL = dashboard");
        verifyURL("dashboard");
        int oldAmount = Integer.valueOf(dashboardPage.getAmount().replaceAll(" APL", "").replaceAll(",", ""));
        newAmount = oldAmount - Integer.valueOf(amountAtm) - Integer.valueOf(feeAtm);

        log.info("Step 6: Click on SendMoney Button");
        sendMoneyModal = dashboardPage.clickSendMoneyFromHeader();

        log.info("Step 7: Fill the fields");
        sendMoneyModal.fillSendMoneyModal(testConfig.getStandartWallet().getUser(),amountAtm, feeAtm, testConfig.getVaultWallet().getPass());

        log.info("Step 8: Verify that balance is changed");
        wait.until((ExpectedCondition<Boolean>) d -> Integer.valueOf(dashboardPage.getAmount().replaceAll(" APL", "").replaceAll(",", "")) == newAmount);
        wait.until((ExpectedCondition<Boolean>) d -> Integer.valueOf(dashboardPage.getLastTransactionAmount(0).replaceAll("-", "")) == Integer.valueOf(amountAtm));

    }

    @Test
    @DisplayName("data is saved in modal window")
    @Order(3)
    void testDataIsFilledInSendMoneyModalWindow () {
        LoginPage loginPage = getPage(LoginPage.class);
        DashboardPage dashboardPage = getPage(DashboardPage.class);
        SendMoneyModal sendMoneyModal;
        String amountAtm = String.valueOf((int) (Math.random() * 100)+1);
        String feeAtm = "5";

        log.info("Step 2: Switch to Login by Secret Phrase");
        loginPage.switchToNonActiveTab();

        log.info("Step 3: Enter Secret Phrase");
        loginPage.enterSecretPhrase(testConfig.getStandartWallet().getPass());

        log.info("Step 4: Click on Submit Button");
        loginPage.clickSubmitBtn();

        log.info("Step 5: Fill data in Send Money section");
        verifyURL("dashboard");
        dashboardPage.enterSendMoneyData(testConfig.getStandartWallet().getUser(), amountAtm, feeAtm);

        log.info("Step 6: Click on SendMoney Button");
        sendMoneyModal = dashboardPage.clickSendMoneyFromDashboard();

        log.info("Step 7: Check data is already filled by information");
        assertTrue(sendMoneyModal.getAccountIdFromSendMoneyModal().equals(testConfig.getStandartWallet().getUser()));
        assertTrue(sendMoneyModal.getAmountAtmFromSendMoneyModal().equals(amountAtm));
        assertTrue(sendMoneyModal.getFeeAtmFromSendMoneyModal().equals(feeAtm));

    }






}

