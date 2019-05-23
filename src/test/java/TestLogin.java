import org.junit.jupiter.api.*;
import pageobj.elements.models.Notification;
import pageobj.pages.models.DashboardPage;
import pageobj.pages.models.LoginPage;
import pageobj.pages.models.modals.UserProfileModal;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
    void testLogInBySecretPhrase () {
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
    void testLogInByEmptySecretPhraseNegative() {
        LoginPage loginPage = getPage(LoginPage.class);

        log.info("Step 2: Switch to Login by Secret Phrase");
        loginPage.switchToNonActiveTab();

        log.info("Step 3: Click on Submit Button");
        loginPage.clickSubmitBtn();

        log.info("Step 4: Check that Error Notification Secret Phrase is required is present");
        verifyNotifications(loginPage.getNotificationsMessages(),"Secret Phrase is required.");

    }

    @Test
    @DisplayName("Log In by Account ID")
    @Order(2)
    void testLogInByAccountID() {
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
    void testLogInByAccountIDNegative() {
        LoginPage loginPage = getPage(LoginPage.class);

        log.info("Step 2: Log In by Invalid Account ID");
        loginPage.enterAccountID("negativetest");

        log.info("Step 3: Press ENTER button");
        loginPage.clickSubmitBtn();

        log.info("Step 4: Check that Error Notification is present");
        verifyNotifications(loginPage.getNotificationsMessages(),"Incorrect \"account\"");

    }

    @Test
    @DisplayName("NEGATIVE: Log In without any Account ID")
    @Order(7)
    void testLogInByEmptyAccountIDNegative(){
        LoginPage loginPage = getPage(LoginPage.class);

        log.info("Step 2: Click on Submit Button");
        loginPage.clickSubmitBtn();

        log.info("Step 3: Check that Error Notification is present");
        verifyNotifications(loginPage.getNotificationsMessages(),"Account ID is required.");
    }

    @Test
    @DisplayName("Log Out")
    @Order(3)
    void testLogOut () throws Exception {
        LoginPage loginPage = getPage(LoginPage.class);
        DashboardPage dashboardPage = getPage(DashboardPage.class);
        UserProfileModal userProfileModal;

        log.info("Step 2: Switch to Login by Secret Phrase");
        loginPage.switchToNonActiveTab();

        log.info("Step 3: Enter Secret Phrase data");
        loginPage.enterSecretPhrase("0");

        log.info("Step 4: Click on Submit Button");
        loginPage.clickSubmitBtn();

        log.info("Step 5: Press on Account Icon");
         userProfileModal = dashboardPage.clickAccountIconBtn();

        log.info("Step 6: Click on Log out button");
        userProfileModal.clickLogoutBtn();

        log.info("Step 7: Wait for Login Page is present");
        verifyURL("login");
    }

    @Test
    @DisplayName("NEGATIVE: Press BACKSPACE button after logging out")
    @Order(2)
    void testBackSpaceBtn (){
        LoginPage loginPage = getPage(LoginPage.class);
        DashboardPage dashboardPage = getPage(DashboardPage.class);
        UserProfileModal userProfileModal;

        log.info("Step 2: Switch to Login by Secret Phrase");
        loginPage.switchToNonActiveTab();

        log.info("Step 3: Enter Secret Phrase data");
        loginPage.enterSecretPhrase("0");

        log.info("Step 4: Click on Submit Button");
        loginPage.clickSubmitBtn();


        log.info("Step 5: Press on Account Icon");
        userProfileModal = dashboardPage.clickAccountIconBtn();

        log.info("Step 6: Click on Log out button");
        userProfileModal.clickLogoutBtn();

        log.info("Step 7: Wait for Login Page is present");
        verifyURL("login");

        log.info("Step 8: Press BACKSPACE button");
        webDriver.navigate().back();

        log.info("Step 9: Wait for Login Page is present");
        verifyURL("login");

    }

    @Test
    @DisplayName("Close Modal Window")
    @Order(4)
    void testExitModalWindowBtn ()  {
        LoginPage loginPage = getPage(LoginPage.class);

        log.info("Step 2: Click on Create New User? button");
        loginPage.clickNewUserBtn();

        log.info("Step 3: Click on Exit button");
        loginPage.closeModalWindow();

        log.info("Step 4: Check that modal window is closed");
        verifyURL("login");
    }

    @Test
    @DisplayName("Create Vault Wallet with randomly generated secret phrase")
    @Order(9)
    void testCreateVaultWalletRandomlyGeneratedSecretPhrase()  {
        LoginPage loginPage = getPage(LoginPage.class);
        DashboardPage dashboardPage = getPage(DashboardPage.class);
        String secretPhrase;
        String accountId;

        log.info("Step 2: Click on Create New User? button");
        loginPage.clickNewUserBtn();

        //log.info("Step 3: Verify Create New User page");
        //website.loginPage().verifyCreateStandardWallet();

        log.info("Step 4: Switch to Vault Wallet");
        loginPage.switchToNonActiveTab();

       // log.info("Step 5: Verify Create new vault wallet page");
        //website.loginPage().verifyCreateVaultWallet();

        log.info("Step 6: Press Create Account button");
        loginPage.clickCreateAccountBtn();

        //log.info("Step 7: Verify Create New Vault Wallet Page (Second Step)");
        //website.loginPage().verifyCreateVaultWalletSecondStep();

        secretPhrase = loginPage.copySecretPhrase();
        accountId = loginPage.copyAccountId();

        log.info("Step 6: Click on CheckBox");
        loginPage.clickCheckboxDataStored();

        log.info("Step 7: Click on Next Button");
        loginPage.clickNextBtn();

        log.info("Step 8: Input Secret Phrase");
        loginPage.enterSecretPhrase(secretPhrase);

        log.info("Step 9: Press Create New Account button ");
        loginPage.clickSubmitBtn();

        log.info("Step 10: Verify User Account Rs");
        assertEquals(dashboardPage.getUserAccountRs(),accountId);

        log.info("Step 11: Do Log out");
        dashboardPage.clickAccountIconBtn().clickLogoutBtn();

        log.info("Step 12: Check Log In Page");
        verifyURL("login");
    }

    @Test
    @DisplayName("NEGATIVE: VAULT WALLET -> Error message is present when User didn't store private data")
    @Order(11)
    @Tag("NEGATIVE")
    void testCreateVaultWalletVerifyStoreDataNegative() {
        LoginPage loginPage = getPage(LoginPage.class);

        log.info("Step 2: Click on Create New User? button");
        loginPage.clickNewUserBtn();

        //log.info("Step 3: Verify Create New User page");
        //website.loginPage().verifyCreateStandardWallet();

        log.info("Step 4: Switch to Vault Wallet");
        loginPage.switchToNonActiveTab();

        log.info("Step 5: Verify Create new vault wallet page");
        loginPage.switchToNonActiveTab();

        log.info("Step 6: Press Create Account button");
        loginPage.clickCreateAccountBtn();

        // log.info("Step 7: Verify Create New Vault Wallet Page (Second Step)");
        //loginPage.verifyCreateVaultWalletSecondStep();

        log.info("Step 8: Click on Next Button");
        loginPage.clickNextBtn();

        log.info("Step 9: Check that error message is present");
        verifyNotifications(loginPage.getNotificationsMessages(),"You have to verify that you stored your private data.");

        log.info("Step 10: Close modal window");
        loginPage.closeModalWindow();

        log.info("Step 11: Verify Log In page");
        verifyURL("login");
    }

    @Test
    @DisplayName("Create Vault Wallet with custom secret phrase")
    @Order(10)
    void testCreateVaultWalletCustomSecretPhrase(){
        LoginPage loginPage = getPage(LoginPage.class);
        DashboardPage dashboardPage = getPage(DashboardPage.class);

        String secretPhrase;
        String accountId;
        log.info("Step 2: Click on Create New User? button");
        loginPage.clickNewUserBtn();

        //log.info("Step 3: Verify Create New User page");
        //website.loginPage().verifyCreateStandardWallet();

        log.info("Step 4: Switch to Vault Wallet");
        loginPage.switchToNonActiveTab();

        //log.info("Step 5: Verify Create new vault wallet page");
        //website.loginPage().verifyCreateVaultWallet();

        log.info("Step 6: Use custom secret phrase");
        loginPage.clickCheckBoxCustomSecretPhrase();

        log.info("Step 7: Input custom secret phrase");
        loginPage.enterCustomSecretPhrase("automationtesting");

        log.info("Step 6: Press Create Account button");
        loginPage.clickCreateAccountBtn();

        log.info("Step 7: Verify Create New Vault Wallet Page (Second Step)");
        //website.loginPage().verifyCreateVaultWalletSecondStepCustomSecretPhrase();
        secretPhrase = loginPage.copySecretPhrase();
        accountId = loginPage.copyAccountId();

        log.info("Step 6: Click on CheckBox");
        loginPage.clickCheckboxDataStored();

        log.info("Step 7: Click on Next Button");
        loginPage.clickNextBtn();

        log.info("Step 8: Input Secret Phrase");
        loginPage.enterSecretPhrase(secretPhrase);

        log.info("Step 9: Press Create New Account button ");
        loginPage.clickSubmitBtn();

        log.info("Step 10: Verify User Account Rs");
        assertEquals(accountId,dashboardPage.getUserAccountRs());

        log.info("Step 11: Do Log out");
        dashboardPage.clickAccountIconBtn().clickLogoutBtn();

        log.info("Step 12: Check Log In Page");
        verifyURL("login");
    }

    @Test
    @DisplayName("NEGATIVE: VAULT WALLET -> Error message is present when User enter invalid secret phrase")
    @Order(12)
    void testCreateVaultWalletInvalidSecretPhraseNegative(){
        LoginPage loginPage = getPage(LoginPage.class);;

        log.info("Step 2: Click on Create New User? button");
        loginPage.clickNewUserBtn();

        //log.info("Step 3: Verify Create New User page");
        //website.loginPage().verifyCreateStandardWallet();

        log.info("Step 4: Switch to Vault Wallet");
        loginPage.switchToNonActiveTab();

        //log.info("Step 5: Verify Create new vault wallet page");
        //website.loginPage().verifyCreateVaultWallet();

        log.info("Step 6: Press Create Account button");
        loginPage.clickCreateAccountBtn();

        //log.info("Step 7: Verify Create New Vault Wallet Page (Second Step)");
        //website.loginPage().verifyCreateVaultWalletSecondStep();
        //Thread.sleep(2000);

        log.info("Step 6: Click on CheckBox");
        loginPage.clickCheckboxDataStored();

        log.info("Step 7: Click on Next Button");
        loginPage.clickNextBtn();


        log.info("Step 8: Input Invalid Secret Phrase");
        loginPage.enterSecretPhrase("invalid secret phrase");


        log.info("Step 9: Press Create New Account button ");
        loginPage.clickSubmitBtn();


        log.info("Step 10: Verify Error message is shown");
        verifyNotifications(loginPage.getNotificationsMessages(),"Incorrect secret phrase!");


        log.info("Step 11: Close modal window");
        loginPage.closeModalWindow();

        log.info("Step 12: Verify Log In page");
        verifyURL("login");
    }

    @Test
    @DisplayName("Create Standard Wallet")
    @Order(13)
    void testCreateStandardWallet() {
        LoginPage loginPage = getPage(LoginPage.class);
        DashboardPage dashboardPage = getPage(DashboardPage.class);
        String secretPhrase;
        String accountId;

        log.info("Step 2: Click on Create New User? button");
        loginPage.clickNewUserBtn();

        //System.out.println("Step 3: Verify Create New User page");
        //website.loginPage().verifyCreateStandardWallet();

        log.info("Step 4: Press Create Account button");
        loginPage.clickCreateAccountBtn();

        log.info("Step 5: Verify Create new user page (Second Step)");
        secretPhrase = loginPage.copySecretPhrase();
        accountId = loginPage.copyAccountId();

        log.info("Step 6: Click on CheckBox");
        loginPage.clickCheckboxDataStored();

        log.info("Step 7: Click on Next Button");
        loginPage.clickNextBtn();

        log.info("Step 8: Input Secret Phrase");
        loginPage.enterSecretPhrase(secretPhrase);


        log.info("Step 9: Press Create New Account button ");
        loginPage.clickSubmitBtn();

        log.info("Step 10: Verify User Account Rs");
        assertEquals(accountId,dashboardPage.getUserAccountRs());


        log.info("Step 11: Do Log out");
        dashboardPage.clickAccountIconBtn().clickLogoutBtn();

        log.info("Step 12: Check Log In Page");
        verifyURL("login");
    }

    @Test
    @DisplayName("NEGATIVE: STANDARD WALLET -> Error message is present when User enter invalid secret phrase")
    @Order(14)
    void testCreateStandardWalletInvalidSecretPhrase(){
        LoginPage loginPage = getPage(LoginPage.class);

        log.info("Step 2: Click on Create New User? button");
        loginPage.clickNewUserBtn();

        //log.info("Step 3: Verify Create New User page");
        //website.loginPage().verifyCreateStandardWallet();

        log.info("Step 4: Press Create Account button");
        loginPage.clickCreateAccountBtn();

        //log.info("Step 5: Verify Create new user page (Second Step)");
        //website.loginPage().verifyCreateStandardWalletSecondStep();

        log.info("Step 6: Click on CheckBox");
        loginPage.clickCheckboxDataStored();

        log.info("Step 7: Click on Next Button");
        loginPage.clickNextBtn();

        log.info("Step 8: Input Secret Phrase");
        loginPage.enterSecretPhrase("Invalid Secret Phrase");

        log.info("Step 9: Press Create New Account button ");
        loginPage.clickSubmitBtn();

        log.info("Step 10: Error message is present");
        verifyNotifications(loginPage.getNotificationsMessages(),"Incorrect secret phrase!");


        log.info("Step 11: Close Modal Window");
        loginPage.closeModalWindow();

        log.info("Step 12: Verify Log In Page");
        verifyURL("login");
    }

    @Test
    @DisplayName("NEGATIVE: STANDARD WALLET -> Error message is present when User didn't store private data")
    @Order(15)
    void testCreateStandardWalletNegative() {
        LoginPage loginPage = getPage(LoginPage.class);

        log.info("Step 2: Click on Create New User? button");
        loginPage.clickNewUserBtn();

        //log.info("Step 3: Verify Create New User page");
        //loginPage.verifyCreateStandardWallet();

        log.info("Step 4: Press Create Account button");
        loginPage.clickCreateAccountBtn();

        //log.info("Step 5: Verify Create new user page (Second Step)");
        //loginPage.verifyCreateStandardWalletSecondStep();

        log.info("Step 6: Click on Next Button");
        loginPage.clickNextBtn();

        log.info("Step 7: Error message is present");
        verifyNotifications(loginPage.getNotificationsMessages(),"You have to verify that you stored your private data.");
    }



}
