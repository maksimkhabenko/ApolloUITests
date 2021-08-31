import io.qameta.allure.Attachment;
import io.qameta.allure.listener.LifecycleListener;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

public class TestListener implements TestWatcher, BeforeEachCallback, AfterEachCallback {
    public WebDriver webDriver;


    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("WORK2");
        screenshot(context.getDisplayName());
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {

    }

    @Override
    public void testSuccessful(ExtensionContext context) {

    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {

    }



    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {

    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {

    }

    @Attachment(value = "{0}", type = "image/png")
    public byte[] screenshot(String name) {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }
}
