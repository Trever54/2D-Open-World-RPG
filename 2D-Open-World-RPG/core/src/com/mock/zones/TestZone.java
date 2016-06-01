package com.mock.zones;

import com.badlogic.gdx.math.Vector2;
import com.mock.main.GameStateManager;
import com.mock.states.TopDownState;

public class TestZone extends TopDownState {

    public TestZone(GameStateManager gsm) {
        super(gsm, "testZone.tmx");
        player.setPosition(new Vector2(128, 128));
        
        createChangeState(2, 0, "MAIN_WORLD");
    }
      
}
