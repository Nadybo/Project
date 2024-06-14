package com.kursach.pages.ozon;

import com.kursach.pages.BasePage;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class OzonProductPage extends BasePage {

    @FindBy(xpath = "//button[.//div[text()='Добавить в корзину']]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//button[.//span[text()='В корзине']]")
    private WebElement inCartButton;

    @FindBy(xpath = "//button[@class='b214-a0 b214-b5 b214-b1']")
    private WebElement targetButton;

    @FindBy(xpath = "//div[@favorite-test-id='add-and-delete-item-in-favorites']/button[2]")
    private WebElement deleteBtn;
    private List<WebElement> buttonList;

    @FindBy(xpath = "//button[@class='fd1_9 b214-a0 b214-b5 b214-a4' and .//div[text()='ОК']]")
    private WebElement closeCookies;

    @FindBy(xpath = "//section[@data-widget='emptyState' and @class='ie2_10']")
    private WebElement cheackFavoritBlock;

    public List<WebElement> getButtons() {
        wait.until(ExpectedConditions.visibilityOfAllElements(buttonList));
        return buttonList;
    }

    public OzonProductPage clickSecondButton() {
        // Шаг для клика по второй кнопке на странице товара
        Allure.step("клик по второй кнопке", step -> {
            Assertions.assertTrue(deleteBtn.isDisplayed(), "Кнопка 'Добавить в корзину' не отображается");
            waitUtilElementToBeVisible(deleteBtn).click(); // Ждем, пока кнопка не станет видимой, и кликаем по ней
        });
        return pageManager.getOzonProductPage(); // Возвращаем страницу товара на Ozon
    }

    public OzonProductPage motionBasketPage() {
        // Шаг для перехода на страницу корзины
        Allure.step("переход на страницу корзины", step -> {
            Assertions.assertTrue(addToCartButton.isDisplayed(), "Кнопка 'Добавить в корзину' не отображается");
            waitUtilElementToBeClickable(addToCartButton).click(); // Ждем, пока кнопка не станет кликабельной, и кликаем по ней
        });
        return pageManager.getOzonProductPage(); // Возвращаем страницу товара на Ozon
    }

    public OzonProductPage basketPage() {
        // Шаг для перехода на страницу корзины
        Allure.step("страница корзины", step -> {
            Assertions.assertTrue(inCartButton.isDisplayed(), "Кнопка 'В корзину' не отображается");
            waitUtilElementToBeVisible(inCartButton).click(); // Ждем, пока кнопка не станет видимой, и кликаем по ней
        });
        return pageManager.getOzonProductPage(); // Возвращаем страницу товара на Ozon
    }

    public OzonProductPage cookies() {
        // Шаг для закрытия уведомления о cookie
        Allure.step("закрытие cookie", step -> {
            Assertions.assertTrue(closeCookies.isDisplayed(), "Кнопка закрытия cookie не отображается");
            waitUtilElementToBeVisible(closeCookies).click(); // Ждем, пока кнопка не станет видимой, и кликаем по ней
        });
        return pageManager.getOzonProductPage(); // Возвращаем страницу товара на Ozon
    }

    public void deleteInBasket() {
        // Шаг для удаления товара из корзины
        Allure.step("удаление из корзины", step -> {
            Assertions.assertTrue(targetButton.isDisplayed(), "Товар не отображается в корзине");
            waitUtilElementToBeClickable(targetButton).click(); // Ждем, пока кнопка не станет кликабельной, и кликаем по ней
        });
        pageManager.getOzonProductPage(); // Возвращаем страницу товара на Ozon
    }
}
