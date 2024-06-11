package com.kursach.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("https://lambdatest.github.io/sample-todo-app/");
    }

    @Test
    public void testPageHeader() {
        String header = driver.findElement(By.tagName("h2")).getText();
        assertEquals("LambdaTest Sample App", header);
    }

    @Test
    public void testRemainingItemsText() {
        String remainingText = driver.findElement(By.className("ng-binding")).getText();
        assertEquals("5 of 5 remaining", remainingText);
    }

    @Test
    public void testFirstItemNotCrossedOut() {
        WebElement firstItem = driver.findElement(By.cssSelector("li.ng-scope:first-child"));
        WebElement checkbox = firstItem.findElement(By.tagName("input"));
        Assert.assertFalse("Чекбокс для первого элемента отмечен", checkbox.isSelected());
        WebElement span = firstItem.findElement(By.tagName("span"));
        Assert.assertTrue("Первый элемент зачеркнут", span.getAttribute("class").contains("done-false"));
    }

    @Test
    public void testCompleteFirstItem() {
        WebElement firstItemCheckbox = driver.findElement(By.xpath("//li[1]/input"));
        firstItemCheckbox.click();
        WebElement firstItem = driver.findElement(By.xpath("//li[1]"));
        String classAttribute = firstItem.getAttribute("class");
        assertEquals("done-true", classAttribute);

        String remainingText = driver.findElement(By.id("todocount")).getText();
        assertEquals("4 of 5 remaining", remainingText);
    }

    @Test
    public void testCompleteAllItems() {
        for (int i = 1; i <= 5; i++) {
            WebElement itemCheckbox = driver.findElement(By.xpath("//li[" + i + "]/input"));
            itemCheckbox.click();
            WebElement item = driver.findElement(By.xpath("//li[" + i + "]"));
            String classAttribute = item.getAttribute("class");
            assertEquals("done-true", classAttribute);
        }

        String remainingText = driver.findElement(By.id("todocount")).getText();
        assertEquals("0 of 5 remaining", remainingText);
    }

    @Test
    public void testAddNewItem() {
        WebElement inputField = driver.findElement(By.id("sampletodotext"));
        WebElement addButton = driver.findElement(By.id("addbutton"));

        int initialRemaining = Integer.parseInt(driver.findElement(By.id("todocount")).getText().split(" ")[0]);

        inputField.sendKeys("New Item");
        addButton.click();

        WebElement newItem = driver.findElement(By.xpath("//li[last()]"));
        String classAttribute = newItem.getAttribute("class");
        assertEquals("done-false", classAttribute);

        int newRemaining = Integer.parseInt(driver.findElement(By.id("todocount")).getText().split(" ")[0]);
        assertEquals(initialRemaining + 1, newRemaining);
    }

    @Test
    public void testCompleteNewItem() {
        WebElement inputField = driver.findElement(By.id("sampletodotext"));
        WebElement addButton = driver.findElement(By.id("addbutton"));

        inputField.sendKeys("New Item");
        addButton.click();

        int initialRemaining = Integer.parseInt(driver.findElement(By.id("todocount")).getText().split(" ")[0]);

        WebElement newItemCheckbox = driver.findElement(By.xpath("//li[last()]/input"));
        newItemCheckbox.click();

        WebElement newItem = driver.findElement(By.xpath("//li[last()]"));
        String classAttribute = newItem.getAttribute("class");
        assertEquals("done-true", classAttribute);

        int newRemaining = Integer.parseInt(driver.findElement(By.id("todocount")).getText().split(" ")[0]);
        assertEquals(initialRemaining - 1, newRemaining);
    }

}
