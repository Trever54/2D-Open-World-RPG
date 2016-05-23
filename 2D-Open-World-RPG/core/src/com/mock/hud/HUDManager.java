package com.mock.hud;

import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mock.main.Game;

public class HUDManager {

    private OrthographicCamera hudCam;
    private SpriteBatch hudSB;
    
    private DirectionHUD directionHUD;
    private ActionHUD actionHUD;
    
    public HUDManager() {
        hudCam = new OrthographicCamera(Game.V_WIDTH, Game.V_HEIGHT);
        hudCam.position.set(Game.V_WIDTH / 2, Game.V_HEIGHT / 2, 0);
        hudSB = new SpriteBatch();
        directionHUD = new DirectionHUD();
        actionHUD = new ActionHUD();
    }
    
    public void update(float dt) {
        hudCam.update();
        directionHUD.update(dt);
        actionHUD.update(dt);
    }
    
    public void render() {
        hudSB.setProjectionMatrix(hudCam.combined);
        directionHUD.render(hudSB); 
        actionHUD.render(hudSB);
    }
    
    public void dispose() {
        directionHUD.dispose();
        actionHUD.dispose();
    }
    
}
