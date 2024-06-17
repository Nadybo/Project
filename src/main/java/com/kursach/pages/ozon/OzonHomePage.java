package com.kursach.pages.ozon;

import com.kursach.pages.BasePage;
import com.kursach.pages.mospolytech.schedulePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OzonHomePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(OzonHomePage.class);

    @FindBy(css = "input[name='text']")
    private WebElement searchField;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    @Step("Шаг для ввода данных в поле поиска товара")
    public OzonHomePage inputDataField(String nameProduct) {

        Assertions.assertTrue(searchField.isDisplayed(), "Поле поиска не отображается на экране");
        waitUtilElementToBeClickable(searchField).sendKeys(nameProduct);
        logger.info("Шаг для ввода данных в поле поиска товара");

        return pageManager.getOzonHomePage();
    }

    @Step("Шаг для выполнения поиска товара")
    public OzonProductSearch searchProduct() {
        Assertions.assertTrue(searchButton.isDisplayed(), "Кнопка поиска не отображается на экране");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitUtilElementToBeVisible(searchButton).click();
        logger.info("Шаг для выполнения поиска товара");
        return pageManager.getOzonProductSearch();
    }


}
