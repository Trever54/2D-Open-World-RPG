package com.mock.entities;

import static com.mock.handlers.B2DVars.PPM;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class AnimatedEntity {

    protected Body body;
    protected Sprite sprite;
    protected float width;
    protected float height;
    
    
    public AnimatedEntity(Body body, Sprite sprite, float width, float height) {
        this.body = body;
        this.sprite = sprite;
        this.width = width;
        this.height = width;
    }
    
    public void render(SpriteBatch sb) { 
        sb.begin();
        sb.draw(sprite, 
                body.getPosition().x * PPM - (width / 2), 
                body.getPosition().y * PPM - (height / 2), 
                width, height);
        sb.end();
    }
    
    public void update(float dt) {
        // ANIMATION HERE
        
        //posX = body.getPosition().x * PPM - (width / 2);
        //posY = body.getPosition().y * PPM - (height / 2);
    }
    
    public void dispose() {
        sprite.getTexture().dispose();
    }
    
    public Body getBody() {
        return this.body;
    }
    
    public Vector2 getPosition() {
        return new Vector2(body.getPosition().x * PPM, body.getPosition().y * PPM );
    }
    
    public void setPosition(Vector2 v) {
        this.body.setTransform(v.x / PPM, v.y / PPM, 0);
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public float getHeight() {
        return this.height;
    }
}
