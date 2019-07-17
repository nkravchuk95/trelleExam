package regression;

import com.google.gson.Gson;
import com.trello.api.TrelloRestClient;
import com.trello.api.models.Card;
import com.trello.api.models.Checklist;
import com.trello.api.models.Label;
import com.trello.api.models.TrelloList;
import com.trello.ui.core.BrowserFactory;
import com.trello.ui.pages.BoardsPage;
import com.trello.ui.pages.CardPage;
import com.trello.ui.pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.*;
import static org.hamcrest.Matchers.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Awaitility.with;

public class CardAction extends BrowserFactory {



    public TrelloRestClient client = new TrelloRestClient();

    public LoginPage loginPage = new LoginPage();
    public CardPage cardPage = new CardPage();

    Card card = new Card("Test_Card_"+new Date().getTime());

    @BeforeTest
    public void prepareData() throws IOException {
        card = client.cardsService.createCardNew("5d09c4cd65685688a7c43973", card).execute().body();
    }

    @AfterTest
    public void clearData() throws IOException {
       client.cardsService.deleteCard(card.id).execute();
    }

    @Test()
    public void moveCard()throws IOException {
        loginPage.open();
        loginPage.login("bopo@planet-travel.club","MavenProject");

        TrelloList trelloList = client.listsService.getList("5d0bbaa6b24718181696873e").execute().body();;

        cardPage.move(card.url,trelloList.name);

        with().pollInterval(10, MILLISECONDS).then().await().atMost(30, SECONDS).until(() -> client.cardsService.getCard(card.id).execute().body().idList, equalTo("5d0bbaa6b24718181696873e"));
        Card cardUp = client.cardsService.getCard(card.id).execute().body();
        Assert.assertEquals(cardUp.idList,trelloList.id);

    }



}
