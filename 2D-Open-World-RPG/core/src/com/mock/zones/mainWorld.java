package com.mock.zones;

import com.badlogic.gdx.math.Vector2;
import com.mock.main.GameStateManager;
import com.mock.states.TopDownState;

public class MainWorld extends TopDownState{ 
    
    public MainWorld(GameStateManager gsm) {
        super(gsm, "mainWorld.tmx");
        player.setPosition(new Vector2(1500, 1500));
        
        createChangeState(24, 24, "MAIN_WORLD"); // was "TEST_ZONE"
        
        
        // set animations
        // set actions   
    }
}
