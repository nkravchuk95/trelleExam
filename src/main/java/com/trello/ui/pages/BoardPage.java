package com.trello.ui.pages;

import com.trello.ui.core.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.trello.ui.core.BrowserFactory.driver;

public class BoardPage extends BrowserFactory {

    private By addCardLink = By.cssSelector(".js-list:nth-child(2)>div>a");
    private By addCardTextarea = By.cssSelector("[class='list-card-composer-textarea js-card-title']");
    private By addCardBtn = By.cssSelector("[class='primary confirm mod-compact js-add-card']");

    public void openBoard(String cardUrl){

        get(cardUrl);
        new WebDriverWait(driver(), 10).until(ExpectedConditions.urlToBe(cardUrl));

    }

    public void createCard(String urlName, String name) {

        openBoard(urlName);
        driver().findElement(addCardLink).click();
        new WebDriverWait(driver(), 20).until(ExpectedConditions.elementToBeClickable(addCardBtn));
        driver().findElement(addCardTextarea).clear();
        driver().findElement(addCardTextarea).sendKeys(name);
        driver().findElement(addCardBtn).click();

    }
}
