package com.mock.zones;

import com.badlogic.gdx.math.Vector2;
import com.mock.handlers.GameStateManager;
import com.mock.states.topDownState;

public class mainWorld extends topDownState{ 
    
    public mainWorld(GameStateManager gsm) {
        super(gsm, "collisionTest.tmx");
        player.setPosition(new Vector2(300, 300));
        
        // set animations
        // set actions
        
        
    }
    
    
    
}
