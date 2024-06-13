package com.kursach.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestPropManager {

    private final Properties properties = new Properties();
    private static TestPropManager INTANCE = null;

    private TestPropManager(){
        loadApplicationProperties();
        loadCustomProperties();
    }

    public static TestPropManager getInstance(){
        if (INTANCE == null){
            INTANCE = new TestPropManager();
        }
        return INTANCE;
    }

    public void  loadApplicationProperties(){
        try{
            properties.load(new FileInputStream(
                    new File("src/main/resources/" +
                            System.getProperty("propFile","Application") + ".properties")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadCustomProperties() {
        properties.forEach((key,value) -> System.getProperties()
                .forEach((customUserKey, custorUserValues) -> {
                    if (key.toString().equals(customUserKey.toString())&&
                        !value.toString().equals(custorUserValues.toString())){
                        properties.setProperty(key.toString(), custorUserValues.toString());
                    }
                })
        );
    }
    public String getProperty(String key, String defaultValues){
        return properties.getProperty(key, defaultValues);
    }
    public String getProperty(String key){
        return properties.getProperty(key);
    }

}
