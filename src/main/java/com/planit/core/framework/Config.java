package com.planit.core.framework;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
    private static Properties props = new Properties();

    static {

        try {
            FileInputStream propertyFile = new FileInputStream("config.properties");
            props.load(propertyFile);
            propertyFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public synchronized  static String getProperty(String propertyName){
        String key = props.getProperty(propertyName);
        if(key == null) throw new IllegalArgumentException(propertyName +" not found or value is null");
     return key;
    }
}
