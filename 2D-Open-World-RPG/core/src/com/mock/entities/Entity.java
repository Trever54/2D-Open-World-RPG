package com.mock.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Entity {
    
    protected float posX;
    protected float posY;
    protected float width;
    protected float height;
    protected Sprite sprite;
    
    public Entity(Sprite sprite, float posX, float posY, float width, float height) {
        this.sprite = sprite;
        this.posX = posX;
        this.posY = posY;   
        this.width = width;
        this.height = height;
    }
    
    public void render(SpriteBatch sb) { 
        sb.begin();
        sb.draw(sprite, posX, posY, width, height);
        sb.end();
    }
    
    public void dispose() {
        sprite.getTexture().dispose();
    }
    
    public Vector2 getPosition() {
        return new Vector2(posX, posY);
    }

}
