package com.mock.zones;

import com.mock.handlers.GameStateManager;
import com.mock.states.topDownState;

public class mainWorld extends topDownState{   
    public mainWorld(GameStateManager gsm) {
        super(gsm, "testWorld.tmx");
    }
}
