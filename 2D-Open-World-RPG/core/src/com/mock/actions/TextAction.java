package com.mock.actions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TextAction {
   
    private String text;
    
    public TextAction(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
    
    public String toString() {
        return "TEXT_ACTION";
    }
    
    
    public void dispose() {
        
    }
    
}
