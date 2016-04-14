package com.mock.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mock.handlers.GameKeys;

public class Player extends Entity {
    
    private int speed;
    private int dx;
    private int dy;
    
    public Player(Body body, Sprite sprite, float posX, float posY, float width,
            float height) {
        super(body, sprite, posX, posY, width, height);
        this.speed = 100;
        this.dx = 0;
        this.dy = 0;
    }
    
    public void render(SpriteBatch sb) {
        super.render(sb);
    }
    
    public void update(float dt) {
        
        if (GameKeys.isDown(GameKeys.UP)) { dy = speed; } 
        else if (GameKeys.isDown(GameKeys.DOWN)) { dy = -speed; } 
        else { dy = 0; }
        
        if (GameKeys.isDown(GameKeys.LEFT)) { dx = -speed; } 
        else if (GameKeys.isDown(GameKeys.RIGHT)) { dx = speed; } 
        else { dx = 0; }
        
        body.setLinearVelocity(new Vector2(dx, dy));
        super.update(dt);
    }
}
