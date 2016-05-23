package com.mock.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mock.handlers.GameKeys;
import com.mock.main.Game;

public class ActionHUD {
    
    private final float BUTTON_SIZE = Game.V_WIDTH / 12;
    
    public static Sprite actionButton;

    public ActionHUD() {
        actionButton = new Sprite(new Texture("HUD/action_button.png"));
        actionButton.setSize(BUTTON_SIZE, BUTTON_SIZE);
        actionButton.setPosition(Game.V_WIDTH - BUTTON_SIZE - 25, 25);
    }
    
    public void update(float dt) {
        
        if (GameKeys.isPressed(GameKeys.SPACE)) {
            System.out.println("TEST");
        }
        
        /*
        if (GameKeys.isDown(GameKeys.SPACE)) {
            System.out.println("PERFORM ACTION");
        }
        */
        
    }
    
    public void render(SpriteBatch hudSB) {
        hudSB.begin();
        hudSB.draw(actionButton, actionButton.getX(), actionButton.getY(), actionButton.getWidth(), actionButton.getHeight());
        hudSB.end();
    }
    
    public void dispose() {
        actionButton.getTexture().dispose();
    }
    
    
}
