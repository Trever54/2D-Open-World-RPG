package com.mock.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mock.entities.Player;
import com.mock.handlers.GameInputProcessor;
import com.mock.handlers.GameKeys;
import com.mock.handlers.GameStateManager;
import com.mock.handlers.MyContactListener;
import com.mock.handlers.TiledMapHandler;

public class Game extends ApplicationAdapter {
    
    public static final String TITLE = "2D-Open-World-RPG";
    public static final float STEP = 1 / 60f;
    public static int V_WIDTH;
    public static int V_HEIGHT;
    private float accum;
	private SpriteBatch sb;
	public static OrthographicCamera cam;
	private GameStateManager gsm;
	private FPSLogger FPS;
	
	@Override
	public void create() {
	    V_WIDTH = Gdx.graphics.getWidth();
	    V_HEIGHT = Gdx.graphics.getHeight();
	    Gdx.input.setInputProcessor(new GameInputProcessor());
	    FPS = new FPSLogger();
		sb = new SpriteBatch();
		cam = new OrthographicCamera(V_WIDTH, V_HEIGHT);
		gsm = new GameStateManager(this);
	}

	@Override
	public void render() {
	    FPS.log();
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
