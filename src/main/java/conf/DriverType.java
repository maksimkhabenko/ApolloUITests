package conf;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
/*
        -Dbrowser=firefox
        -Dbrowser=chrome
        -Dbrowser=ie
        -Dbrowser=edge
        -Dbrowser=opera
*/
public enum DriverType implements DriverSetup {

    FIREFOX {
        public WebDriver getWebDriver() {
            FirefoxOptions options = new FirefoxOptions();

            options.addPreference("browser.download.folderList", 2);
            options.addPreference("browser.download.dir", System.getProperty("user.home")+"/Downloads/");
            options.addPreference("browser.download.useDownloadDir", true);
            options.addPreference("browser.helperApps.neverAsk.saveToDisk","application/octet-stream");
            options.addPreference("pdfjs.disabled", true);
            WebDriverManager.firefoxdriver().setup();

            return new FirefoxDriver(options);
        }
    },
    CHROME {
        public WebDriver getWebDriver() {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
    }
}
