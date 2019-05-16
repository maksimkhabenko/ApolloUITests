
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.LoginPage;


public class TestLogining extends TestBase {

    @Test
    public void test(){
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.logining("APL-MK35-9X23-YQ5E-8QBKH");

    }

}
