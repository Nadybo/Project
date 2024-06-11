package com.kursach.tests;

import com.kursach.pages.TodoPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTests {
    private WebDriver driver;
    private TodoPage todoPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        todoPage = new TodoPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testPageHeader() {
        todoPage.open();
        assertEquals("LambdaTest Sample App", todoPage.getHeader());
    }

    @Test
    public void testRemainingItemsText() {
        todoPage.open();
        assertEquals("5 of 5 remaining", todoPage.getRemainingText());
    }

    @Test
    public void testFirstItemNotCompleted() {
        todoPage.open();
        assertTrue(todoPage.getFirstItemClass().contains("ng-pristine"));
        assertTrue(todoPage.getFirstItemClass().contains("ng-untouched"));
        assertTrue(todoPage.getFirstItemClass().contains("ng-valid"));
    }

    @Test
    public void testCompleteFirstItem() {
        todoPage.open();
        todoPage.clickFirstItemCheckbox();
        assertTrue(todoPage.getFirstItemClass().contains("ng-dirty"));
        assertTrue(todoPage.getFirstItemClass().contains("ng-touched"));
        assertTrue(todoPage.getFirstItemClass().contains("ng-valid"));
        assertEquals("4 of 5 remaining", todoPage.getRemainingText());
    }

    @Test
    public void testCompleteAllItems() {
        todoPage.open();
        for (int i = 0; i < 5; i++) {
            todoPage.clickItemCheckbox(i);
        }
        assertEquals("0 of 5 remaining", todoPage.getRemainingText());
    }

    @Test
    public void testAddNewItem() {
        todoPage.open();
        int initialRemaining = Integer.parseInt(todoPage.getRemainingText().split(" ")[0]);
        todoPage.addNewItem("New Item");
        assertTrue(todoPage.getLastItemClass().contains("ng-pristine"));
        assertTrue(todoPage.getLastItemClass().contains("ng-untouched"));
        assertTrue(todoPage.getLastItemClass().contains("ng-valid"));
        int newRemaining = Integer.parseInt(todoPage.getRemainingText().split(" ")[0]);
        assertEquals(initialRemaining + 1, newRemaining);
    }

    @Test
    public void testCompleteNewItem() {
        todoPage.open();
        todoPage.addNewItem("New Item");
        int initialRemaining = Integer.parseInt(todoPage.getRemainingText().split(" ")[0]);
        todoPage.clickItemCheckbox(5); // New item is at the last index (5)
        assertTrue(todoPage.getLastItemClass().contains("ng-dirty"));
        assertTrue(todoPage.getLastItemClass().contains("ng-touched"));
        assertTrue(todoPage.getLastItemClass().contains("ng-valid"));
        int newRemaining = Integer.parseInt(todoPage.getRemainingText().split(" ")[0]);
        assertEquals(initialRemaining - 1, newRemaining);
    }
}
