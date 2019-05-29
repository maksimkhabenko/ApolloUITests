package pageobj.pages.models.modals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobj.elements.models.Button;
import pageobj.elements.models.TextField;
import pageobj.pages.BasePage;

import java.util.Set;

public class SendMoneyModal extends BasePage {

    @FindBy(id = "send-money-modal-recipient-field")
    private TextField recipientAccountId;

    @FindBy (id = "send-money-modal-amountATM-field")
    private TextField amountATM;

    @FindBy (id = "send-money-modal-feeATM-field")
    private TextField feeATM;

    @FindBy (id = "send-money-modal-secretPhrase-field")
    private TextField secretPhraseField;

    @FindBy (id = "send-money-modal-submit-button")
    private TextField submitSendMoney;


    public SendMoneyModal(WebDriver driver) {
        super(driver);
    }


    public SendMoneyModal enterRecipientAccountId(String accountID){
        recipientAccountId.writeText(accountID);
        return this;
    }

    public SendMoneyModal enterAmountATM (String amountAtm) {
        amountATM.writeText(amountAtm);
        return this;
    }

    public SendMoneyModal enterFeeAtm (String feeAtm ) {
        feeATM.writeText(feeAtm);
        return this;
    }

    public SendMoneyModal enterSecretPhrase (String secretPhrase ) {
        secretPhraseField.writeText(secretPhrase);
        return this;
    }

    public SendMoneyModal clickSendMoneyBtn () {
        submitSendMoney.click();
        return this;
    }

    public SendMoneyModal fillSendMoneyModal (String recipientAccountId, String amountAtm, String feeAtm, String secretPhrase) {
        enterRecipientAccountId(recipientAccountId);
        enterAmountATM(amountAtm);
        enterFeeAtm(feeAtm);
        enterSecretPhrase(secretPhrase);
        clickSendMoneyBtn();
        return this;
    }


    public String getAccountIdFromSendMoneyModal () {
        return recipientAccountId.getWebElement().getAttribute("value");
    }

    public String getAmountAtmFromSendMoneyModal () {
        return amountATM.getWebElement().getAttribute("value");
    }

    public String getFeeAtmFromSendMoneyModal () {
        return feeATM.getWebElement().getAttribute("value");
    }







}
