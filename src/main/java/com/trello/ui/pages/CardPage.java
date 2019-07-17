package com.trello.ui.pages;

import com.trello.ui.core.BrowserFactory;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CardPage extends BrowserFactory {



    private By membersBtnLink = By.cssSelector("a[class='button-link js-change-card-members']");
        private By memberItemActive = By.cssSelector("[class='item js-member-item selected active']");
        private By memberItemNoActive = By.cssSelector("[class='item js-member-item selected']");
        private By memberLink = By.cssSelector("a[class='name js-select-member']");

    private By labelsBtnLink = By.cssSelector("a[class='button-link js-edit-labels']");
        private By labelsClose = By.cssSelector("a[class='pop-over-header-close-btn icon-sm icon-close']");

    private By checklistBtnLink = By.cssSelector("a[class='button-link js-add-checklist-menu']");
        private By checklistNameField = By.cssSelector("[class='js-checklist-title js-autofocus']");
        private By checklistAddBtn = By.cssSelector("[class='primary wide confirm js-add-checklist']");

    private By checklists = By.cssSelector("div[class='checklist']");
        private By checklistItemAddLink = By.cssSelector("button[class='js-new-checklist-item-button button subtle hide-on-edit']");
        private By checklistItemTextArea = By.cssSelector("textarea[class='edit field checklist-new-item-text js-new-checklist-item-input']");
        private By checklistItemAddBtn = By.cssSelector("input[class='primary confirm mod-submit-edit js-add-checklist-item']");


    private By dateBtnLink = By.cssSelector("a[class='button-link js-add-due-date']");
        private By dateSubmitBtn = By.cssSelector("[class = 'primary wide confirm']");


    private By attachBtnLink = By.cssSelector("a[class='button-link js-attach']");
        private By attachmentLinks = By.cssSelector("a[class='phenom mod-attachment-type']");

    private By descTextLink = By.cssSelector("a[class='description-fake-text-area hide-on-edit js-edit-desc js-hide-with-draft']");
        private By descTextArea = By.cssSelector("textarea[class='field field-autosave js-description-draft description card-description']");
        private By descSubmitBtn = By.cssSelector("[class = 'primary confirm mod-submit-edit js-save-edit']");
        private By descEditable = By.cssSelector("div[class='u-gutter']>[class='editable']']");

    private By commTextArea = By.cssSelector("textarea[class='comment-box-input js-new-comment-input']");
        private By commSubmitBtn = By.cssSelector("[class = 'primary confirm mod-no-top-bottom-margin js-add-comment']");

    private By moveCardLink = By.cssSelector("a[class='button-link js-move-card']");
    private By copyCardLink = By.cssSelector("a[class='button-link js-copy-card']");
    private By listsSelect = By.cssSelector("[class = 'js-select-list']");
    private By cardsSelect = By.cssSelector("[class = 'js-select-board']");
    private By moveSubmitBtn = By.cssSelector("[class = 'primary wide js-submit']");

    private By delCardLink = By.cssSelector("a[class='button-link js-archive-card']");
        private By delCardBtn = By.cssSelector("[class = 'button-link js-delete-card negate']");
        private By delCardConfirm = By.cssSelector("[class = 'js-confirm full negate']");

    private By closeCardLink = By.cssSelector("a[class='icon-lg icon-close dialog-close-button js-close-window']");

    public void open(String cardUrl){

        get(cardUrl);

    }

    public void move(String cardUrl, String listName){

        get(cardUrl);
        driver().findElement(moveCardLink).click();
        Select lists = new Select(driver().findElement(listsSelect));
        lists.selectByVisibleText(listName);
        driver().findElement(moveSubmitBtn).click();

    }

    public void copy(String cardUrl, String boardName, String listName){

        get(cardUrl);
        driver().findElement(copyCardLink).click();
        new WebDriverWait(driver(), 20).until(ExpectedConditions.elementToBeClickable(moveSubmitBtn));
        Select cards = new Select(driver().findElement(cardsSelect));
        cards.selectByVisibleText(boardName);

        Select lists = new Select(driver().findElement(listsSelect));
        lists.selectByVisibleText(listName);

        driver().findElement(moveSubmitBtn).click();

    }


    public void addMember(String cardUrl, String memberId){

        get(cardUrl);
        driver().findElement(membersBtnLink).click();

        driver().findElement(By.cssSelector("a[idmember='"+ memberId +"']")).click();

    }

    public void addLabel(String cardUrl, String idLabel){

        get(cardUrl);
        driver().findElement(labelsBtnLink).click();

        driver().findElement(By.cssSelector("span[data-idlabel='"+ idLabel +"']")).click();
        driver().findElement(labelsClose).click();

    }

    public void addChecklist(String cardUrl, String checklistName, String checklistItemName){

        get(cardUrl);
        int checklistsSize = driver().findElements(checklists).size();
        driver().findElement(checklistBtnLink).click();
        new WebDriverWait(driver(), 20).until(ExpectedConditions.elementToBeClickable(checklistAddBtn));
        driver().findElement(checklistNameField).clear();
        driver().findElement(checklistNameField).sendKeys(checklistName);
        driver().findElement(checklistAddBtn).click();
        new WebDriverWait(driver(), 20).until(ExpectedConditions.elementToBeClickable(driver().findElements(checklistItemAddBtn).get(checklistsSize)));
        driver().findElements(checklistItemTextArea).get(checklistsSize).clear();
        driver().findElements(checklistItemTextArea).get(checklistsSize).sendKeys(checklistItemName);
        driver().findElements(checklistItemAddBtn).get(checklistsSize).click();
    }


    public void addDateTime(String cardUrl, String date){

        get(cardUrl);
        driver().findElement(dateBtnLink).click();

        driver().findElement(By.cssSelector("button[data-pika-day='"+ date +"']")).click();

        driver().findElement(dateSubmitBtn).click();
    }

    public void addAttachment(String cardUrl, String urlAttachment){

    }

    public void addDescr(String cardUrl, String descr){
        get(cardUrl);
        driver().findElement(descTextLink).click();
        driver().findElement(descTextArea).clear();
        driver().findElement(descTextArea).sendKeys(descr);
        driver().findElement(descSubmitBtn).click();

    }

    public void addComment(String cardUrl, String comm){

        get(cardUrl);
        driver().findElement(commTextArea).clear();
        driver().findElement(commTextArea).sendKeys(comm);
        driver().findElement(commSubmitBtn).click();
    }


    public void deleteCard(String cardUrl) {

        get(cardUrl);
        driver().findElement(delCardLink).click();
        new WebDriverWait(driver(), 20).until(ExpectedConditions.elementToBeClickable(delCardBtn));
        driver().findElement(delCardBtn).click();
        driver().findElement(delCardConfirm).click();
    }
}
