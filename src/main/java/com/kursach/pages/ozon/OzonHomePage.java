package com.kursach.pages.ozon;

import com.kursach.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OzonHomePage extends BasePage {

    @FindBy(css = "input[name='text']")
    private WebElement searchField;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    public OzonHomePage inputDataField(String nameProduct){
        waitUtilElementToBeClickable(searchField).sendKeys(nameProduct);
        return pageManager.getOzonHomePage();
    }

    public OzonProductSearch searchProduct(){
        waitUtilElementToBeVisible(searchButton).click();
        return pageManager.getOzonProductSearch();
    }

}
