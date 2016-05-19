package com.mock.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mock.main.Game;

public class DirectionHUD {

    private final float BUTTON_SIZE = Game.V_WIDTH / 12;
    
    public static Sprite upButton;
    public static Sprite downButton;
    public static Sprite leftButton;
    public static Sprite rightButton;

    public DirectionHUD() {
        upButton = new Sprite(new Texture("HUD/up_button.png"));
        downButton = new Sprite(new Texture("HUD/down_button.png"));
        leftButton = new Sprite(new Texture("HUD/left_button.png"));
        rightButton = new Sprite(new Texture("HUD/right_button.png"));
        upButton.setSize(BUTTON_SIZE, BUTTON_SIZE);
        downButton.setSize(BUTTON_SIZE, BUTTON_SIZE);
        leftButton.setSize(BUTTON_SIZE, BUTTON_SIZE);
        rightButton.setSize(BUTTON_SIZE, BUTTON_SIZE);
        upButton.setPosition(5 + BUTTON_SIZE, 5 + (BUTTON_SIZE * 2));
        downButton.setPosition(5 + BUTTON_SIZE, 5);
        leftButton.setPosition(5, 5 + BUTTON_SIZE);
        rightButton.setPosition(5 + (BUTTON_SIZE * 2), 5 + BUTTON_SIZE);
    }
    
    public void update(float dt) {
        
    }
    
    public void render(SpriteBatch hudSB) {     
        hudSB.begin();
        hudSB.draw(upButton, upButton.getX(), upButton.getY(), upButton.getWidth(), upButton.getHeight());
        hudSB.draw(downButton, downButton.getX(), downButton.getY(), downButton.getWidth(), downButton.getHeight());
        hudSB.draw(leftButton, leftButton.getX(), leftButton.getY(), leftButton.getWidth(), leftButton.getHeight());
        hudSB.draw(rightButton, rightButton.getX(), rightButton.getY(), rightButton.getWidth(), rightButton.getHeight());
        hudSB.end();
    }
    
    public void dispose() {
        upButton.getTexture().dispose();
        downButton.getTexture().dispose();
        leftButton.getTexture().dispose();
        rightButton.getTexture().dispose();
    }
    
    
}
