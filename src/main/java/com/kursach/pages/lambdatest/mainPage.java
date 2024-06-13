package com.kursach.pages.lambdatest;

import com.kursach.pages.BasePage;
import io.smallrye.common.constraint.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class mainPage extends BasePage {

    @FindBy(tagName = "h2")
    private WebElement header;

    @FindBy(className = "ng-binding")
    private WebElement remainingText;

    @FindBy(xpath = "//li[1]/input")
    private WebElement firstItemCheckbox;

    @FindBy(xpath = "//li/input")
    private List<WebElement> itemCheckboxes;

    @FindBy(id = "sampletodotext")
    private WebElement inputField;

    @FindBy(id = "addbutton")
    private WebElement addButton;

    @FindBy(xpath = "//li[last()]/input")
    private WebElement lastItemCheckbox;

    public mainPage checkHeader() {
       assertEquals("LambdaTest Sample App", header.getText());
       return pageManager.getMainPage();
    }
    public mainPage checkRemainingText(String expectedText) {
        assertEquals(expectedText, remainingText.getText());
        return pageManager.getMainPage();
    }
    public mainPage checkFirstItemNotCompleted() {
        String classAttribute = firstItemCheckbox.getAttribute("class");
        assertTrue(classAttribute.contains("ng-pristine"));
        assertTrue(classAttribute.contains("ng-untouched"));
        Assert.assertTrue(classAttribute.contains("ng-valid"));
        return pageManager.getMainPage();
    }

    public mainPage clickFirstItemCheckbox() {
        firstItemCheckbox.click();
        Assert.assertTrue(firstItemCheckbox.isSelected());
        return pageManager.getMainPage();
    }

    public mainPage clickItemCheckbox() {
        for (int i = 1; i < 5; i++) {
            itemCheckboxes.get(i).click();
        }
        return pageManager.getMainPage();
    }

    public mainPage addNewItem(String item) {
        inputField.sendKeys(item);
        addButton.click();
        return pageManager.getMainPage();
    }
    public mainPage clickLastItem(){
        lastItemCheckbox.click();
        Assert.assertTrue(lastItemCheckbox.isSelected());
        return pageManager.getMainPage();
    }
}
