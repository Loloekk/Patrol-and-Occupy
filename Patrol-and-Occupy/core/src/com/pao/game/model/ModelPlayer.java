package com.pao.game.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ModelPlayer {
    Player1,Player2,Player3,Player4;
    public static List<ModelPlayer> getColorList(int n)
    {
        return Arrays.stream(ModelPlayer.values()).limit(n).collect(Collectors.toList());
    }
}