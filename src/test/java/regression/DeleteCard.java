package regression;

import com.trello.api.TrelloRestClient;
import com.trello.api.models.Card;
import com.trello.ui.core.BrowserFactory;
import com.trello.ui.pages.CardPage;
import com.trello.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.with;
import static org.hamcrest.Matchers.equalTo;

public class DeleteCard extends BrowserFactory {

    public TrelloRestClient client = new TrelloRestClient();
    LoginPage loginPage = new LoginPage();
    CardPage cardPage = new CardPage();

    @Test
    public void deleteCard() throws IOException{
        loginPage.open();
        loginPage.login("bopo@planet-travel.club","MavenProject");

        Card card = client.cardsService.createCard("Test_Card_"+new Date().getTime(),"5d09c4cd65685688a7c43973").execute().body();
        int sizeCardsFirst = client.listsService.getCards("5d09c4cd65685688a7c43973").execute().body().size();
        cardPage.deleteCard(card.url);
        with().pollInterval(10, MILLISECONDS).then().await().atMost(30, SECONDS).until(() -> client.listsService.getCards("5d09c4cd65685688a7c43973").execute().body().size(), equalTo(sizeCardsFirst-1));
        Assert.assertEquals(client.listsService.getCards("5d09c4cd65685688a7c43973").execute().body().size(),sizeCardsFirst-1);

    }
}
