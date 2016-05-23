package com.mock.zones;

import com.badlogic.gdx.math.Vector2;
import com.mock.handlers.GameStateManager;
import com.mock.states.TopDownState;

public class MainWorld extends TopDownState{ 
    
    public MainWorld(GameStateManager gsm) {
        super(gsm, "mainWorld.tmx");
        player.setPosition(new Vector2(300, 300));
        
        // (19, 8) - change state
        
        // createChangeState(19, 8, "INSIDE_TP");
        
        
        
        
        // set animations
        // set actions   
    }
}
