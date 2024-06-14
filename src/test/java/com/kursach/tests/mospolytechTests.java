package com.kursach.tests;

import com.kursach.tests.base.BaseTests;
import org.junit.jupiter.api.Test;

public class mospolytechTests extends BaseTests {

    @Test
    public void Tests() {
        pageManager.getHomePage()
                .selectSchedule()
                .motionNextPage()
                .clearfield("221-361");
//                .checkClickSearchForGroup();
    }
}
