package com.pao.game.Constants;

import com.pao.game.Constants.ConfigLoader;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    static Map<String, Float> map = new HashMap<>();
    static ConfigLoader configLoader = new ConfigLoader("assets/constants.properties");
    public static float getConstant(String name)
    {
        if(!map.containsKey(name))
        {
            map.put(name,configLoader.getFloatProperty(name));
        }
        return map.get(name);
    }

}
