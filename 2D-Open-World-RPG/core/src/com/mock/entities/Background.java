package com.mock.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mock.main.Game;

public class Background {

    private Sprite image;
    
    public Background(String path) {
        image = new Sprite(new Texture(path));
    }
    
    public void update(float dt) {
        
    }
    
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(image, -6400, -6400, 12800, 12800);
        sb.end();
    }
    
    public void dispose() {
        image.getTexture().dispose();
    }
    
}
