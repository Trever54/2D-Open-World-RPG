package com.mock.entities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mock.handlers.GameKeys;
import com.mock.main.Game;

public class Player extends Entity {
    
    private int speed;

    public Player(Sprite sprite, float posX, float posY, float width,
            float height) {
        super(sprite, posX, posY, width, height);
        this.speed = 2;
    }
    
    public void render(SpriteBatch sb) {
        super.render(sb);
    }
    
    public void update() {
        if (GameKeys.isDown(GameKeys.UP)) {
            posY += speed;
            Game.cam.translate(new Vector2(0, speed));
        }
        if (GameKeys.isDown(GameKeys.DOWN)) {
            posY -= speed;
            Game.cam.translate(new Vector2(0, -speed));
        }
        if (GameKeys.isDown(GameKeys.LEFT)) {
            posX -= speed;
            Game.cam.translate(new Vector2(-speed, 0));
        }
        if (GameKeys.isDown(GameKeys.RIGHT)) {
            posX += speed;
            Game.cam.translate(new Vector2(speed, 0));
        }
    }
}
