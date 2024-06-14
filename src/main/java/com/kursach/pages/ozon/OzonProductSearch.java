package com.kursach.pages.ozon;

import com.kursach.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OzonProductSearch extends BasePage {

    @FindBy(xpath = "//span[text()='Samsung']")
    private WebElement samsungElement;

    @FindBy(xpath ="//*[@id='layoutPage']/div[1]/div[2]/div[1]/div/div/div[1]/strong")
    private WebElement checkText;

    @FindBy(xpath = "//div[contains(@class, 'd405-a') and contains(@class, 'jk9_23')]/button[contains(@class, 'i6u_23')]")
    private WebElement exampleButton;

    @FindBy(xpath = "//input[@name='filter' and @type='text' and @class='e305-b0']")
    private WebElement filterInput;

    @FindBy(xpath = "//*[@id='stickyHeader']/div/div[3]/a[1]")
    private WebElement favoriteElement;

    @FindBy(xpath = "//a[contains(@class, 'tile-hover-target') and contains(@class, 'i0x_23') and contains(@class, 'x0i_23')]")
    private WebElement productName;

    public OzonProductSearch checkPage(){
        assertEquals("ноутбук", waitUtilElementToBeVisible(checkText).getText());
        System.out.println(checkText.getText());
        return pageManager.getOzonProductSearch();
    }

    public OzonProductSearch selectProduct() {
       waitUtilElementToBeVisible(exampleButton).click();
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
