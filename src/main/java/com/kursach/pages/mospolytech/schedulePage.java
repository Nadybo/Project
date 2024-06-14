package com.kursach.pages.mospolytech;

import com.kursach.pages.BasePage;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;


public class schedulePage extends BasePage {

    @FindBy(xpath = "//input[@class=\"groups\"and@placeholder=\"группа ...\"]")
    private WebElement inputGroup;

    @FindBy(xpath = "//div[@class=\"found-groups row not-print\"]/div[1]")
    private WebElement resultGroup;

    @FindBy(xpath = "//div[@class=\"found-groups row not-print\"]")
    private List<WebElement> resultGroups;
    @FindBy(xpath = "//div[@class='schedule-day schedule-day_today']")
    private WebElement todayScheduleDay;
    @FindBy(xpath = "//div[@class='schedule-day schedule-day_today']/div[1]")
    private WebElement todayScheduleDayTitle;


    public schedulePage clearfield(String groupNumber) {
        // Шаг для очистки поля и ввода номера группы
        Allure.step("очистка поля и ввод номера группы", step -> {
            waitUtilElementToBeVisible(inputGroup); // Ждем, пока поле ввода станет видимым
            waitUtilElementToBeClickable(inputGroup); // Ждем, пока поле ввода станет кликабельным

            inputGroup.clear(); // Очищаем поле ввода
            inputGroup.sendKeys(groupNumber); // Вводим номер группы
            try {
                Thread.sleep(1000); // Делаем паузу для стабилизации интерфейса
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Ждем, пока появится результат поиска
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"found-groups row not-print\"]")));
            try {
                Thread.sleep(2000); // Делаем дополнительную паузу для стабилизации интерфейса
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Проверяем, что найден ровно один результат поиска для номера группы
            Assertions.assertEquals(1, resultGroups.size(), "Ожидался ровно один результат поиска для номера группы: " + groupNumber + ", но найдено " + resultGroups.size());

            WebElement searchResult = resultGroup;
            // Проверяем, что результат поиска содержит номер группы в атрибуте id
            Assertions.assertTrue(searchResult.getAttribute("id").contains(groupNumber), "Результат поиска не содержит номер группы: " + groupNumber);

            try {
                Thread.sleep(1000); // Делаем паузу для стабилизации интерфейса
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        return pageManager.getSchedulepage(); // Возвращаем страницу расписания
    }

    public schedulePage checkClickSearchForGroup() {
        // Шаг для проверки и клика по результату поиска группы
        Allure.step("проверка и клик по результату поиска группы", step -> {
            waitUtilElementToBeClickable(resultGroup); // Ждем, пока результат поиска станет кликабельным

            scrollWithOffset(resultGroup, 0, 100); // Прокручиваем до элемента resultGroup с смещением по вертикали
            try {
                Thread.sleep(2000); // Делаем паузу для стабилизации интерфейса
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            resultGroup.click(); // Кликаем по результату поиска

            waitUtilElementToBeVisible(todayScheduleDay); // Ждем, пока страница расписания станет видимой

            String currentDay = getCurrentDayOfWeek(); // Получаем текущий день недели
            try {
                Thread.sleep(2000); // Делаем паузу для стабилизации интерфейса
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Проверяем, что текущий день недели выделен на странице расписания
            Assertions.assertTrue(todayScheduleDay.getAttribute("class").contains("schedule-day_today"), "Текущий день не выделен на странице расписания");
            // Проверяем, что выделенный день соответствует текущему дню недели
            Assertions.assertTrue(todayScheduleDayTitle.getText().contains(currentDay), "Выделенный день не соответствует текущему дню недели");
        });

        return pageManager.getSchedulepage(); // Возвращаем страницу расписания
    }

    public String getCurrentDayOfWeek() {
        // Метод для получения текущего дня недели на русском языке
        LocalDate currentDate = LocalDate.now(); // Получаем текущую дату

        DayOfWeek dayOfWeek = currentDate.getDayOfWeek(); // Получаем день недели

        // Возвращаем название дня недели на русском языке
        switch (dayOfWeek) {
            case MONDAY:
                return "Понедельник";
            case TUESDAY:
                return "Вторник";
            case WEDNESDAY:
                return "Среда";
            case THURSDAY:
                return "Четверг";
            case FRIDAY:
                return "Пятница";
            case SATURDAY:
                return "Суббота";
            case SUNDAY:
                return "Воскресенье";
            default:
                return "";
        }
    }


}
