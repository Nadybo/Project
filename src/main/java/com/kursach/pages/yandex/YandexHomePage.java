package com.kursach.pages.yandex;

import com.kursach.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YandexHomePage extends BasePage {

    @FindBy(css = "div[data-zone-name='catalog'] button")
    private WebElement catalogButton;

    @FindBy(xpath = "//li//a//span[.='Ноутбуки и компьютеры']")
    private WebElement linkNounbooks;

    @FindBy(xpath = "//a[contains(text(),'Планшеты')]")
    private WebElement tabletsButton;


    public YandexHomePage openCatalog() {
        waitUtilElementToBeClickable(catalogButton).click();
        return pageManager.getYandexPage();
    }
    public YandexHomePage slectCategory(){
        waitUtilElementToBeVisible(linkNounbooks);
        actions.moveToElement(linkNounbooks).perform();
        return pageManager.getYandexPage();
    }
    public catalogPlanshety TablesPage(){
        waitUtilElementToBeVisible(tabletsButton).click();
        pageManager.getCataloPlanshety();
        return pageManager.getCataloPlanshety();
    }
}
