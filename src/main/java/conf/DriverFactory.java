package conf;

import org.openqa.selenium.WebDriver;

import static conf.DriverType.CHROME;

public class DriverFactory {
    DriverType driverType = CHROME;

    public DriverFactory() {
        String browser = System.getProperty("browser", driverType.toString()).toUpperCase();
        System.out.println("------------------------"+browser);
        try {
            if (browser != null && !browser.equals("")) {
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
