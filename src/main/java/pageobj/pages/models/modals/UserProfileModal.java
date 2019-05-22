package pageobj.pages.models.modals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobj.elements.models.Button;
import pageobj.pages.BasePage;

public class UserProfileModal extends BasePage {
    @FindBy(xpath = "//label[text()=\"Logout\"]")
    private Button logOutBtn;
    @FindBy(css = "a[class='user-account-rs blue-text']")
    private Button userAccountRsInfo;

    public UserProfileModal(WebDriver driver) {
        super(driver);
    }

    public void clickLogoutBtn()  {
        logOutBtn.click();
    }

}
