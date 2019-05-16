package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.Button;
import pages.elements.CheckBox;
import pages.elements.TextField;
import pages.helpers.BasePage;

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
}
