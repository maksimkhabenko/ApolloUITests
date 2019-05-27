import conf.DriverFactory;
import conf.TestConfig;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobj.elements.models.Notification;
import utils.APIConnector;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.parallel.ResourceAccessMode.READ_WRITE;
import static org.junit.jupiter.api.parallel.Resources.SYSTEM_PROPERTIES;
import static org.openqa.selenium.support.PageFactory.*;


@Execution(ExecutionMode.CONCURRENT)
public class TestBase {
    WebDriverWait wait;
    DriverFactory driverFactory = new DriverFactory();
    WebDriver webDriver;
    APIConnector apiConnector;

    static TestConfig testConfig;
    static Logger log = Logger.getLogger(TestBase.class.getName());
    private static String baseUrl;

    @BeforeAll
    @ResourceLock(value = SYSTEM_PROPERTIES, mode = READ_WRITE)
    static void beforAll() {
        testConfig = TestConfig.getTestConfig();
        baseUrl = testConfig.getHost();
    }

    @BeforeEach
     void setUp() {
        this.apiConnector = new APIConnector(testConfig);
        this.webDriver = driverFactory.getDriver();
        this.wait = new WebDriverWait(webDriver,15);
        this.webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        this.webDriver.manage().window().maximize();
        this.webDriver.get(baseUrl);
    }

    void verifyURL(String pageName){
        try {
            wait.until((ExpectedCondition<Boolean>) d -> webDriver.getCurrentUrl().contains(pageName));
        }catch (Exception e){
            log.log(Level.WARNING, e.getMessage());
        }
         finally {
            String msg = "Verify current url: " + pageName + " but current page: "+webDriver.getCurrentUrl();
            Assert.assertTrue( msg,webDriver.getCurrentUrl().contains(pageName));
        }
    }


     void verifyNotifications(List<Notification> notifications, String expectedMessage){

        try {
            wait.until((ExpectedCondition<Boolean>) d -> notifications.stream().anyMatch(msg -> (msg.getMeassage().equals(expectedMessage))));
        }catch (Exception e){        }
        finally {
            if (notifications.size() > 0) {
                Assert.assertTrue(notifications.stream().anyMatch(msg -> (msg.getMeassage().equals(expectedMessage))));
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
}
