package com.kursach.pages.ozon;

import com.kursach.pages.BasePage;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OzonProductSearch extends BasePage {

    @FindBy(xpath = "//span[text()='Samsung']")
    private WebElement samsungElement;

    @FindBy(xpath ="//*[@id='layoutPage']/div[1]/div[2]/div[1]/div/div/div[1]/strong")
    private WebElement checkText;

    @FindBy(xpath = "//div[contains(@class, 'd405-a') and contains(@class, 'jk9_23')]/button[contains(@class, 'i6u_23')]")
    private WebElement exampleButton;

    @FindBy(xpath = "//input[@name='filter' and @type='text' and @class='e305-b0']")
    private WebElement filterInput;

    @FindBy(xpath = "//*[@id='stickyHeader']/div/div[3]/a[1]")
    private WebElement favoriteElement;

    @FindBy(xpath = "//a[contains(@class, 'tile-hover-target') and contains(@class, 'i0x_23') and contains(@class, 'x0i_23')]")
    private WebElement productName;

    @FindBy(xpath = "//*[@id='layoutPage']/div[1]/div[2]/div[2]/div[1]/div/aside/div[2]/div[4]/div[2]/div[1]/div/div[1]/div/input")
    private  WebElement inputPriceFrom;

    @FindBy(xpath = "//*[@id='layoutPage']/div[1]/div[2]/div[2]/div[1]/div/aside/div[2]/div[4]/div[2]/div[1]/div/div[2]/div/input")
    private WebElement inputPriceBefore;

    public OzonProductSearch checkPage() {
        // Шаг для проверки страницы
        Allure.step("проверка страницы", step -> {
            try {
                Thread.sleep(2000); // Делаем паузу для стабилизации интерфейса
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assertions.assertTrue(checkText.isDisplayed(), "Текст не отображается на странице");
            assertEquals("ноутбук", waitUtilElementToBeVisible(checkText).getText()); // Проверяем текст на соответствие "ноутбук"
            System.out.println(checkText.getText()); // Выводим текст на консоль
        });
        return pageManager.getOzonProductSearch(); // Возвращаем страницу поиска товаров на Ozon
    }

    public OzonProductSearch selectProduct() {
        // Шаг для выбора товара
        Allure.step("выбор товара", step -> {
            Assertions.assertTrue(exampleButton.isDisplayed(), "Кнопка не отображается на странице");

            waitUtilElementToBeVisible(exampleButton).click(); // Ждем, пока кнопка не станет видимой и кликаем по ней
        });
        return pageManager.getOzonProductSearch(); // Возвращаем страницу поиска товаров на Ozon
    }

    public OzonProductSearch favoritProducts() {
        // Шаг для добавления товара в избранное
        Allure.step("добавление в избранное", step -> {
            Assertions.assertTrue(favoriteElement.isDisplayed(), "Кнопка не отображается на странице");

            waitUtilElementToBeClickable(favoriteElement).click(); // Ждем, пока кнопка не станет кликабельной и кликаем по ней
        });
        return pageManager.getOzonProductSearch(); // Возвращаем страницу поиска товаров на Ozon
    }

    public OzonProductPage motionProductPage() {
        // Шаг для перехода на страницу товара
        Allure.step("переход на страницу товара", step -> {
            Assertions.assertTrue(productName.isDisplayed(), "Наименование товара не отображается на странице");

            waitUtilElementToBeClickable(productName).click(); // Ждем, пока наименование товара не станет кликабельным и кликаем по нему
        });
        return pageManager.getOzonProductPage(); // Возвращаем страницу товара на Ozon
    }

    public OzonProductSearch selectBrand() {
        // Шаг для выбора бренда
        Allure.step("выбор бренда", step -> {
            try {
                Thread.sleep(2000); // Делаем паузу для стабилизации интерфейса
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assertions.assertTrue(samsungElement.isDisplayed(), "Кнопка не отображается на странице");

            waitUtilElementToBeClickable(samsungElement).click(); // Ждем, пока кнопка не станет кликабельной и кликаем по ней
        });
        return pageManager.getOzonProductSearch(); // Возвращаем страницу поиска товаров на Ozon
    }

    public OzonProductSearch priceFrom(String price_from) {
        // Шаг для ввода цены "от"
        Allure.step("цена от", step -> {
            Assertions.assertTrue(inputPriceFrom.isDisplayed(), "Поле не отображается на странице");

            inputPriceFrom.sendKeys(Keys.chord(Keys.CONTROL, "a")); // Выделяем всё
            inputPriceFrom.sendKeys(Keys.DELETE); // Удаляем текущее значение
            waitUtilElementToBeVisible(inputPriceFrom).sendKeys(price_from); // Вводим новое значение цены "от"
        });
        return pageManager.getOzonProductSearch(); // Возвращаем страницу поиска товаров на Ozon
    }

    public OzonProductPage priceBfore(String price_bfore) {
        // Шаг для ввода цены "до"
        Allure.step("цена до", step -> {
            Assertions.assertTrue(inputPriceBefore.isDisplayed(), "Поле не отображается на странице");

            inputPriceBefore.sendKeys(Keys.chord(Keys.CONTROL, "a")); // Выделяем всё
            inputPriceBefore.sendKeys(Keys.DELETE); // Удаляем текущее значение
            waitUtilElementToBeVisible(inputPriceBefore).sendKeys(price_bfore); // Вводим новое значение цены "до"
        });
        return pageManager.getOzonProductPage(); // Возвращаем страницу товара на Ozon
    }
}
