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
        // setBackground("clouds.png");
        player.setPosition(new Vector2(1800, 1800));    // 1800, 1800 for PracticeMap 
        
        // createTextAction(26, 24, new TextAction("TEST TEXT"));
        
        // createChangeState(24, 24, "TEST_ZONE");
          
        /*
        // MUSIC NEEDS TO BE IMPLEMENTED ON THE TOPDOWNSTATE LEVEL
        music = Gdx.audio.newMusic(Gdx.files.internal("track01.mp3"));
        music.setLooping(true);
        music.play();
        */
        
        // set animations
        // set actions   
    }
}
