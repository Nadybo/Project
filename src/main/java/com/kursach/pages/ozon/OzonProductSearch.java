package com.kursach.pages.ozon;

import com.kursach.pages.BasePage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OzonProductSearch extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(OzonProductSearch.class);
    private static int clickIndex = 0;

    @FindBy(xpath = "//span[text()='Samsung']")
    private WebElement samsungElement;

    @FindBy(xpath ="//*[@id='layoutPage']/div[1]/div[2]/div[1]/div/div/div[1]/strong")
    private WebElement checkText;

    @FindBy(xpath = "//*[@id=\"paginatorContent\"]/div/div/div[1]/div[4]/div/button")
    private WebElement exampleButton;

    @FindBy(xpath = "//input[@name='filter' and @type='text' and @class='e305-b0']")
    private WebElement filterInput;

    @FindBy(xpath = "//*[@id='stickyHeader']/div/div[3]/a[1]")
    private WebElement favoriteElement;

    @FindBy(xpath = "//*[@id=\"layoutPage\"]/div[1]/div[2]/div/div/div/div[2]/div/div/div[5]/div/div/div/div/div/div[1]/a")
    private WebElement productName;

    @FindBy(xpath = "//*[@id='layoutPage']/div[1]/div[2]/div[2]/div[1]/div/aside/div[2]/div[4]/div[2]/div[1]/div/div[1]/div/input")
    private  WebElement inputPriceFrom;

    @FindBy(xpath = "//*[@id='layoutPage']/div[1]/div[2]/div[2]/div[1]/div/aside/div[2]/div[4]/div[2]/div[1]/div/div[2]/div/input")
    private WebElement inputPriceBefore;

    @FindBy(xpath = "//*[@id=\"layoutPage\"]/div[1]/div[2]/div[2]/div[2]/div[3]/div/div/div/div/div/button/span/div/span")
    private WebElement filterTitle;

    @FindBy(xpath = "//*[@id=\"layoutPage\"]/div[1]/div[2]/div[2]/div[1]/div/aside/div[2]/div[4]/div[1]/span")
    private WebElement missClick;

    @FindBy(xpath = "//*[@id='stickyHeader']/div/div[3]/a[1]/span")
    private WebElement indicatorFavProduct;

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
        clickIndex++;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(String.valueOf(clickIndex), indicatorFavProduct.getText());
        logger.info("Шаг для выбора товара" + "index: " + clickIndex);

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
        assertEquals("Бренд: Samsung",filterTitle.getText());
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
    public OzonProductSearch priceBfore(String price_bfore) {
        Assertions.assertTrue(inputPriceBefore.isDisplayed(), "Поле не отображается на странице");

        inputPriceBefore.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputPriceBefore.sendKeys(Keys.DELETE);
        waitUtilElementToBeVisible(inputPriceBefore).sendKeys(price_bfore);
        missClick.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(inputPriceBefore.getAttribute("value")+ ":" + inputPriceFrom.getAttribute("value"));
        logger.info("Шаг для ввода цены до");

        return pageManager.getOzonProductSearch();
    }

    @Step("Проверка полей после ввода значение")
    public void checkingPriceFiled(String priceFrom, String priceBefore){
        String inputFrom = inputPriceFrom.getAttribute("value").replaceAll("\\s", "");
        String inputBefore = inputPriceBefore.getAttribute("value").replaceAll("\\s", "");
        assertEquals(priceFrom.replaceAll("\\s", ""), inputFrom);
        assertEquals(priceBefore.replaceAll("\\s", ""), inputBefore);
        logger.info("Проверка полей после ввода значение");
        pageManager.getOzonProductPage();
    }
}
