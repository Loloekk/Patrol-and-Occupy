package com.pao.game.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MyColor {
    Y,G,B,R;
    public static List<MyColor> getColorList(int n)
    {
        return Arrays.stream(MyColor.values()).limit(n).collect(Collectors.toList());
    }
}