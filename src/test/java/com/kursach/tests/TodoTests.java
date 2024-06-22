package com.kursach.tests;

import com.kursach.tests.base.BaseTests4Lab1;
import com.kursach.utils.AllureListener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

public class TodoTests extends BaseTests4Lab1 {

    @Test
    @DisplayName("Проверка")
    @ExtendWith(AllureListener.class)
    public void tests() {
        pageManager.getMainPage()
        .checkHeader()
        .checkRemainingText("5 of 5 remaining")
        .clickFirstItemCheckbox("First Item")
        .markItemAsDone("First Item")
        .checkRemainingText("4 of 5 remaining")
        .clickItemCheckbox()
        .checkRemainingText("0 of 5 remaining")
        .addNewItem("6th checkbox")
        .checkRemainingText("1 of 6 remaining")
        .clickLastItem();
    }
}
