package com.kursach.pages.ozon;

import com.kursach.pages.BasePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OzonProductSearch extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(OzonProductSearch.class);

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

    @Step("Шаг для проверки страницы")
    public OzonProductSearch checkPage() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(checkText.isDisplayed(), "Текст не отображается на странице");
        assertEquals("ноутбук", waitUtilElementToBeVisible(checkText).getText());
        System.out.println(checkText.getText());
        logger.info("Шаг для проверки страницы");

        return pageManager.getOzonProductSearch();
    }

    @Step("Шаг для выбора товара")
    public OzonProductSearch selectProduct() {
        Assertions.assertTrue(exampleButton.isDisplayed(), "Кнопка не отображается на странице");

        waitUtilElementToBeVisible(exampleButton).click();
        logger.info("Шаг для выбора товара");

        return pageManager.getOzonProductSearch();
    }

    @Step("Шаг для добавления товара в избранное")
    public OzonProductSearch favoritProducts() {
        Assertions.assertTrue(favoriteElement.isDisplayed(), "Кнопка не отображается на странице");
        waitUtilElementToBeClickable(favoriteElement).click();
        logger.info("Шаг для добавления товара в избранное");

        return pageManager.getOzonProductSearch();
    }

    @Step("Шаг для перехода на страницу товара")
    public OzonProductPage motionProductPage() {
        Assertions.assertTrue(productName.isDisplayed(), "Наименование товара не отображается на странице");

        waitUtilElementToBeClickable(productName).click();
        logger.info("Шаг для перехода на страницу товара");

        return pageManager.getOzonProductPage();
    }

    @Step("Шаг для выбора бренда")
    public OzonProductSearch selectBrand() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(samsungElement.isDisplayed(), "Кнопка не отображается на странице");

        waitUtilElementToBeClickable(samsungElement).click();
        logger.info("Шаг для выбора бренда");
        return pageManager.getOzonProductSearch();
    }

    @Step("Шаг для ввода цены от")
    public OzonProductSearch priceFrom(String price_from) {

        Assertions.assertTrue(inputPriceFrom.isDisplayed(), "Поле не отображается на странице");

        inputPriceFrom.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputPriceFrom.sendKeys(Keys.DELETE);
        waitUtilElementToBeVisible(inputPriceFrom).sendKeys(price_from);
        logger.info("Шаг для ввода цены от");

        return pageManager.getOzonProductSearch();
    }

    @Step("Шаг для ввода цены до")
    public OzonProductPage priceBfore(String price_bfore) {
        Assertions.assertTrue(inputPriceBefore.isDisplayed(), "Поле не отображается на странице");

        inputPriceBefore.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputPriceBefore.sendKeys(Keys.DELETE);
        waitUtilElementToBeVisible(inputPriceBefore).sendKeys(price_bfore);
        logger.info("Шаг для ввода цены до");

        return pageManager.getOzonProductPage();
    }
}
