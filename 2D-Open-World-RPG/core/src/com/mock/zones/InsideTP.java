package com.mock.zones;

import com.badlogic.gdx.math.Vector2;
import com.mock.handlers.GameStateManager;
import com.mock.states.TopDownState;

public class InsideTP extends TopDownState {

    public InsideTP(GameStateManager gsm) {
        super(gsm, "collisionTest.tmx");
        player.setPosition(new Vector2(300, 300));
        
         createChangeState(1, 0, "MAIN_WORLD");
    }
    
    
}
