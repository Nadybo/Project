package com.kursach.pages;

import com.kursach.managers.DriverManager;
import com.kursach.managers.PageManager;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected DriverManager driverManager = DriverManager.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(50),Duration.ofSeconds(5));
    protected PageManager pageManager = PageManager.getInstance();
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();
    protected Actions actions;

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
        this.actions = new Actions(driverManager.getDriver());
    }

    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    protected WebElement waitUtilElementToBeClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected WebElement waitUtilElementToBeVisible(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    protected WebElement waitUtilElementToBeVisible(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
