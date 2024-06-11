package com.kursach.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TodoPage {
    private WebDriver driver;

    public TodoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    private By header = By.tagName("h2");
    private By remainingText = By.className("ng-binding");
    private By firstItemCheckbox = By.xpath("//li[1]/input");
    private By itemCheckboxes = By.xpath("//li/input");
    private By inputField = By.id("sampletodotext");
    private By addButton = By.id("addbutton");
    private By lastItemCheckbox = By.xpath("//li[last()]/input");

    // Methods
    public void open() {
        driver.get("https://lambdatest.github.io/sample-todo-app/");
    }

    public String getHeader() {
        return driver.findElement(header).getText();
    }

    public String getRemainingText() {
        return driver.findElement(remainingText).getText();
    }

    public String getFirstItemClass() {
        return driver.findElement(firstItemCheckbox).getAttribute("class");
    }

    public void clickFirstItemCheckbox() {
        driver.findElement(firstItemCheckbox).click();
    }

    public void clickItemCheckbox(int index) {
        List<WebElement> checkboxes = driver.findElements(itemCheckboxes);
        checkboxes.get(index).click();
    }

    public void addNewItem(String item) {
        driver.findElement(inputField).sendKeys(item);
        driver.findElement(addButton).click();
    }

    public String getLastItemClass() {
        return driver.findElement(lastItemCheckbox).getAttribute("class");
    }
}
