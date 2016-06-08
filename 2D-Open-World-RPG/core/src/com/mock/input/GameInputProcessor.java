package com.mock.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.mock.main.Game;

public class GameInputProcessor implements InputProcessor {
    
    //----------------------------------------------------------------
    //Android Input Below
    //----------------------------------------------------------------
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenY = Game.V_HEIGHT - screenY;
        TouchInput.setPositionTouchDown(screenX, screenY, pointer);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { 
        screenY = Game.V_HEIGHT - screenY;
        TouchInput.setPositionTouchUp(screenX, screenY, pointer);
        return true; 
    }
     
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        screenY = Game.V_HEIGHT - screenY;
        TouchInput.setPositionDragged(screenX, screenY, pointer);
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
