package pageobj.pages.models;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobj.elements.models.Button;
import pageobj.elements.models.Notification;
import pageobj.elements.models.Text;
import pageobj.elements.models.TextField;
import pageobj.pages.BasePage;
import pageobj.pages.models.modals.SendMoneyModal;
import pageobj.pages.models.modals.UserProfileModal;

import java.util.List;

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

    @FindBy (xpath = "//button[contains(text(),'Send')]")
    private Button sendMoneyBtn;

    @FindBy (css = "a[class = 'transaction-item']")
    private List<Text> myTransactionsInfo;

    @FindBy (xpath = "//input[@placeholder='Account ID']")
    private TextField sendMoneyRecipientAccountId;

    @FindBy (xpath = "//input[@placeholder='Amount']")
    private TextField sendMoneyAmountAtm;

    @FindBy (xpath = "//input[@placeholder='Fee']")
    private TextField sendMoneyFee;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void enterSendMoneyData (String recipientAccountId, String amountAtm, String feeAtm) {
        sendMoneyRecipientAccountId.writeText(recipientAccountId);
        sendMoneyAmountAtm.writeText(amountAtm);
        sendMoneyFee.writeText(feeAtm);
    }



    public String getLastTransactionAmount(int index){
        return myTransactionsInfo.get(index).getWebElement().findElement(By.className("transaction-amount")).getText();
    }

    public SendMoneyModal clickSendMoneyFromDashboard() {
        sendMoneyBtn.click();
        if (sendMoneyModal == null) {
            sendMoneyModal = new SendMoneyModal(driver);
        }
        return sendMoneyModal;
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
            forging.click();
    }

    public String getUserAccountRs() {
        return  userAccountRsBtn.readText();
    }

    public String getForgingStatus() {
        return forging.readText();
    }


}
