package com.mock.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.mock.handlers.ContactHandler;
import com.mock.main.Game;

public class TextHUD {
    
    private Sprite textPanel;
    private final float PANEL_WIDTH = Game.V_WIDTH;
    private final float PANEL_HEIGHT = Game.V_HEIGHT / 4;
    
    public TextHUD() {
        this.textPanel = new Sprite(new Texture("HUD/text_panel.png"));
    }
    
    public void update(float dt) {
        String text = ContactHandler.textActions.peek().getText();
    }
    
    public void render(SpriteBatch hudSB) {
        hudSB.begin();
        hudSB.draw(textPanel, 0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        hudSB.end();
    }
    
    public void dispose() {
        
    }
    
}
