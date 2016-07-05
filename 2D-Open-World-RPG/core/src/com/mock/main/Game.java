package com.mock.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mock.input.GameInputProcessor;

public class Game extends ApplicationAdapter {
    
    public static final String TITLE = "2D-Open-World-RPG";
    public static final float STEP = 1 / 60f;
    public static final int BIT_SIZE = 64;
    public static float ZOOM = 1f;
    public static int V_WIDTH;
    public static int V_HEIGHT;
    public static String FPS_TEXT;
    private float accum;
    private OrthographicCamera cam;
	private SpriteBatch sb;
	private GameStateManager gsm;
	private FPSLogger FPS;
	
	@Override
	public void create() {
	    V_WIDTH = Gdx.graphics.getWidth();
	    V_HEIGHT = Gdx.graphics.getHeight();
	    Gdx.input.setInputProcessor(new GameInputProcessor());
	    FPS = new FPSLogger();
	    FPS_TEXT = "";
		sb = new SpriteBatch();
		cam = new OrthographicCamera(V_WIDTH / ZOOM, V_HEIGHT / ZOOM);
		gsm = new GameStateManager(this);
	}

	@Override
	public void render() {
	    FPS.log();
	    FPS_TEXT = "FPS: ?";   // currently doesn't work
	    accum += Gdx.graphics.getDeltaTime();
	    while (accum >= STEP) {
	        accum -= STEP;
	        gsm.update(STEP);
	        gsm.render();
	    }
	}
	
	@Override
	public void dispose() {
	    sb.dispose();
	}
	
	public SpriteBatch getSpriteBatch() { return sb; }
	public OrthographicCamera getCamera() { return cam; }
}
