package com.kursach.pages.yandex;

import com.kursach.pages.BasePage;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
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
        // Шаг для открытия каталога на главной странице Yandex
        Allure.step("открытие каталога", step -> {
            Assertions.assertTrue(catalogButton.isDisplayed(), "Кнопка каталога не отображается на странице");

            waitUtilElementToBeClickable(catalogButton).click(); // Ждем, пока кнопка не станет кликабельной и кликаем по ней
        });
        return pageManager.getYandexPage(); // Возвращаем страницу Yandex
    }

    public YandexHomePage slectCategory() {
        // Шаг для выбора категории "Ноутбуки" в каталоге
        Allure.step("выбор категории", step -> {
            Assertions.assertTrue(linkNounbooks.isDisplayed(), "Ссылка на категорию 'Ноутбуки' не отображается");

            waitUtilElementToBeVisible(linkNounbooks); // Ждем, пока ссылка не станет видимой
            action.moveToElement(linkNounbooks).perform(); // Наводим курсор на элемент
        });
        return pageManager.getYandexPage(); // Возвращаем страницу Yandex
    }

    public catalogPlanshety TablesPage() {
        // Шаг для перехода на страницу планшетов в каталоге
        Allure.step("страница с планшетами", step -> {
            Assertions.assertTrue(tabletsButton.isDisplayed(), "Ссылка на страницу с планшетами не отображается");

            try {
                Thread.sleep(5000); // Делаем паузу для стабилизации интерфейса (можно рассмотреть замену на более надежные ожидания)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitUtilElementToBeVisible(tabletsButton).click(); // Ждем, пока ссылка на планшеты не станет видимой и кликаем по ней
            pageManager.getCataloPlanshety(); // Возвращаем страницу каталога планшетов
        });
        return pageManager.getCataloPlanshety(); // Возвращаем страницу каталога планшетов
    }
}
