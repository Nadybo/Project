package com.kursach.pages.lambdatest;

import com.kursach.pages.BasePage;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
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
        // Проверяем, что текст заголовка равен "LambdaTest Sample App"
        assertEquals("LambdaTest Sample App", header.getText());
        // Возвращаем объект mainPage после проверки
        return pageManager.getMainPage();
    }

    public mainPage checkRemainingText(String expectedText) {
        // Проверяем, что remainingText равен ожидаемому тексту
        assertEquals(expectedText, remainingText.getText());
        // Возвращаем объект mainPage после проверки
        return pageManager.getMainPage();
    }

    public mainPage checkFirstItemNotCompleted() {
        // Шаг для проверки, что первый элемент не завершен
        Allure.step("проверка первого элемента на незавершенность", step -> {
            try {
                Thread.sleep(1000); // Рассмотрите замену на корректное ожидание
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Получаем атрибут class у чекбокса первого элемента
            String classAttribute = firstItemCheckbox.getAttribute("class");
            // Утверждения для проверки состояния чекбокса
            assertTrue(classAttribute.contains("ng-pristine"));
            assertTrue(classAttribute.contains("ng-untouched"));
            Assertions.assertTrue(classAttribute.contains("ng-valid"));
        });
        // Возвращаем объект mainPage после проверки
        return pageManager.getMainPage();
    }

    public mainPage clickFirstItemCheckbox() {
        // Шаг для клика по чекбоксу первого элемента
        Allure.step("клик по чекбоксу первого элемента", step -> {
            try {
                Thread.sleep(1000); // Рассмотрите замену на корректное ожидание
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Кликаем по чекбоксу первого элемента
            firstItemCheckbox.click();
            Assertions.assertTrue(firstItemCheckbox.isSelected());
        });
        // Возвращаем объект mainPage после проверки
        return pageManager.getMainPage();
    }

    public mainPage clickItemCheckbox() {
        // Шаг для клика по чекбоксам элементов
        Allure.step("клик по чекбоксам элементов", step -> {
            for (int i = 1; i < 5; i++) {
                itemCheckboxes.get(i).click();
            }
        });
        // Возвращаем объект mainPage после кликов
        return pageManager.getMainPage();
    }

    public mainPage addNewItem(String item) {
        // Шаг для добавления нового элемента
        Allure.step("добавление нового элемента", step -> {
            inputField.sendKeys(item);
            addButton.click();
        });
        // Возвращаем объект mainPage после добавления
        return pageManager.getMainPage();
    }

    public mainPage clickLastItem() {
        // Шаг для клика по последнему элементу
        Allure.step("клик по последнему элементу", step -> {
            lastItemCheckbox.click();
            Assertions.assertTrue(lastItemCheckbox.isSelected());
        });
        // Возвращаем объект mainPage после клика
        return pageManager.getMainPage();
    }



}
