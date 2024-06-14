package com.kursach.pages.mospolytech;

import com.kursach.pages.BasePage;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;


public class mospolytechHomePage extends BasePage {

    @FindBy(xpath = "//a[@class='user-nav__item-link'and@href='/obuchauschimsya/raspisaniya/']")
    private WebElement buttonShedule;

    @FindBy(xpath = "//*[@id='bx_3777608605_2811']/div[3]/div/div[1]/a")
    private WebElement buttonLookAtWebsite;

    public mospolytechHomePage selectSchedule() {
        // Шаг для выбора расписания
        Allure.step("выбор расписания", step -> {
            waitUtilElementToBeClickable(buttonShedule); // Ждем, пока кнопка станет кликабельной
            Assertions.assertTrue(buttonShedule.isDisplayed(), "Кнопка расписания не видна на экране");

            buttonShedule.click(); // Кликаем на кнопку расписания

            wait.until(ExpectedConditions.urlContains("/obuchauschimsya/raspisaniya/")); // Ждем, пока URL содержит указанный путь

            String currentUrl = driver.getCurrentUrl();
            Assertions.assertTrue(currentUrl.contains("/obuchauschimsya/raspisaniya/"), "Переход на новую страницу не выполнен успешно. Текущий URL: " + currentUrl);
        });
        return pageManager.getHomePage();
    }

    public schedulePage motionNextPage() {
        // Шаг для перехода на следующую страницу
        Allure.step("переход на следующую страницу", step -> {
            scrollToElementJs(buttonLookAtWebsite); // Прокрутка до элемента buttonLookAtWebsite
            waitUtilElementToBeVisible(buttonLookAtWebsite).click(); // Ждем, пока кнопка станет видимой и кликаем на нее

            String originalWindow = driver.getWindowHandle(); // Запоминаем исходное окно

            Set<String> windowHandles = driver.getWindowHandles(); // Получаем все открытые окна
            Assertions.assertTrue(windowHandles.size() > 1, "Новая вкладка не открылась после нажатия кнопки 'Посмотреть на сайте'");

            // Переключаемся на новую вкладку
            for (String windowHandle : windowHandles) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
            wait.until(ExpectedConditions.urlContains("rasp.dmami.ru")); // Ждем, пока URL содержит указанный путь

            // Проверяем, что произошел переход на новую страницу
            String newTabUrl = driver.getCurrentUrl();
            Assertions.assertTrue(newTabUrl.contains("rasp.dmami.ru"), "Переход на новую страницу не выполнен успешно. Текущий URL: " + newTabUrl);
        });
        return pageManager.getSchedulepage();
    }

}
