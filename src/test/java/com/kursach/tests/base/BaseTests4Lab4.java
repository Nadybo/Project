package com.kursach.tests.base;

import com.kursach.managers.DriverManager;
import com.kursach.managers.InitManager;
import com.kursach.managers.PageManager;
import com.kursach.managers.TestPropManager;
import com.kursach.utils.PropsConst;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import java.io.IOException;


public class BaseTests4Lab4 {

    private final DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    private final TestPropManager testPropManager = TestPropManager.getInstance();

    @BeforeEach
    public void beforeClass(){
        InitManager.initFramework();
    }

    @BeforeEach
    public void setUp() {
        DriverManager.getInstance().getDriver();
        driverManager.getDriver().get(testPropManager.getProperty(PropsConst.OZON_URL));
    }


//    @AfterEach
//    public void takeScreenshot(TestInfo info) throws IOException {
//        InitManager.quitFramework();
//    }
}
