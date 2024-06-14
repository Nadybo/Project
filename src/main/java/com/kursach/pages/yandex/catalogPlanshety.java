package com.kursach.pages.yandex;

import com.kursach.pages.BasePage;
import io.qameta.allure.Allure;
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
        // Ожидаем видимости всех элементов списка продуктов
        wait.until(ExpectedConditions.visibilityOfAllElements(products));
        return products; // Возвращаем список элементов продуктов
    }

    public catalogPlanshety logFirstFiveProducts() {
        // Шаг для логирования первых пяти продуктов
        Allure.step("логирование первых пяти продуктов", step -> {
            List<WebElement> productsList = getProducts(); // Получаем список продуктов
            for (int i = 0; i < Math.min(productsList.size(), 5); i++) { // Логируем максимум пять продуктов
                WebElement product = productsList.get(i);
                String productName = product.findElement(By.cssSelector("h3[data-auto='snippet-title']")).getText(); // Находим название продукта
                String productPrice = product.findElement(By.cssSelector("span[data-auto='snippet-price-current']")).getText(); // Находим цену продукта
                logger.info("Product {}: Title - {}, Price - {}", i + 1, productName, productPrice); // Логируем информацию о продукте
                if (i == 1) {
                    secondProductName = productName; // Запоминаем название второго продукта
                    secondProductPrice = productPrice; // Запоминаем цену второго продукта
                }
            }
        });
        return pageManager.getCataloPlanshety(); // Возвращаем страницу каталога планшетов
    }

    public catalogPlanshety filterBySamsung() {
        // Шаг для фильтрации по Samsung
        Allure.step("фильтрация по Samsung", step -> {
            Assertions.assertTrue(samsungCheckbox.isDisplayed(), "Кнопка выбора бренда Samsung не отображается");

            waitUtilElementToBeClickable(samsungCheckbox).click(); // Ждем, пока кнопка не станет кликабельной и кликаем по ней
        });
        return pageManager.getCataloPlanshety(); // Возвращаем страницу каталога планшетов
    }

    public catalogPlanshety SortToCheap() {
        // Шаг для сортировки по возрастанию цены
        Allure.step("сортировка по возрастанию цены", step -> {
            Assertions.assertTrue(sortButton.isDisplayed(), "Кнопка сортировки не отображается");

            waitUtilElementToBeClickable(sortButton).click(); // Ждем, пока кнопка не станет кликабельной и кликаем по ней
        });
        return pageManager.getCataloPlanshety(); // Возвращаем страницу каталога планшетов
    }

    public void searchSecondProduct() {
        // Шаг для поиска второго продукта
        Allure.step("поиск второго продукта", step -> {
            if (secondProductName != null) {
                searchInput.sendKeys(secondProductName); // Вводим название второго продукта в поле поиска
            }
            Assertions.assertTrue(searchButton.isDisplayed(), "Кнопка поиска не отображается");
            waitUtilElementToBeVisible(searchButton).click(); // Ждем, пока кнопка не станет видимой и кликаем по ней
        });
    }

    public String getSecondProductName() {
        return secondProductName; // Возвращаем название второго продукта
    }

    public String getSecondProductPrice() {
        return secondProductPrice; // Возвращаем цену второго продукта
    }
}
