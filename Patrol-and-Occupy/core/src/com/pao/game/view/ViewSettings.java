package com.pao.game.view;

import com.badlogic.gdx.Input;
import com.pao.game.model.ModelPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class ViewSettings {
    private static List<Integer> player1Buttons = new ArrayList<>(Arrays.asList(Input.Keys.UP,Input.Keys.DOWN,Input.Keys.LEFT,Input.Keys.RIGHT,Input.Keys.CONTROL_RIGHT,Input.Keys.SLASH));
    private static List<Integer> player2Buttons = new ArrayList<>(Arrays.asList(Input.Keys.W,Input.Keys.S,Input.Keys.A,Input.Keys.D,Input.Keys.SPACE,Input.Keys.E));
    private static int player1Button =Input.Keys.W;
    private static int player2Button =Input.Keys.UP;
    private static int player3Button =Input.Keys.T;
    private static int player4Button =Input.Keys.O;
    public static List<Integer> getButtons(ModelPlayer player)
    {
        if(player == ModelPlayer.Player1) return player1Buttons;
        if(player == ModelPlayer.Player2) return player2Buttons;
        return null;
    }
    public static int getButton(ModelPlayer player)
    {
        if(player == ModelPlayer.Player1) return player1Button;
        if(player == ModelPlayer.Player2) return player2Button;
        if(player == ModelPlayer.Player3) return player3Button;
        if(player == ModelPlayer.Player4) return player4Button;
        return 0;
    }



}
