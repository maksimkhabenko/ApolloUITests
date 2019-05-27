package pageobj.pages.models;

import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobj.elements.models.*;
import pageobj.pages.BasePage;

import java.io.File;
import java.net.URL;
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


    @FindBy (css = "p.mb-3:nth-child(2) span")
    private Text secreatPhrase;
    @FindBy (css = "p.mb-3:nth-child(3) span")
    private Text accouyntID;
    @FindBy (css = "p.mb-3:nth-child(4) span")
    private Text publicKey;



    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void switchToNonActiveTab() {
        nonActiveBtn.click();
    }

    public void enterSecretPhrase (String secretphrase) {
        password.clear();
        password.writeText(secretphrase);
    }

    public void enterCustomSecretPhrase(String secretphrase) {
        customSecretPhrase.clear();
        customSecretPhrase.writeText(secretphrase);
    }

    public void clickSubmitBtn() {
        submit.click();
    }

    public void enterAccountID(String accountID) {
        accountIdField.writeText(accountID);
    }

    public void clickNewUserBtn() {
        newUserBtn.click();
    }

    public void closeModalWindow() {
            closeModalWindowBtn.click();
    }

    public void clickCreateAccountBtn() {
        createAccountBtn.click();
    }

    public String copySecretPhrase() {
       return secreatPhrase.readText().trim();
    }

    public String copyAccountId() {
        return accouyntID.readText();
    }

    public void clickNextBtn() {
        nextBtn.click();
    }

    public void clickCheckboxDataStored() {
        checkboxDataStored.setChecked(true);
    }

    public void clickCheckBoxCustomSecretPhrase() {
        checkboxCustomSecretPhrase.setChecked(true);
    }

    public void clickImportVaultWalletBtn() {
        importVaultWalletBtn.click();
    }

    public void importFile(String acoountID) {
      URL url =   Test.class.getClassLoader().getResource(acoountID);
        String path = url.getPath();
        if (System.getProperty("os.name").contains("Win")){
         path =  path.substring(1);
        }
      uploadFileBtn.getWebElement().sendKeys(path);
    }
}
