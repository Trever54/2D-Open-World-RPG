package com.mock.entities;

import static com.mock.main.Game.BIT_SIZE;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mock.handlers.GameKeys;

public class Player extends Entity {
    
    private float speed;
    private float dx;
    private float dy;
    
    public Player(Body body, Sprite sprite) {
        super(body, sprite, BIT_SIZE, BIT_SIZE);
        this.speed = 3f;
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
    }
}
