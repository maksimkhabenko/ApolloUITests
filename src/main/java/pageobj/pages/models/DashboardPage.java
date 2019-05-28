package pageobj.pages.models;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import pageobj.elements.models.Button;
import pageobj.elements.models.Notification;
import pageobj.elements.models.Text;
import pageobj.pages.BasePage;

import static org.junit.Assert.assertTrue;

public class DashboardPage extends BasePage {


    @FindBy(css = "div[class=\"message\"]")
    private Notification[] notification;

    @FindBy(className = "user-account-rs")
    private Button userAccountRsBtn;

    @FindBy(css = "div[class=\"underscore btn stop icon-button filters FORGING_BODY_MODAL primary transparent\"]")
    private Button generalForgingSettings;

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
            generalForgingSettings.click();
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
