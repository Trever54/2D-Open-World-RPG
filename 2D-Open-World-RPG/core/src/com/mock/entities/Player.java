package com.mock.entities;

import static com.mock.main.Game.BIT_SIZE;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mock.input.GameKeys;

public class Player extends AnimatedEntity {
    
    private Animation downAnimation;
    private Animation upAnimation;
    private Animation leftAnimation;
    private Animation rightAnimation;
    private Animation downStanding;
    private Animation upStanding;
    private Animation leftStanding;
    private Animation rightStanding;
    private Animation currentAnimation;
    private boolean moving;
    
    private float speed;
    private float dx;
    private float dy;
    
    public Player(Body body, Texture spriteSheet) {
        super(body, spriteSheet, BIT_SIZE, BIT_SIZE);
        this.speed = 4f;
        this.dx = 0;
        this.dy = 0;
        // ANIMATION STUFF
        TextureRegion[][] tmpFrames = TextureRegion.split(spriteSheet, BIT_SIZE, BIT_SIZE);
        TextureRegion[] downFrames = new TextureRegion[4];
        TextureRegion[] upFrames = new TextureRegion[4];
        TextureRegion[] leftFrames = new TextureRegion[4];
        TextureRegion[] rightFrames = new TextureRegion[4];
        downFrames[0] = tmpFrames[0][0];
        downFrames[1] = tmpFrames[0][1];
        downFrames[2] = tmpFrames[0][2];
        downFrames[3] = tmpFrames[0][1];
        leftFrames[0] = tmpFrames[1][0];
        leftFrames[1] = tmpFrames[1][1];
        leftFrames[2] = tmpFrames[1][2];
        leftFrames[3] = tmpFrames[1][1];
        rightFrames[0] = tmpFrames[2][0];
        rightFrames[1] = tmpFrames[2][1];
        rightFrames[2] = tmpFrames[2][2];
        rightFrames[3] = tmpFrames[2][1];
        upFrames[0] = tmpFrames[3][0];
        upFrames[1] = tmpFrames[3][1];
        upFrames[2] = tmpFrames[3][2];
        upFrames[3] = tmpFrames[3][1];
        downAnimation = new Animation(1f/3f, downFrames);
        upAnimation = new Animation(1f/3f, upFrames);
        leftAnimation = new Animation(1f/3f, leftFrames);
        rightAnimation = new Animation(1f/3f, rightFrames);
        downStanding = new Animation(1f, tmpFrames[0][1]);
        upStanding = new Animation(1f, tmpFrames[3][1]);
        leftStanding = new Animation(1f, tmpFrames[1][1]);
        rightStanding = new Animation(1f, tmpFrames[2][1]);
        currentAnimation = downStanding;
        moving = false;
    }
    
    public void render(SpriteBatch sb) {
        super.render(sb, currentAnimation);
    }
    
    public void update(float dt) {
        super.update(dt);
        if (GameKeys.isDown(GameKeys.LEFT) && !moving) { 
            dx = -speed; 
            currentAnimation = leftAnimation;
            moving = true;
        } 
        if (GameKeys.isDown(GameKeys.RIGHT) && !moving) { 
            dx = speed;
            currentAnimation = rightAnimation;
            moving = true;
        }
        if (GameKeys.isUp(GameKeys.RIGHT) && currentAnimation.equals(rightAnimation)) { 
            dx = 0;
            currentAnimation = rightStanding;
            moving = false;
        }
        if (GameKeys.isUp(GameKeys.LEFT) && currentAnimation.equals(leftAnimation)) { 
            dx = 0;
            currentAnimation = leftStanding;
            moving = false;
        }
        if (GameKeys.isDown(GameKeys.UP) && !moving) { 
            dy = speed; 
            currentAnimation = upAnimation;
            moving = true;
        } 
        if (GameKeys.isDown(GameKeys.DOWN) && !moving) { 
            dy = -speed;
            currentAnimation = downAnimation;
            moving = true;
        } 
        if (GameKeys.isUp(GameKeys.DOWN) && currentAnimation.equals(downAnimation)) { 
            dy = 0; 
            currentAnimation = downStanding;
            moving = false;
        }
        if (GameKeys.isUp(GameKeys.UP) && currentAnimation.equals(upAnimation)) { 
            dy = 0; 
            currentAnimation = upStanding;
            moving = false;
        }
        body.setLinearVelocity(new Vector2(dx, dy));
    }
}
