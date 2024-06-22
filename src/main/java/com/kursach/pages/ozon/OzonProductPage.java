package com.kursach.pages.ozon;

import com.kursach.pages.BasePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.xpath.XPath;
import java.util.List;


public class OzonProductPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(OzonProductPage.class);
    private static int count = 0;

    @FindBy(xpath = "//button[.//div[text()='Добавить в корзину']]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//button[.//span[text()='В корзине']]")
    private WebElement inCartButton;

    @FindBy(xpath = "//button[@class='b214-a0 b214-b5 b214-b1']")
    private WebElement targetButton;

    @FindBy(xpath = "//div[@favorite-test-id='add-and-delete-item-in-favorites']/button[2]")
    private WebElement deleteBtn;

    @FindBy(xpath = "//div[@data-widget='cookieBubble']//button")
    private WebElement closeCookies;

    @FindBy(xpath = "//div[@class='g6d_9']//a[2]//span[1]")
    private WebElement indicatorBacket;

    @Step("Шаг для клика по второй кнопке на странице товара")
    public OzonProductPage clickSecondButton() {

        Assertions.assertTrue(deleteBtn.isDisplayed(), "Кнопка 'Добавить в корзину' не отображается");
        waitUtilElementToBeVisible(deleteBtn).click();
        logger.info("Шаг для клика по второй кнопке на странице товара");

        return pageManager.getOzonProductPage();
    }

    @Step("Шаг для перехода на страницу корзины")
    public OzonProductPage motionBasketPage() {
        Assertions.assertTrue(addToCartButton.isDisplayed(), "Кнопка 'Добавить в корзину' не отображается");
        waitUtilElementToBeClickable(addToCartButton).click();
        count++;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(String.valueOf(count), indicatorBacket.getText());
        logger.info("Шаг для перехода на страницу корзины");

        return pageManager.getOzonProductPage();
    }

    @Step("Шаг для перехода на страницу корзины")
    public OzonProductPage basketPage() {

        Assertions.assertTrue(inCartButton.isDisplayed(), "Кнопка 'В корзину' не отображается");
        waitUtilElementToBeVisible(inCartButton).click();
        logger.info("Шаг для перехода на страницу корзины 2");

        return pageManager.getOzonProductPage();
    }

    @Step("Шаг для закрытия уведомления о cookie")
    public OzonProductPage cookies() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(closeCookies.isDisplayed(), "Кнопка закрытия cookie не отображается");
        waitUtilElementToBeVisible(closeCookies).click();
        logger.info("Шаг для закрытия уведомления о cookie");

        return pageManager.getOzonProductPage();
    }

    @Step("Шаг для удаления товара из корзины")
    public void deleteInBasket() {

        Assertions.assertTrue(targetButton.isDisplayed(), "Товар не отображается в корзине");
        waitUtilElementToBeClickable(targetButton).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals("0",indicatorBacket.getText());
        logger.info("Шаг для удаления товара из корзины");
        pageManager.getOzonProductPage();
    }
}
