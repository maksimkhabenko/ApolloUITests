package pageobj.pages.models;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    @FindBy(css = "div[class='user-box']")
    private Button accountIconBtn;

    @FindBy(css = "div[class=\"message\"]")
    private Notification[] notification;

    @FindBy(className = "user-account-rs")
    private Button userAccountRsBtn;

    private UserProfileModal userProfileModal;


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public UserProfileModal clickAccountIconBtn() {
        accountIconBtn.click();
        if (userProfileModal == null){
            userProfileModal = new UserProfileModal(driver);
        }
        return userProfileModal;
    }


    public String getUserAccountRs() {
        return  userAccountRsBtn.readText();
    }


}
