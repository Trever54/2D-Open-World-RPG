package com.mock.zones;

import static com.mock.handlers.B2DVars.PPM;
import static com.mock.main.Game.BIT_SIZE;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mock.handlers.GameStateManager;
import com.mock.states.TopDownState;

public class MainWorld extends TopDownState{ 
    
    public MainWorld(GameStateManager gsm) {
        super(gsm, "island.tmx");
        player.setPosition(new Vector2(300, 300));
        
        // (19, 8) - change state
        
        createChangeState(19, 8, "INSIDE_TP");
        
        
        
        
        // set animations
        // set actions   
    }
}
