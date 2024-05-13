package com.pao.game.viewmodel;

import java.util.ArrayList;
import java.util.List;

public enum MyColor {
    R,G,B,Y;
    public static List<MyColor> getColorList(int n)
    {
        List<MyColor> colors = new ArrayList<>();
        if(n>=1)colors.add(G);
        if(n>=2)colors.add(Y);
        if(n>=3)colors.add(R);
        if(n>=4)colors.add(B);
        return colors;
    }
}