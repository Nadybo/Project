package com.kursach.utils;

import com.kursach.managers.DriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AllureListener extends AllureJunit5 implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        takeAndAttachScreenshot(context);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        takeAndAttachScreenshot(context);
    }

    private void takeAndAttachScreenshot(ExtensionContext context) {
        WebDriver driver = DriverManager.getInstance().getDriver();
        byte[] byteImage = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        String testName = context.getDisplayName();
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String screenshotName = String.format("%s_%s.png", testName, timestamp);
        Allure.getLifecycle().addAttachment(screenshotName, "image/png", null, byteImage);
    }
}
