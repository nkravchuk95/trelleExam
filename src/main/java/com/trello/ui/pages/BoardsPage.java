package com.trello.ui.pages;

import com.trello.ui.core.BrowserFactory;
import com.trello.ui.core.Constans;
import com.trello.ui.core.Elem;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.trello.ui.core.BrowserFactory.driver;
import static com.trello.ui.core.BrowserFactory.get;

public class BoardsPage extends BrowserFactory {

    private static final String PATH = "/mavenproject3/boards";

    public Elem boardByUrlName(String urlName){
        return new Elem(By.cssSelector(".board-tile[href*='"+urlName+"']"), urlName);
    }


    public void openBoard(String urlName){
        boardByUrlName(urlName).click();
    }

    private By memberMenu = By.cssSelector("[data-test-id= 'header-member-menu-button']");
    private By logoutBth = By.cssSelector("[data-test-id= 'header-member-menu-logout']");

    public void logout(){
        driver().findElement(memberMenu).click();
        driver().findElement(logoutBth).click();
        new WebDriverWait(driver(), 10).until(ExpectedConditions.urlToBe("https://trello.com/logged-out"));
    }



}
