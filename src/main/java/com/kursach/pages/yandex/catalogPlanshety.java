package com.kursach.pages.yandex;

import com.kursach.pages.BasePage;
import io.github.bonigarcia.wdm.webdriver.WebDriverBrowser;
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

    @FindBy(xpath = "//*[@id='/content/page/fancyPage/cms/1/SearchTitleWithBreadcrumbs-SearchTitleWithBreadcrumbs']/div/div/div/h1")
    private WebElement checkPageTables;

    @FindBy(xpath = "//*[@id='/content/page/fancyPage/cms/3/QuickFiltersWrapper-QuickFiltersWrapper/quickFilters']/div/div/div/div[1]/div/button/span")
    private WebElement filterTitle;

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

    @Step("Проверка заголовка страницу")
    public catalogPlanshety checkPageTitele(String title){
        Assertions.assertEquals(checkPageTables.getText(),title,"Проверка заголовка страницу планшеты");
        logger.info("Проверка заголовка страницу планшеты");
        return this;
    }

    @Step("Шаг для фильтрации по Samsung")
    public catalogPlanshety filterBySamsung() {

        Assertions.assertTrue(samsungCheckbox.isDisplayed(), "Кнопка выбора бренда Samsung не отображается");

        waitUtilElementToBeClickable(samsungCheckbox).click();
        Assertions.assertEquals(filterTitle.getText(),"Samsung");
        logger.info("Шаг для фильтрации по Samsung");

        return pageManager.getCataloPlanshety();
    }

    @Step("Шаг для сортировки по возрастанию цены")
    public catalogPlanshety SortToCheap() {
        Assertions.assertTrue(sortButton.isDisplayed(), "Кнопка сортировки не отображается");

        waitUtilElementToBeClickable(sortButton).click();

        String ariaPressed = sortButton.getAttribute("aria-pressed");
        if ("true".equals(ariaPressed)) {
            System.out.println("Кнопка выбрана (aria-pressed='true').");
        } else {
            System.out.println("Кнопка не выбрана (aria-pressed!='true').");
        }
        logger.info("Шаг для сортировки по возрастанию цены");

        return pageManager.getCataloPlanshety();
    }

    @Step("Шаг для поиска второго продукта")
    public catalogPlanshety searchSecondProduct() {
        if (secondProductName != null) {
            searchInput.sendKeys(secondProductName);
        }
        Assertions.assertTrue(searchButton.isDisplayed(), "Кнопка поиска не отображается");
        waitUtilElementToBeVisible(searchButton).click();
        logger.info("Шаг для поиска второго продукта");
        return this;
    }

    @Step("Проверка результатов поиска")
    public void CheckingSearchResults(){
        waitUtilElementToBeVisible(checkPageTables);
        Assertions.assertEquals(checkPageTables.getText(),secondProductName,"Проверка результатов поиска");
    }
}

