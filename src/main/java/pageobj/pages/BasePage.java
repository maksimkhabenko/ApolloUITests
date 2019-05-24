package pageobj.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobj.elements.models.Button;
import pageobj.pages.IWebPage;
import pageobj.pages.models.modals.UserProfileModal;
import pageobj.pages.utils.CustomFieldDecorator;

public abstract class BasePage implements IWebPage {
    protected WebDriver driver;
    protected JavascriptExecutor executor;
    @FindBy(css = "div[class='user-box']")
    private Button accountIconBtn;
    private UserProfileModal userProfileModal;


    public UserProfileModal clickAccountIconBtn() {
            accountIconBtn.click();
            if (userProfileModal == null) {
                userProfileModal = new UserProfileModal(driver);
            }
            return userProfileModal;

    }

     public BasePage(WebDriver driver) {
        this.driver = driver;
        this.executor = (JavascriptExecutor)driver;
        PageFactory.initElements(new CustomFieldDecorator(driver),  this);
    }




}
