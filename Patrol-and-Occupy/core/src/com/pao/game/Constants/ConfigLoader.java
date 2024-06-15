package com.pao.game.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties = new Properties();

    public ConfigLoader(String path) {
        try (InputStream input = new FileInputStream(path)) {
            properties.load(input);
        } catch (IOException ex) {
            try (InputStream input = new FileInputStream("Patrol-and-Occupy/"+path)) {
                properties.load(input);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public int getIntProperty(String key) {
        String value = properties.getProperty(key);
        if(value == null) System.out.println(key);
        return value != null ? Integer.parseInt(value) : 0;
    }
    public float getFloatProperty(String key) {
        String value = properties.getProperty(key);
        if(value == null) System.out.println(key);
        return value != null ? Float.parseFloat(value) : 0.0f;
    }
}