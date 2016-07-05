package com.mock.zones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.mock.actions.TextAction;
import com.mock.main.GameStateManager;
import com.mock.states.TopDownState;

public class MainWorld extends TopDownState{ 
    
    private Music music;
    
    public MainWorld(GameStateManager gsm) {
        super(gsm, "PracticeMap.tmx");
        // setMusic("track01.mp3");
        player.setPosition(new Vector2(2800, 1500));    // 1800, 1800 for PracticeMap 
        
        createTextAction(42, 31, new TextAction("This is a big door!"));
        createTextAction(43, 31, new TextAction("This is a big door!"));
        createTextAction(44, 31, new TextAction("This is a big door!"));
        createTextAction(45, 31, new TextAction("This is a big door!"));
        createTextAction(46, 31, new TextAction("This is a big door!"));
        createTextAction(47, 31, new TextAction("This is a big door!"));
        
        createChangeState(44, 25, "TEST_ZONE");
        
         
    }
}
