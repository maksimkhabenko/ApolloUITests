package pageobj.pages.models;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobj.elements.models.Button;
import pageobj.elements.models.Notification;
import pageobj.elements.models.Text;
import pageobj.elements.models.TextField;
import pageobj.pages.BasePage;
import pageobj.pages.models.modals.UserProfileModal;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.refreshed;

public class DashboardPage extends BasePage {


    @FindBy(css = "div[class=\"message\"]")
    private Notification[] notification;

    @FindBy(className = "user-account-rs")
    private Button userAccountRsBtn;

    @FindBy(css = "div[class=\"underscore btn stop icon-button filters FORGING_BODY_MODAL primary transparent\"]")
    private Button generalSettings;

    @FindBy(css = "div[class=\"input-section p-0\"] a label")
    private Button forging;

    @FindBy(css = "div.page-body-item-content div.amount")
    private Text amount;

    @FindBy (css = "tr:nth-child(6) td:last-child")
    private Text effectiveBalance;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getAmount() {
        return amount.readText();
    }

    public String getEffectiveBalance() {
        return effectiveBalance.readText();
    }

    public void openAccountDetails () {
        amount.click();
    }

    public void clickGeneralSettings () {
        try {
            generalSettings.click();
        }
        catch (Exception e){
            executor.executeScript("arguments[0].click();", generalSettings.getWebElement());
        }
    }

    public void clickForging() {
        try {
            forging.click();
        }
        catch (Exception a) {
            executor.executeScript("arguments[0].click();", forging.getWebElement());
        }

    }

    public String getUserAccountRs() {
        return  userAccountRsBtn.readText();
    }

    public String getForgingStatus() {
        return forging.readText();
    }


}
