package com.kursach.managers;

public class InitManager {
    private static final TestPropManager props = TestPropManager.getInstance();
    private  static final DriverManager driverManager = DriverManager.getInstance();

    public static void initFramework(){
        driverManager.getDriver().manage().window().maximize();
    }

    public static void quitFramework(){
        driverManager.quitDriver();
    }

}
