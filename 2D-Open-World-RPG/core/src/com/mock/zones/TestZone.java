package com.mock.zones;

import com.badlogic.gdx.math.Vector2;
import com.mock.main.GameStateManager;
import com.mock.states.TopDownState;

public class TestZone extends TopDownState {

    public TestZone(GameStateManager gsm) {
        super(gsm, "testZone.tmx");
        // setMusic("track03.mp3");
        player.setPosition(new Vector2(470, 160));
        
        createChangeState(7, 0, "MAIN_WORLD");
    }
      
}
