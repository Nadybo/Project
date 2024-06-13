package com.kursach.pages.mospolytech;

import com.kursach.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;


public class schedulePage extends BasePage {

    @FindBy(css= ".groups:nth-child(2)")
    private WebElement inputFild;

    @FindBy(id= "221-361")
    private WebElement groupNumber;

    ArrayList<String> tabs = new ArrayList<>(driverManager.getDriver().getWindowHandles());

    public schedulePage clearfield(String number){
       waitUtilElementToBeClickable(inputFild).sendKeys(number);
       return pageManager.getSchedulepage();
    }
    public schedulePage selectGroup(){
        waitUtilElementToBeClickable(groupNumber).click();
        return pageManager.getSchedulepage();

    }

}
