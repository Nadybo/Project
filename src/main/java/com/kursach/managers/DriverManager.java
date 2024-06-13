package com.kursach.managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager {

    private static DriverManager INTANCE = null;

    private WebDriver driver;

    private DriverManager(){

    }
    public static DriverManager getInstance(){
        if (INTANCE == null){
            INTANCE = new DriverManager();
        }
        return INTANCE;
    }
    public WebDriver getDriver(){
        if (driver == null){
            initDriver();
        }
        return driver;
    }
    public void initDriver(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
    }
    public void quitDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }

}
