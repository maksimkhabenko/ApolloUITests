package pageobj.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobj.elements.models.Button;
import pageobj.pages.IWebPage;
import pageobj.pages.models.modals.SettingsModal;
import pageobj.pages.utils.CustomFieldDecorator;

public abstract class BasePage implements IWebPage {
    protected WebDriver driver;
    protected JavascriptExecutor executor;

    @FindBy(css = "i[class ='zmdi zmdi stop zmdi-settings']")
    private Button settingsBtn;
    private SettingsModal settingsModal;

     public BasePage(WebDriver driver) {
        this.driver = driver;
        this.executor = (JavascriptExecutor)driver;
        PageFactory.initElements(new CustomFieldDecorator(driver),  this);
    }

    public SettingsModal clickSettingsBtn(){
          settingsBtn.click();
          if (settingsModal == null){
              settingsModal = new SettingsModal(driver);
          }
         return settingsModal;
    }


}
