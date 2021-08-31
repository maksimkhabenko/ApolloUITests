import conf.DriverFactory;
import conf.TestConfig;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobj.elements.models.Notification;
import pageobj.pages.models.LoginPage;
import utils.APIConnector;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.parallel.ResourceAccessMode.READ_WRITE;
import static org.junit.jupiter.api.parallel.Resources.SYSTEM_PROPERTIES;
import static org.openqa.selenium.support.PageFactory.*;


@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(TestListener.class)
public  class TestBase  {
    WebDriverWait wait;
    DriverFactory driverFactory = new DriverFactory();
    WebDriver webDriver;
    APIConnector apiConnector;

    static TestConfig testConfig;
    static Logger log = Logger.getLogger(TestBase.class.getName());
    private static String baseUrl;



    @BeforeAll
    @ResourceLock(value = SYSTEM_PROPERTIES, mode = READ_WRITE)
    static void beforeAll() {
        testConfig = TestConfig.getTestConfig();
        baseUrl = testConfig.getHost();
    }

    @BeforeEach
     void setUp() {
        this.apiConnector = new APIConnector(testConfig);
        /*
        this.webDriver = driverFactory.getDriver();
        this.wait = new WebDriverWait(webDriver,120);
        this.webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        this.webDriver.manage().window().maximize();
        this.webDriver.get(baseUrl);
         */

    }

    void verifyURL(String pageName){
        try {
            wait.until((ExpectedCondition<Boolean>) d -> webDriver.getCurrentUrl().contains(pageName));
        }catch (Exception e){
            log.log(Level.WARNING, e.getMessage());
        }
         finally {
            String msg = "Verify current url: " + pageName + " but current page: "+webDriver.getCurrentUrl();
            assertTrue( webDriver.getCurrentUrl().contains(pageName),msg);

        }
    }


     void verifyNotifications(List<Notification> notifications, String expectedMessage){

        try {
            wait.until((ExpectedCondition<Boolean>) d -> notifications.stream().anyMatch(msg -> (msg.getMessage().equals(expectedMessage))));
        }catch (Exception e){        }
        finally {
            if (notifications.size() > 0) {
                assertTrue(notifications.stream().anyMatch(msg -> (msg.getMessage().equals(expectedMessage))));
                notifications.forEach(Notification::click);
            }

        }
    }


    protected  <T> T getPage(Class<T> clazz){
        return initElements(webDriver, clazz);
    }

    @AfterEach
    void tearDown() {
         webDriver.quit();
    }

    @AfterAll
    static void afterAll() {

    }


    @Attachment(value = "{0}", type = "image/png")
    public byte[] screenshot(String name) {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

     void importSCFile(LoginPage loginPage, String accountID, String pass){

        log.info("Step : Click on Import Vault Wallet button");
        loginPage.clickImportVaultWalletBtn();

        log.info("Step : Switch to SECRET FILE IMPORT");
        loginPage.switchToNonActiveTab();

        log.info("Step: Input Invalid Secret Phrase");
        loginPage.enterSecretPhrase(pass);

        log.info("Step: Import correct file");
        loginPage.importFile(accountID);

        log.info("Step: Click on RESTORE ACCOUNT button");
        loginPage.clickSubmitBtn();
    }


     void loginToWallet(LoginPage loginPage, String accountId){

        log.info("Step : Log In by Account ID");
        loginPage.enterAccountID(accountId.substring(3));

        log.info("Step : Click on Submit Button");
        loginPage.clickSubmitBtn();
    }




}
