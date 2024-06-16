package com.pao.game.constants;

import java.util.HashMap;
import java.util.Map;

public class DrawingConstants {
    static Map<String, Float> mapFloat = new HashMap<>();
    static ConfigLoader configLoader = new ConfigLoader("assets/objectDrawing.properties");
    public static float getFloatConstant(String name)
    {
        if(!mapFloat.containsKey(name))
        {
            mapFloat.put(name,configLoader.getFloatProperty(name));
        }
        return mapFloat.get(name);
    }
}
