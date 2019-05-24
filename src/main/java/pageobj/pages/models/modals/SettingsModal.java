package pageobj.pages.models.modals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobj.elements.models.Button;
import pageobj.elements.models.TextField;
import pageobj.pages.BasePage;

import java.util.Set;

public class SettingsModal extends BasePage {
    @FindBy(xpath = "//*[contains(text(), 'Export Secret File')]")
    private Button exportFileBtn;
    @FindBy(css = " .col-sm-9 input[placeholder='Account ID']")
    private TextField accoutnIDField;
    @FindBy(css = " .col-sm-9 input[placeholder='Secret Phrase']")
    private TextField passField;
    @FindBy(css = "button[class = 'btn absolute btn-right blue round round-top-left round-bottom-right']")
    private Button exportButton;

    @FindBy(css = "a[class = 'btn danger static']")
    private Button yesDeleteFileBtn;
    @FindBy(css = "a[class = 'btn success static']")
    private Button noDeleteFileBtn;



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

    public SettingsModal deleteFile(boolean yes){
        if (yes){
            yesDeleteFileBtn.click();
        }else {
            noDeleteFileBtn.click();
        }
        return this;
    }
}
