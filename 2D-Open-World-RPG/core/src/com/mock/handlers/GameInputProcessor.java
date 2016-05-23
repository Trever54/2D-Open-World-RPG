package com.mock.handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.mock.hud.ActionHUD;
import com.mock.hud.DirectionHUD;
import com.mock.main.Game;

public class GameInputProcessor implements InputProcessor {
    
    //----------------------------------------------------------------
    //Android Input Below
    //----------------------------------------------------------------
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenY = Game.V_HEIGHT - screenY; // repositions y to have a 0 value at the bottom left corner
        if (DirectionHUD.upButton.getBoundingRectangle().contains(screenX, screenY) && pointer == 0) {
            GameKeys.setKey(GameKeys.UP, true);
        }
        if (DirectionHUD.downButton.getBoundingRectangle().contains(screenX, screenY) && pointer == 0) {
            GameKeys.setKey(GameKeys.DOWN, true);
        }
        if (DirectionHUD.leftButton.getBoundingRectangle().contains(screenX, screenY) && pointer == 0) {
            GameKeys.setKey(GameKeys.LEFT, true);
        }
        if (DirectionHUD.rightButton.getBoundingRectangle().contains(screenX, screenY) && pointer == 0) {
            GameKeys.setKey(GameKeys.RIGHT, true);
        }
        if (ActionHUD.actionButton.getBoundingRectangle().contains(screenX, screenY)) {
            GameKeys.setKey(GameKeys.SPACE, true);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { 
        if (pointer == 0) {
            GameKeys.setKey(GameKeys.UP, false);
            GameKeys.setKey(GameKeys.DOWN, false);
            GameKeys.setKey(GameKeys.LEFT, false);
            GameKeys.setKey(GameKeys.RIGHT, false);
        }
        GameKeys.setKey(GameKeys.SPACE, false); // TODO: Make the input more user-friendly
        return true; 
    }
     
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        screenY = Game.V_HEIGHT - screenY; // repositions y to have a 0 value at the bottom left corner
        if (!DirectionHUD.upButton.getBoundingRectangle().contains(screenX, screenY) && pointer == 0) {
            GameKeys.setKey(GameKeys.UP, false);
        }
        if (!DirectionHUD.downButton.getBoundingRectangle().contains(screenX, screenY) && pointer == 0) {
            GameKeys.setKey(GameKeys.DOWN, false);
        }
        if (!DirectionHUD.leftButton.getBoundingRectangle().contains(screenX, screenY) && pointer == 0) {
            GameKeys.setKey(GameKeys.LEFT, false);
        }
        if (!DirectionHUD.rightButton.getBoundingRectangle().contains(screenX, screenY) && pointer == 0) {
            GameKeys.setKey(GameKeys.RIGHT, false);
        }
        return true;
    }

    //----------------------------------------------------------------
    // Computer Input Below
    //----------------------------------------------------------------
    @Override
    public boolean keyDown(int keycode) { 
        if (keycode == Keys.UP) {
            GameKeys.setKey(GameKeys.UP, true);
        }
        if (keycode == Keys.DOWN) {
            GameKeys.setKey(GameKeys.DOWN, true);
        }
        if (keycode == Keys.LEFT) {
            GameKeys.setKey(GameKeys.LEFT, true);
        }
        if (keycode == Keys.RIGHT) {
            GameKeys.setKey(GameKeys.RIGHT, true);
        }
        if (keycode == Keys.SPACE) {
            GameKeys.setKey(GameKeys.SPACE, true);
        }
        return true;
    }
    
    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.UP) {
            GameKeys.setKey(GameKeys.UP, false);
        }
        if (keycode == Keys.DOWN) {
            GameKeys.setKey(GameKeys.DOWN, false);
        }
        if (keycode == Keys.LEFT) {
            GameKeys.setKey(GameKeys.LEFT, false);
        }
        if (keycode == Keys.RIGHT) {
            GameKeys.setKey(GameKeys.RIGHT, false);
        }
        if (keycode == Keys.SPACE) {
            GameKeys.setKey(GameKeys.SPACE, false);
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
