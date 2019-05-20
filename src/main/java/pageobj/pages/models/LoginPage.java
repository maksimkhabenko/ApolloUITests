package pageobj.pages.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import pageobj.elements.models.Button;
import pageobj.elements.models.CheckBox;
import pageobj.elements.models.Notification;
import pageobj.elements.models.TextField;
import pageobj.pages.BasePage;

import java.util.List;

public class LoginPage extends BasePage {

    @FindBy(className = "btn")
    private Button submit;

    @FindBy(className = "form-control")
    private TextField accountIdField;

    @FindBy(css = "input[type=\"password\"]")
    private TextField password;

    @FindBy(css = "a[class='form-tab']")
    private Button nonActiveBtn;

    @FindBy (css = "[class=\"button-block\"]:nth-child(3)")
    private Button newUserBtn;

    @FindBy (css = "form[class='tab-body active'] button")
    private Button createAccountBtn;

    @FindBy (css = "input[value='false']")
    private CheckBox checkboxDataStored;

    @FindBy (css = "button[type='submit']")
    private Button nextBtn;

    @FindBy (css = ".exit")
    private Button closeModalWindowBtn;

    @FindBy (css = "[type=\'checkbox\']")
    private CheckBox checkboxCustomSecretPhrase;

    @FindBy (css = "textarea")
    private TextField customSecretPhrase;

    @FindBy (css = "[class=\"button-block\"]:nth-child(2)")
    private Button importVaultWalletBtn;

    @FindBy (className = "upload-block")
    private Button importFileBtn;

    @FindBy (css = "input[type='file']")
    private Button uploadFileBtn;


    @FindBy(className = "notification-message")
    private List<Notification> notificationsMessages;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void logining(String login){
         accountIdField.writeText(login);
         submit.click();
    }

    public void switchToNonActiveTab() {
        nonActiveBtn.click();
    }

    public void enterSecretPhrase (String secretphrase) {
        password.clear();
        password.writeText(secretphrase);
    }

    public void clickSubmitBtn() {
        submit.click();
    }

    public List<Notification> getNotificationsMessages() {
        return notificationsMessages;
    }
}
