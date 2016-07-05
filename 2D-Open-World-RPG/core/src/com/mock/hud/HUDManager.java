package com.mock.hud;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mock.main.Game;

public class HUDManager {
    
    public static boolean textMode = false;

    private OrthographicCamera hudCam;
    private SpriteBatch hudSB;
    
    private DirectionHUD directionHUD;
    private ActionHUD actionHUD;
    private ZoomHUD zoomHUD;
    
    private TextHUD textHUD;
    private FpsHUD fpsHUD;
    
    public HUDManager() {
        hudCam = new OrthographicCamera(Game.V_WIDTH, Game.V_HEIGHT);
        hudCam.position.set(Game.V_WIDTH / 2, Game.V_HEIGHT / 2, 0);
        hudSB = new SpriteBatch();
        directionHUD = new DirectionHUD();
        actionHUD = new ActionHUD();
        zoomHUD = new ZoomHUD();
        textHUD = new TextHUD();
        fpsHUD = new FpsHUD();
    }
    
    public void update(float dt) {
        hudCam.update();
        if (!textMode) {
            directionHUD.update(dt);
            actionHUD.update(dt);
            zoomHUD.update(dt);
        } else {
            textHUD.update(dt);
        }
        fpsHUD.update(dt);
    }
    
    public void render() {
        hudSB.setProjectionMatrix(hudCam.combined);
        if (!textMode) {
            directionHUD.render(hudSB); 
            actionHUD.render(hudSB);
            zoomHUD.render(hudSB);
        } else {
            textHUD.render(hudSB);
        }
        fpsHUD.render(hudSB);
    }
    
    public void dispose() {
        directionHUD.dispose();
        actionHUD.dispose();
        zoomHUD.dispose();
        textHUD.dispose();
        fpsHUD.dispose();
    }
    
}
