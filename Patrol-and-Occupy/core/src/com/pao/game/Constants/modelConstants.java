package com.pao.game.Constants;

import java.util.HashMap;
import java.util.Map;

public class modelConstants {

    static Map<String, Float> map = new HashMap<>();
    static ConfigLoader configLoader = new ConfigLoader("assets/modelConstants.properties");
    public static float getConstant(String name)
    {
        if(!map.containsKey(name))
        {
            map.put(name,configLoader.getFloatProperty(name));
        }
        return map.get(name);
    }

}
