package com.mock.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * posX and posY are the top left corner of the sprite?
 * the body position is the center of the sprite?
 * @author Trever Mock
 */
public class Entity {
    
    protected Body body;
    protected Sprite sprite;
    protected float posX;
    protected float posY;
    protected float width;
    protected float height;
    
    public Entity(Body body, Sprite sprite, float posX, float posY, float width, float height) {
        this.body = body;
        this.sprite = sprite;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        body.setTransform(new Vector2(posX, posY), 0);  
    }
    
    public void render(SpriteBatch sb) { 
        sb.begin();
        sb.draw(sprite, posX, posY, width, height);
        sb.end();
    }
    
    public void update(float dt) {
        posX = body.getPosition().x - (width / 2);
        posY = body.getPosition().y - (height / 2);
    }
    
    public void dispose() {
        sprite.getTexture().dispose();
    }
    
    public Vector2 getPosition() {
        return new Vector2(posX, posY);
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public float getHeight() {
        return this.height;
    }

}
