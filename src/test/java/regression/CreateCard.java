package regression;

import com.trello.api.TrelloRestClient;
import com.trello.ui.core.BrowserFactory;
import com.trello.ui.pages.BoardPage;
import com.trello.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.with;
import static org.hamcrest.Matchers.equalTo;

public class CreateCard extends BrowserFactory {

    public TrelloRestClient client = new TrelloRestClient();

    LoginPage loginPage = new LoginPage();
    BoardPage boardPage = new BoardPage();

    @Test
    public void createCard() throws Exception{
        loginPage.open();
        loginPage.login("bopo@planet-travel.club","MavenProject");
        int sizeCardsFirst = client.listsService.getCards("5d09c4cd65685688a7c43973").execute().body().size();
        boardPage.createCard("https://trello.com/b/e86Q7ocv/l","MavenProject");
        with().pollInterval(10, MILLISECONDS).then().await().atMost(30, SECONDS).until(() -> client.listsService.getCards("5d09c4cd65685688a7c43973").execute().body().size(), equalTo(sizeCardsFirst+1));
        Assert.assertEquals(client.listsService.getCards("5d09c4cd65685688a7c43973").execute().body().size(),sizeCardsFirst+1);

    }
}
