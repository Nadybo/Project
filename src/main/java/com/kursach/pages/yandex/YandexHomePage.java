package com.kursach.pages.yandex;

import com.kursach.pages.BasePage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YandexHomePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(YandexHomePage.class);

    @FindBy(css = "div[data-zone-name='catalog'] button")
    private WebElement catalogButton;

    @FindBy(xpath = "//li//a//span[.='Ноутбуки и компьютеры']")
    private WebElement linkNounbooks;

    @FindBy(xpath = "//a[contains(text(),'Планшеты')]")
    private WebElement tabletsButton;


    @Step("Шаг для открытия каталога на главной странице Yandex")
    public YandexHomePage openCatalog() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(catalogButton.isDisplayed(), "Кнопка каталога не отображается на странице");

        waitUtilElementToBeClickable(catalogButton).click();
        logger.info("Шаг для открытия каталога на главной странице Yandex");
        return pageManager.getYandexPage();
    }

    @Step("Шаг для выбора категории 'Ноутбуки' в каталоге")
    public YandexHomePage slectCategory() {
        Assertions.assertTrue(linkNounbooks.isDisplayed(), "Ссылка на категорию 'Ноутбуки' не отображается");

        waitUtilElementToBeVisible(linkNounbooks);
        action.moveToElement(linkNounbooks).perform();
        logger.info("Шаг для выбора категории 'Ноутбуки' в каталоге");
        return pageManager.getYandexPage();
    }

    @Step("Шаг для перехода на страницу планшетов в каталоге")
    public catalogPlanshety TablesPage() {
        Assertions.assertTrue(tabletsButton.isDisplayed(), "Ссылка на страницу с планшетами не отображается");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitUtilElementToBeVisible(tabletsButton).click();
        pageManager.getCataloPlanshety();
        logger.info("Шаг для перехода на страницу планшетов в каталоге");

        return pageManager.getCataloPlanshety();
    }
}
