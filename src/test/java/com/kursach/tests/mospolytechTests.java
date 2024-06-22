package com.kursach.tests;

import com.kursach.tests.base.BaseTests;
import com.kursach.utils.AllureListener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

public class mospolytechTests extends BaseTests {

    @Test
    @DisplayName("Проверка")
    @ExtendWith(AllureListener.class)
    public void Tests() {
        pageManager.getHomePage()
                .selectSchedule()
                .motionNextPage()
                .clearfield("221-361");
//                .checkClickSearchForGroup();
    }
}
