package com.kursach.pages.ozon;

import com.kursach.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class OzonProductPage extends BasePage {

    @FindBy(xpath = "//button[contains(@class, 'n0k_27') and contains(@class, 'b214-a0')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//button[contains(@class, 'n0k_27') and contains(@class, 'b214-a0') and .//span[contains(text(), 'В корзине')]]")
    private WebElement inCartButton;

    @FindBy(xpath = "//button[@class='b214-a0 b214-b5 b214-b1']")
    private WebElement targetButton;
    @FindBy(xpath = "//div[@favorite-test-id='add-and-delete-item-in-favorites']//button[contains(@class, 'b8m_4_6')]")
    private List<WebElement> buttonList;

    @FindBy(xpath = "//button[contains(@class, 'df_9') and contains(@class, 'b214-a0') and contains(@class, 'b214-b5') and contains(@class, 'b214-a4')]")
    private WebElement closeCookies;

    @FindBy(xpath = "//section[@data-widget='emptyState' and @class='ie2_10']")
    private WebElement cheackFavoritBlock;

    public List<WebElement> getButtons() {
        wait.until(ExpectedConditions.visibilityOfAllElements(buttonList));
        return buttonList;
    }

    public OzonProductPage clickSecondButton() {
        List<WebElement> buttons = getButtons();
        if (buttons.size() >= 2) {
            WebElement secondButton = buttons.get(1);
            secondButton.click();
        } else {
            System.out.println("There are not enough buttons to click the second button.");
        }
        return pageManager.getOzonProductPage();
    }

    public OzonProductPage motionBasketPage(){
        waitUtilElementToBeClickable(addToCartButton).click();
        return pageManager.getOzonProductPage();
    }
    public OzonProductPage basketPage(){
        waitUtilElementToBeVisible(inCartButton).click();
        return pageManager.getOzonProductPage();
    }
    public OzonProductPage cookies(){
        waitUtilElementToBeVisible(closeCookies).click();
        return pageManager.getOzonProductPage();
    }
    public OzonProductPage deleteInBasket(){
        waitUtilElementToBeClickable(targetButton).click();
        return pageManager.getOzonProductPage();
    }

}
