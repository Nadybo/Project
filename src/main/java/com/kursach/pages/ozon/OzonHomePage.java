package com.kursach.pages.ozon;

import com.kursach.pages.BasePage;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OzonHomePage extends BasePage {

    @FindBy(css = "input[name='text']")
    private WebElement searchField;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    public OzonHomePage inputDataField(String nameProduct) {
        // Шаг для ввода данных в поле поиска товара
        Allure.step("ввод данных в поле поиска", step -> {
            Assertions.assertTrue(searchField.isDisplayed(), "Поле поиска не отображается на экране");
            waitUtilElementToBeClickable(searchField).sendKeys(nameProduct); // Ждем, пока поле поиска не станет кликабельным и вводим текст nameProduct
        });
        return pageManager.getOzonHomePage(); // Возвращаем объект домашней страницы Ozon
    }

    public OzonProductSearch searchProduct() {
        // Шаг для выполнения поиска товара
        Allure.step("поиск товара", step -> {
            Assertions.assertTrue(searchButton.isDisplayed(), "Кнопка поиска не отображается на экране");
            try {
                Thread.sleep(2000); // Делаем паузу для стабилизации интерфейса
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitUtilElementToBeVisible(searchButton).click(); // Ждем, пока кнопка поиска не станет видимой и кликаем по ней
        });
        return pageManager.getOzonProductSearch(); // Возвращаем страницу результатов поиска Ozon
    }


}
