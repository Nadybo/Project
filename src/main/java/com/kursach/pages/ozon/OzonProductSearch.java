package com.kursach.pages.ozon;

import com.kursach.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OzonProductSearch extends BasePage {

    @FindBy(xpath = "//span[text()='Samsung']")
    private WebElement samsungElement;

    @FindBy(xpath = "//div[@class='caa3_32']/strong")
    private WebElement checkText;

    @FindBy(css = "div.kj7_23.jk8_23 div.d405-a.kj8_23 button.i4u_23")
    private WebElement exampleButton;

    @FindBy(xpath = "//input[@name='filter' and @type='text' and @class='e305-b0']")
    private WebElement filterInput;

    @FindBy(xpath = "//a[@data-widget='favoriteCounter' and contains(@class, 'h5a od3_16_9')]//span[contains(text(), 'Избранное')]")
    private WebElement favoriteElement;

    @FindBy(xpath = "//div[contains(@class, 'a8b ba9 ac w8i_23')]//span[contains(@class, 'tsBody500Medium')]")
    private WebElement productName;


    public OzonProductSearch selectCotolog(){
        actions.moveToElement(filterInput).doubleClick();
        return pageManager.getOzonProductSearch();
    }
    public OzonProductSearch checkPage(){
        assertEquals("ноутбук", checkText.getText());
        return pageManager.getOzonProductSearch();
    }

    public OzonProductSearch selectProduct() {
        waitUtilElementToBeClickable(exampleButton).click();
        return pageManager.getOzonProductSearch();
    }

    public OzonProductSearch favoritProducts(){
        waitUtilElementToBeClickable(favoriteElement).click();
        return pageManager.getOzonProductSearch();
    }

    public OzonProductPage motionProductPage(){
        waitUtilElementToBeClickable(productName).click();
        return pageManager.getOzonProductPage();
    }

}
