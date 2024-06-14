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
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.logging.Logger;

public class BasePage {
    protected DriverManager driverManager = DriverManager.getInstance();
    protected WebDriver driver = driverManager.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(50));
    protected PageManager pageManager = PageManager.getInstance();
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();
    protected Actions action = new Actions(driverManager.getDriver());

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }
    public WebElement scrollWithOffset(WebElement element, int x, int y) {
        String code = "window.scroll(" + (element.getLocation().x + x) + ","
                + (element.getLocation().y + y) + ");";
        ((JavascriptExecutor) driverManager.getDriver()).executeScript(code, element, x, y);
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
