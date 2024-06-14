package com.kursach.pages.mospolytech;

import com.kursach.pages.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;


public class mospolytechHomePage extends BasePage {

    @FindBy(xpath = "//a[@class=\"user-nav__item-link\"and@href=\"/obuchauschimsya/raspisaniya/\"]")
    private WebElement buttonShedule;
    @FindBy(xpath = "//a[@href=\"https://rasp.dmami.ru/\"]")
    private WebElement buttonLookAtWebsite;

    public mospolytechHomePage selectSchedule() {
        waitUtilElementToBeClickable(buttonShedule);
        Assertions.assertTrue(buttonShedule.isDisplayed(), "Schedule button is not visible");

        buttonShedule.click();

        wait.until(ExpectedConditions.urlContains("/obuchauschimsya/raspisaniya/"));

        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("/obuchauschimsya/raspisaniya/"), "Navigation to the new page was not successful. Current URL: " + currentUrl);

        return pageManager.getHomePage();
    }
    public schedulePage motionNextPage (){
            scrollToElementJs(buttonLookAtWebsite);
            waitUtilElementToBeClickable(buttonLookAtWebsite);
            Assertions.assertTrue(buttonLookAtWebsite.isDisplayed(), "Button 'Look at Website' is not visible");

            String originalWindow = driver.getWindowHandle();

            buttonLookAtWebsite.click();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Set<String> windowHandles = driver.getWindowHandles();
            Assertions.assertTrue(windowHandles.size() > 1, "New tab did not open after clicking 'Look at Website' button");

            // Переключиться на новую вкладку
            for (String windowHandle : windowHandles) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            wait.until(ExpectedConditions.urlContains("rasp.dmami.ru"));

            // Проверить, что переход на новую страницу произошел
            String newTabUrl = driver.getCurrentUrl();
            Assertions.assertTrue(newTabUrl.contains("rasp.dmami.ru"), "Navigation to the new page was not successful. Current URL: " + newTabUrl);
        return pageManager.getSchedulepage();
    }
}
