package pageobj.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobj.elements.models.Button;
import pageobj.pages.IWebPage;
import pageobj.pages.models.modals.SendMoneyModal;
import pageobj.pages.models.modals.UserProfileModal;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobj.elements.models.Button;
import pageobj.elements.models.Notification;
import pageobj.pages.IWebPage;
import pageobj.pages.models.modals.SettingsModal;
import pageobj.pages.utils.CustomFieldDecorator;

import java.util.List;

public abstract class BasePage implements IWebPage {
    protected WebDriver driver;
    protected JavascriptExecutor executor;
    @FindBy(css = "div[class='user-box']")
    private Button accountIconBtn;
    private UserProfileModal userProfileModal;
    protected SendMoneyModal sendMoneyModal;
    @FindBy (css = "i[class=\"zmdi zmdi-balance-wallet\"]")
    private Button sendMoneyBtnHeader;

    public SendMoneyModal clickSendMoneyFromHeader() {
        sendMoneyBtnHeader.click();
        if (sendMoneyModal == null) {
            sendMoneyModal = new SendMoneyModal(driver);
        }
        return sendMoneyModal;
    }


    public UserProfileModal clickAccountIconBtn() {
            accountIconBtn.click();
            if (userProfileModal == null) {
                userProfileModal = new UserProfileModal(driver);
            }
            return userProfileModal;

    }
    protected WebDriverWait wait;

    @FindBy(css = "i[class ='zmdi zmdi stop zmdi-settings']")
    private Button settingsBtn;

    @FindBy(className = "notification-message")
    private List<Notification> notificationsMessages;
	
    private SettingsModal settingsModal;

     public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,15);
        this.executor = (JavascriptExecutor)driver;
        PageFactory.initElements(new CustomFieldDecorator(driver),  this);
    }

    public List<Notification> getNotificationsMessages() {
        return notificationsMessages;
    }

    public SettingsModal clickSettingsBtn(){
         try {
             settingsBtn.click();
         }catch (Exception e){
             executor.executeScript("arguments[0].click();", settingsBtn.getWebElement());
         }
          if (settingsModal == null){
              settingsModal = new SettingsModal(driver);
          }
         return settingsModal;
    }



}
