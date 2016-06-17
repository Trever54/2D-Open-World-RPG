package com.mock.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mock.entities.Player;
import com.mock.input.GameKeys;
import com.mock.input.TouchInput;
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
        if ((TouchInput.containsTouchDown(upButton) || GameKeys.isDown(GameKeys.UP)) && !Player.moving) {
            Player.dy = Player.SPEED;
        }
        if ((TouchInput.containsTouchDown(downButton) || GameKeys.isDown(GameKeys.DOWN)) && !Player.moving) {
            Player.dy = - Player.SPEED;
        }
        if ((TouchInput.containsTouchDown(leftButton) || GameKeys.isDown(GameKeys.LEFT)) && !Player.moving) {
            Player.dx = - Player.SPEED;
        }
        if ((TouchInput.containsTouchDown(rightButton) || GameKeys.isDown(GameKeys.RIGHT)) && !Player.moving) {
            Player.dx = Player.SPEED;
        }
        if (TouchInput.containsTouchDown(upButton) && TouchInput.touchUp) {
            Player.dy = 0;
        }
        if (TouchInput.containsTouchDown(downButton) && TouchInput.touchUp) {
            Player.dy = 0;
        }
        if (TouchInput.containsTouchDown(leftButton) && TouchInput.touchUp) {
            Player.dx = 0;
        }
        if (TouchInput.containsTouchDown(rightButton) && TouchInput.touchUp) {
            Player.dx = 0;
        }   
        if (GameKeys.isUp(GameKeys.UP) && Player.facingUp && Player.moving && !TouchInput.touchDown) {
            GameKeys.setKey(GameKeys.UP, false);
            Player.dx = 0;
            Player.dy = 0;
        }
        if (GameKeys.isUp(GameKeys.DOWN) && Player.facingDown && Player.moving && !TouchInput.touchDown) {
            GameKeys.setKey(GameKeys.DOWN, false);
            Player.dx = 0;
            Player.dy = 0;
        }
        if (GameKeys.isUp(GameKeys.LEFT) && Player.facingLeft && Player.moving && !TouchInput.touchDown) {
            GameKeys.setKey(GameKeys.LEFT, false);
            Player.dx = 0;
            Player.dy = 0;
        }
        if (GameKeys.isUp(GameKeys.RIGHT) && Player.facingRight && Player.moving && !TouchInput.touchDown) {
            GameKeys.setKey(GameKeys.RIGHT, false);
            Player.dx = 0;
            Player.dy = 0;
        }
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
