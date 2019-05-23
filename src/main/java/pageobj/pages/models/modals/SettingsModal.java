package pageobj.pages.models.modals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import pageobj.elements.models.Button;
import pageobj.elements.models.TextField;
import pageobj.pages.BasePage;

public class SettingsModal extends BasePage {
    @FindBy(xpath = "//*[contains(text(), 'Export Secret File')]")
    private Button exportFileBtn;
    @FindBy(css = " .col-sm-9 input[placeholder='Account ID']")
    private TextField accoutnIDField;
    @FindBy(css = " .col-sm-9 input[placeholder='Secret Phrase']")
    private TextField passField;
    @FindBy(css = "button[class = 'btn absolute btn-right blue round round-top-left round-bottom-right']")
    private Button exportButton;



    public SettingsModal(WebDriver driver) {
        super(driver);
    }


    public SettingsModal clickExportFileBtn(){
        exportFileBtn.click();
        return this;
    }

    public SettingsModal enterAccountID(String accoutnID){
        accoutnIDField.clear();
        accoutnIDField.writeText(accoutnID);
        return this;
    }

    public SettingsModal enterPass(String pass){
        passField.clear();
        passField.writeText(pass);
        return this;
    }

    public SettingsModal clickExportBtn(){
        exportButton.click();
        return this;
    }
}
