import org.junit.jupiter.api.*;
import pageobj.elements.models.Notification;
import pageobj.pages.models.DashboardPage;
import pageobj.pages.models.LoginPage;


import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLoginAllure extends TestBase {

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
    @DisplayName("Log In by Account ID")
    @Order(2)
    void testLogInByAccountID() {
        LoginPage loginPage = getPage(LoginPage.class);
        DashboardPage dashboardPage = getPage(DashboardPage.class);

        String accountRs = testConfig.getStandartWallet().getUser();
        log.info("Step 2: Log In by Account ID: "+accountRs);
        loginPage.enterAccountID(accountRs.substring(3));

        log.info("Step 3: Click on Submit Button");
        loginPage.clickSubmitBtn();

        log.info("Step 4: Verify User Account Rs");
        assertTrue(dashboardPage.getUserAccountRs().equals(testConfig.getStandartWallet().getUser()),"Verify User Account Rs");

        log.info("Step 5: Log out");
        dashboardPage.clickAccountIconBtn().clickLogoutBtn();

        log.info("Step 6: Check Log In Page");
        verifyURL("login");

    }




    private boolean isPresent(String fileName, boolean delete){
        boolean isPresent = false;

        File folder = new File(System.getProperty("user.home")+"/Downloads/");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
                if (file.isFile() && file.getName().contains(fileName)) {
                  if (delete) file.delete();
                    isPresent = true;
                }
        }
        return isPresent;
    }

}
