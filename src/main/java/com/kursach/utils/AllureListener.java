package com.kursach.utils;

import com.kursach.managers.DriverManager;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.text.SimpleDateFormat;
import java.util.Date;

import static io.qameta.allure.Allure.getLifecycle;

public class AllureListener extends AllureJunit5 {

//    @Override
//    public void testFailure(final TestExecutionSummary.Failure failure) {
//        byte[] byteImage = ((TakesScreenshot) DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
//        String testName = failure.getDescription().getDisplayName();
//        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
//        String screenshotName = String.format("%s_%s.png", testName, timestamp);
//        getLifecycle().addAttachment(screenshotName, "image/png", null, byteImage);
//        super.testFailure(failure);
//    }
}