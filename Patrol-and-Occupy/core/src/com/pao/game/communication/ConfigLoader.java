package com.pao.game.communication;

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
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public int getIntProperty(String key) {
        String value = properties.getProperty(key);
        return value != null ? Integer.parseInt(value) : 0;
    }

    public float getFloatProperty(String key) {
        String value = properties.getProperty(key);
        return value != null ? Float.parseFloat(value) : 0.0f;
    }
}