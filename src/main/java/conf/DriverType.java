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
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
    },
    CHROME {
        public WebDriver getWebDriver() {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
    }
}
