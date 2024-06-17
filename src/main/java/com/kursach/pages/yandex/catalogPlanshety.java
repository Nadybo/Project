package com.kursach.pages.yandex;

import com.kursach.pages.BasePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class catalogPlanshety extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(catalogPlanshety.class);

    private String secondProductName;
    private String secondProductPrice;

    @FindBy(xpath = "//label[@data-auto='filter-list-item-153061' and contains(@class, '_3Mi7V')]//span[contains(text(), 'Samsung')]")
    private WebElement samsungCheckbox;

    @FindBy(xpath = "//button[@data-autotest-id='aprice' and contains(text(), 'подешевле')]")
    private WebElement sortButton;

    @FindBy(xpath = "//button[contains(@aria-label, 'Сначала дешёвые')]")
    private WebElement cheapFirstOption;

    @FindBy(xpath = "//div[@data-apiary-widget-name='@light/Organic']")
    private List<WebElement> products;

    @FindBy(css = "input[id='header-search']")
    private WebElement searchInput;

    @FindBy(css = "button[data-auto='search-button']")
    private WebElement searchButton;

    public List<WebElement> getProducts() {
        wait.until(ExpectedConditions.visibilityOfAllElements(products));
        return products;
    }

    @Step("Шаг для логирования первых пяти продуктов")
    public catalogPlanshety logFirstFiveProducts() {
        List<WebElement> productsList = getProducts();
        for (int i = 0; i < Math.min(productsList.size(), 5); i++) {
            WebElement product = productsList.get(i);
            String productName = product.findElement(By.cssSelector("h3[data-auto='snippet-title']")).getText();
            String productPrice = product.findElement(By.cssSelector("span[data-auto='snippet-price-current']")).getText();
            logger.info("Product {}: Title - {}, Price - {}", i + 1, productName, productPrice);
            if (i == 1) {
                secondProductName = productName;
                secondProductPrice = productPrice;
                }
            }

        return pageManager.getCataloPlanshety();
    }

    @Step("Шаг для фильтрации по Samsung")
    public catalogPlanshety filterBySamsung() {

        Assertions.assertTrue(samsungCheckbox.isDisplayed(), "Кнопка выбора бренда Samsung не отображается");

        waitUtilElementToBeClickable(samsungCheckbox).click();
        logger.info("Шаг для фильтрации по Samsung");

        return pageManager.getCataloPlanshety();
    }

    @Step("Шаг для сортировки по возрастанию цены")
    public catalogPlanshety SortToCheap() {
        Assertions.assertTrue(sortButton.isDisplayed(), "Кнопка сортировки не отображается");

        waitUtilElementToBeClickable(sortButton).click();
        logger.info("Шаг для сортировки по возрастанию цены");

        return pageManager.getCataloPlanshety();
    }

    @Step("Шаг для поиска второго продукта")
    public void searchSecondProduct() {
        if (secondProductName != null) {
            searchInput.sendKeys(secondProductName); // Вводим название второго продукта в поле поиска
        }
        Assertions.assertTrue(searchButton.isDisplayed(), "Кнопка поиска не отображается");
        waitUtilElementToBeVisible(searchButton).click();
        logger.info("Шаг для поиска второго продукта");

    }
}
