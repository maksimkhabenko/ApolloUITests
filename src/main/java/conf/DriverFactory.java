package conf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static conf.DriverType.CHROME;
import static conf.DriverType.FIREFOX;

public class DriverFactory {
    private DriverType driverType = CHROME;

    public DriverFactory() {
        String browser = System.getProperty("browser", driverType.toString()).toUpperCase();
        try {
            if (!browser.equals("")) {
                driverType = DriverType.valueOf(browser);
            }
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
    }

    public WebDriver getDriver(){
        return driverType.getWebDriver();
    }
}
