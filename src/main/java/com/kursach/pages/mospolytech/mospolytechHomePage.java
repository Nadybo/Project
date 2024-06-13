package com.kursach.pages.mospolytech;

import com.kursach.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class mospolytechHomePage extends BasePage {

    @FindBy(css = ".user-nav__item:nth-child(3) svg")
    WebElement userNavItemSvg;

    @FindBy(css = "#bx_3777608605_2811 .button-group__item:nth-child(1) > .btn")
    WebElement buttonGroupItemBtn;

    public mospolytechHomePage selectSchedule(){
        waitUtilElementToBeVisible(userNavItemSvg).click();
        return pageManager.getHomePage();

    }
    public schedulePage motionNextPage(){
        waitUtilElementToBeVisible(scrollToElementJs(buttonGroupItemBtn)).click();
        return pageManager.getSchedulepage();
    }
}
