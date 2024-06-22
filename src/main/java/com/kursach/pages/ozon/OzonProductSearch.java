package com.kursach.pages.ozon;

import com.kursach.pages.BasePage;
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
    private static int clickIndex = 0;

    @FindBy(xpath = "//span[text()='Samsung']")
    private WebElement samsungElement;

    @FindBy(xpath ="//*[@id='layoutPage']//strong")
    private WebElement checkText;

    @FindBy(xpath = "//div[@class='d405-a kj1_23']//button[1]")
    private WebElement exampleButton;

    @FindBy(xpath = "//a[@data-widget='favoriteCounter']")
    private WebElement favoriteElement;

    @FindBy(xpath = "//div[@class='y9i_23']//a[1]")
    private WebElement productName;

    @FindBy(xpath = "//div[1]/div[@class='f05-b0 f05-b3']/input")
    private  WebElement inputPriceFrom;

    @FindBy(xpath = "//div[2]/div[@class='f05-b0 f05-b3']/input")
    private WebElement inputPriceBefore;

    @FindBy(xpath = "//div[@class='e905-a4 e905-a5']/span")
    private WebElement filterTitle;

    @FindBy(xpath = "//span[text()='Цена']")
    private WebElement missClick;

    @FindBy(xpath = "//a[@data-widget='favoriteCounter']//span[1]")
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
    public void checkingPriceFiled(String priceFrom){
        String inputFrom = inputPriceFrom.getAttribute("value").replaceAll("\\s", "");
        assertEquals(priceFrom.replaceAll("\\s", ""), inputFrom);
        logger.info("Проверка полей после ввода значение");
        pageManager.getOzonProductPage();
    }
}
