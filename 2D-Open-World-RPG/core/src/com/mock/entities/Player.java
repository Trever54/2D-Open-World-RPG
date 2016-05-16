package com.mock.entities;

import static com.mock.handlers.B2DVars.PPM;
import static com.mock.main.Game.BIT_SIZE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mock.handlers.GameKeys;

public class Player extends Entity {
    
    private Texture img;
    private Animation downAnimation;
    private Animation upAnimation;
    private Animation leftAnimation;
    private Animation rightAnimation;
    private Animation downStanding;
    private Animation upStanding;
    private Animation leftStanding;
    private Animation rightStanding;
    private Animation currentAnimation;
    private float elapsedTime;
    
    private float speed;
    private float dx;
    private float dy;
    
    public Player(Body body, Sprite sprite) {
        super(body, sprite, BIT_SIZE, BIT_SIZE);
        this.speed = 2f;
        this.dx = 0;
        this.dy = 0;
        // ANIMATION STUFF
        img = new Texture("testPlayerAnimation.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(img, BIT_SIZE, BIT_SIZE);
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
    }
    
    public void render(SpriteBatch sb) {
        // super.render(sb);
        sb.begin();
        sb.draw(currentAnimation.getKeyFrame(elapsedTime, true), 
                body.getPosition().x * PPM - (width / 2), 
                body.getPosition().y * PPM - (height / 2), 
                width, height);
        sb.end();
    }
    
    public void update(float dt) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        
        if (GameKeys.isDown(GameKeys.LEFT)) { 
            dx = -speed; 
            currentAnimation = leftAnimation;
        } 
        else if (GameKeys.isDown(GameKeys.RIGHT)) { 
            dx = speed;
            currentAnimation = rightAnimation;
        }
        else { 
            dx = 0;
        }
        
        if (GameKeys.isDown(GameKeys.UP)) { 
            dy = speed; 
            currentAnimation = upAnimation;
        } 
        else if (GameKeys.isDown(GameKeys.DOWN)) { 
            dy = -speed;
            currentAnimation = downAnimation;
        } 
        else { 
            dy = 0; 
        }
        
        if (GameKeys.isUp(GameKeys.DOWN) && currentAnimation.equals(downAnimation)) {
            currentAnimation = downStanding;
        }
        if (GameKeys.isUp(GameKeys.UP) && currentAnimation.equals(upAnimation)) {
            currentAnimation = upStanding;
        }
        if (GameKeys.isUp(GameKeys.LEFT) && currentAnimation.equals(leftAnimation)) {
            currentAnimation = leftStanding;
        }
        if (GameKeys.isUp(GameKeys.RIGHT) && currentAnimation.equals(rightAnimation)) {
            currentAnimation = rightStanding;
        }
        
        
        body.setLinearVelocity(new Vector2(dx, dy));
    }
}
