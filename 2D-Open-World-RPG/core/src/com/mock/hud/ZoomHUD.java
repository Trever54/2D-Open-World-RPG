package com.mock.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mock.handlers.GameKeys;
import com.mock.main.Game;

public class ZoomHUD {
    
    private final float BUTTON_SIZE = Game.V_WIDTH / 24;
    
    public static Sprite plusButton;
    public static Sprite minusButton;


    public ZoomHUD() {
        plusButton = new Sprite(new Texture("HUD/plus_button.png"));
        minusButton = new Sprite(new Texture("HUD/minus_button.png"));
        plusButton.setSize(BUTTON_SIZE, BUTTON_SIZE);
        minusButton.setSize(BUTTON_SIZE, BUTTON_SIZE);
        plusButton.setPosition(Game.V_WIDTH - (BUTTON_SIZE * 8) - 25, 25);
        minusButton.setPosition(Game.V_WIDTH - (BUTTON_SIZE * 6) - 25, 25);
    }
    
    public void update(float dt) {   
        if (GameKeys.isDown(GameKeys.ZOOM_IN) && Game.ZOOM > 0.5f) {
            Game.ZOOM -= 0.01f;
        } 
        if (GameKeys.isDown(GameKeys.ZOOM_OUT) && Game.ZOOM < 2.5f) {
            Game.ZOOM += 0.01f;
        }
    }
    
    public void render(SpriteBatch hudSB) {
        hudSB.begin();
        hudSB.draw(plusButton, plusButton.getX(), plusButton.getY(), plusButton.getWidth(), plusButton.getHeight());
        hudSB.draw(minusButton, minusButton.getX(), minusButton.getY(), minusButton.getWidth(), minusButton.getHeight());
        hudSB.end();
    }
    
    public void dispose() {
        plusButton.getTexture().dispose();
        minusButton.getTexture().dispose();
    }
    
    
}
