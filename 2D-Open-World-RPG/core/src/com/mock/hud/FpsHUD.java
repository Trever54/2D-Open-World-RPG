package com.mock.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mock.handlers.ContactHandler;
import com.mock.input.GameKeys;
import com.mock.input.TouchInput;
import com.mock.main.Game;
import com.mock.states.TopDownState;

public class FpsHUD {

    private BitmapFont font = new BitmapFont();
    
    private String text;
    
    public FpsHUD() {
        text = "";
    }
    
    public void update(float dt) {
        text = Game.FPS_TEXT;
    }
    
    public void render(SpriteBatch hudSB) {
        hudSB.begin();
        font.draw(hudSB, text, Game.V_WIDTH - 100, Game.V_HEIGHT, 100, 50, false);
        hudSB.end();
    }
    
    public void dispose() {
        font.dispose();
    }
    
}
