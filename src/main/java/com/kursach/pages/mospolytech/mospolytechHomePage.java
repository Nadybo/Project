package com.kursach.pages.mospolytech;

import com.kursach.pages.BasePage;
import com.kursach.pages.lambdatest.mainPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;


public class mospolytechHomePage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(mospolytechHomePage.class);

    @FindBy(xpath = "//a[@class='user-nav__item-link'and@href='/obuchauschimsya/raspisaniya/']")
    private WebElement buttonShedule;

    @FindBy(xpath = "//li[@id='bx_3777608605_2811']//a[@href='https://rasp.dmami.ru/']")
    private WebElement buttonLookAtWebsite;

    @Step("Шаг для выбора расписания")
    public mospolytechHomePage selectSchedule() {
        waitUtilElementToBeClickable(buttonShedule);
        Assertions.assertTrue(buttonShedule.isDisplayed(), "Кнопка расписания не видна на экране");

        buttonShedule.click();

        wait.until(ExpectedConditions.urlContains("/obuchauschimsya/raspisaniya/"));

        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("/obuchauschimsya/raspisaniya/"), "Переход на новую страницу не выполнен успешно. Текущий URL: " + currentUrl);

        logger.info("Шаг для выбора расписания");
        return pageManager.getHomePage();
    }

    @Step("Шаг для перехода на следующую страницу")
    public schedulePage motionNextPage() {
        scrollToElementJs(buttonLookAtWebsite);
        waitUtilElementToBeVisible(buttonLookAtWebsite).click();

        String originalWindow = driver.getWindowHandle();

        Set<String> windowHandles = driver.getWindowHandles();
        Assertions.assertTrue(windowHandles.size() > 1, "Новая вкладка не открылась после нажатия кнопки 'Посмотреть на сайте'");

        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        wait.until(ExpectedConditions.urlContains("rasp.dmami.ru"));

        String newTabUrl = driver.getCurrentUrl();
        Assertions.assertTrue(newTabUrl.contains("rasp.dmami.ru"), "Переход на новую страницу не выполнен успешно. Текущий URL: " + newTabUrl);

        logger.info("Шаг для перехода на следующую страницу");
        return pageManager.getSchedulepage();
    }

}
