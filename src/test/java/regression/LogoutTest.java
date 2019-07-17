package regression;

import com.trello.ui.core.BrowserFactory;
import com.trello.ui.pages.BoardsPage;
import com.trello.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;



public class LogoutTest extends BrowserFactory {

    LoginPage loginPage = new LoginPage();
    BoardsPage boardsPage = new BoardsPage();

    @Test
    public void logout() throws Exception{
        loginPage.open();
        loginPage.login("bopo@planet-travel.club","MavenProject");
        boardsPage.logout();
        Assert.assertTrue(driver().getCurrentUrl().contentEquals("https://trello.com/logged-out"), "Board page not logout");

    }
}
