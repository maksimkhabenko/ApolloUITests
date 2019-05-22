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

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.junit.jupiter.api.parallel.ResourceAccessMode.READ_WRITE;
import static org.junit.jupiter.api.parallel.Resources.SYSTEM_PROPERTIES;
import static org.openqa.selenium.support.PageFactory.*;


@Execution(ExecutionMode.CONCURRENT)
public class TestBase {
    protected WebDriverWait wait;
    protected DriverFactory driverFactory = new DriverFactory();
    protected WebDriver webDriver;

    protected static TestConfig testConfig;
    protected static Logger log = Logger.getLogger(TestBase.class.getName());

    private static String baseUrl;

    @BeforeAll
    @ResourceLock(value = SYSTEM_PROPERTIES, mode = READ_WRITE)
    static void beforAll() {
        testConfig = TestConfig.getTestConfig();
        baseUrl = testConfig.getHost();
    }

    @BeforeEach
     void setUp() {
        this.webDriver = driverFactory.getDriver();
        this.wait = new WebDriverWait(webDriver,15);
        this.webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        this.webDriver.manage().window().maximize();
        this.webDriver.get(baseUrl);
    }

    protected void verifyURL(String pageName){
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return webDriver.getCurrentUrl().contains(pageName);
                }
            });
        }catch (Exception e){}
         finally {
            String msg = "Verify current url: " + pageName + " but current page: "+webDriver.getCurrentUrl();
            Assert.assertTrue( msg,webDriver.getCurrentUrl().contains(pageName));
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
