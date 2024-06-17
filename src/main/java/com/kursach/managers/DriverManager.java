package com.kursach.managers;

import com.kursach.utils.PropsConst;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager {

    private final TestPropManager testPropManager = TestPropManager.getInstance();

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
//    public void initDriver(){
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//    }
    private void initDriver() {
        System.setProperty("webdriver.edge.driver", testPropManager.getProperty(PropsConst.PATH_EDGE_DRIVER_WINDOWS));
        driver = new EdgeDriver();

    }

    public void quitDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }

}
