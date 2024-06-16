package com.pao.game.constants;

import java.util.HashMap;
import java.util.Map;

public class ViewConstants {
    static Map<String, Float> mapFloat = new HashMap<>();
    static Map<String, Integer> mapInt = new HashMap<>();
    static Map<String, String> mapString = new HashMap<>();
    static ConfigLoader configLoader = new ConfigLoader("assets/viewConstants.properties");
    public static float getFloatConstant(String name)
    {
        if(!mapFloat.containsKey(name))
        {
            mapFloat.put(name,configLoader.getFloatProperty(name));
        }
        return mapFloat.get(name);
    }
    public static int getIntConstant(String name)
    {
        if(!mapInt.containsKey(name))
        {
            mapInt.put(name,configLoader.getIntProperty(name));
        }
        return mapInt.get(name);
    }
    public static String getConstant(String name)
    {
        if(!mapString.containsKey(name))
        {
            mapString.put(name,configLoader.getProperty(name));
        }
        return mapString.get(name);
    }
}
