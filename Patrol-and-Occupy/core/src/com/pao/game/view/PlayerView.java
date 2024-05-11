package com.pao.game.view;

import com.pao.game.viewmodel.Color;

public class PlayerView {
    Color color;
    int up;
    int down;
    int left;
    int right;
    boolean lastStateUp;
    boolean lastStateDown;
    boolean lastStateLeft;
    boolean lastStateRight;
    public PlayerView(Color color, int up,int down,int left,int right)
    {
        this.color =color;
        this.up=up;
        this.down=down;
        this.left=left;
        this.right=right;
        lastStateDown = false;
        lastStateUp = false;
        lastStateLeft = false;
        lastStateRight = false;
    }
}