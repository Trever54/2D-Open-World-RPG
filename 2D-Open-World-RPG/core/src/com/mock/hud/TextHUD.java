package com.mock.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mock.handlers.ContactHandler;
import com.mock.input.TouchInput;
import com.mock.main.Game;
import com.mock.states.TopDownState;

public class TextHUD {
     
    private final float PANEL_WIDTH = Game.V_WIDTH;
    private final float PANEL_HEIGHT = Game.V_HEIGHT / 4;
    
    public static Sprite textPanel;
    private BitmapFont font = new BitmapFont();
    
    public TextHUD() {
        this.textPanel = new Sprite(new Texture("HUD/text_panel.png"));
        this.textPanel.setPosition(0, 0);
        this.textPanel.setSize(PANEL_WIDTH, PANEL_HEIGHT);
    }
    
    public void update(float dt) {
        String text = ContactHandler.textActions.peek().getText();
        if (TouchInput.isPressed(textPanel)) {
            ContactHandler.textActions.pop().dispose();
            TopDownState.freezePlayer = false;
            HUDManager.textMode = false;
        } 
    }
    
    public void render(SpriteBatch hudSB) {
        hudSB.begin();
        hudSB.draw(textPanel, textPanel.getX(), textPanel.getY(), textPanel.getWidth(), textPanel.getHeight());
        font.draw(hudSB, "TEST", 20, PANEL_HEIGHT - 20, 50, 50, true);
        hudSB.end();
    }
    
    public void dispose() {
        font.dispose();
        textPanel.getTexture().dispose();
    }
    
}
