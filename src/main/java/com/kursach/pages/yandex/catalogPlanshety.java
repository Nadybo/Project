package com.kursach.pages.yandex;

import com.kursach.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class catalogPlanshety extends BasePage {

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

    public catalogPlanshety logFirstFiveProducts() {
        List<WebElement> productsList = getProducts();
        for (int i = 0; i < Math.min(productsList.size(), 5); i++) {
            WebElement product = productsList.get(i);
            String productName = product.findElement(By.cssSelector("h3[data-auto='snippet-title']")).getText();
            String productPrice = product.findElement(By.cssSelector("span[data-auto='snippet-price-current']")).getText();
            System.out.printf("Product %d: %s - %s%n", i + 1, productName, productPrice);
            if (i == 1) {
                secondProductName = productName;
                secondProductPrice = productPrice;
            }
        }
        return pageManager.getCataloPlanshety();
    }


    public catalogPlanshety filterBySamsung() {
        waitUtilElementToBeClickable(samsungCheckbox).click();
//        Assert.assertTrue(samsungCheckbox.isSelected());
        return pageManager.getCataloPlanshety();
    }

   public catalogPlanshety SortToCheap(){
        waitUtilElementToBeClickable(sortButton).click();
        return pageManager.getCataloPlanshety();
   }

    public void searchSecondProduct() {
        if (secondProductName != null) {
            searchInput.sendKeys(secondProductName);

        }
        searchButton.click();
    }

//    public String getSecondProductName() {
//        return secondProductName;
//    }
//
//    public String getSecondProductPrice() {
//        return secondProductPrice;
//    }
}
