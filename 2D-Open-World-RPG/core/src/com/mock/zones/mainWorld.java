package com.mock.zones;

import com.badlogic.gdx.math.Vector2;
import com.mock.main.GameStateManager;
import com.mock.states.TopDownState;

import actions.TextAction;

public class MainWorld extends TopDownState{ 
    
    public MainWorld(GameStateManager gsm) {
        super(gsm, "mainWorld.tmx");
        player.setPosition(new Vector2(1500, 1500));
        
        
        // player.setPosition(new Vector2(150, 150)); // bottom left corner
        
        // createChangeState(24, 24, "MAIN_WORLD"); // creates a change state to the zone noted in the String passed
        
        createTextAction(26, 24, new TextAction("TEST TEXT"));
        
        createChangeState(24, 24, "TEST_ZONE");
        
        
        // set animations
        // set actions   
    }
}
