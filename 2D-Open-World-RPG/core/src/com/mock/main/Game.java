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
import com.mock.entities.Player;
import com.mock.handlers.GameInputProcessor;
import com.mock.handlers.GameKeys;

public class Game extends ApplicationAdapter {
    
    public static final String TITLE = "2D-Open-World-RPG";
    public static final float STEP = 1 / 60f;
    public static int V_WIDTH;
    public static int V_HEIGHT;
    public float accum;  
	private SpriteBatch sb;
	
	public static OrthographicCamera cam;
	
	private Player player;
	
	private Sprite world;
	
	private FPSLogger FPS;
	

	@Override
	public void create() {
	    V_WIDTH = Gdx.graphics.getWidth();
	    V_HEIGHT = Gdx.graphics.getHeight();
	    Gdx.input.setInputProcessor(new GameInputProcessor());
	    FPS = new FPSLogger();
		sb = new SpriteBatch();
		cam = new OrthographicCamera(V_WIDTH, V_HEIGHT);
		
		world = new Sprite(new Texture("testWorld.png"));
		world.setPosition(- V_WIDTH / 2, - V_HEIGHT / 2);
		
		player = new Player(new Sprite(new Texture("player.png")), 0, 0, 25, 25);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		FPS.log(); // Prints the current FPS
		sb.setProjectionMatrix(cam.combined); // set up camera
		update(); // updates everything in the game
		
		// testing batch
		sb.begin();
		world.draw(sb);
		sb.end();
		
		// render objects
		player.render(sb);	
	}
	
	public void update() {
	    player.update();   // also updates camera currently
	    GameKeys.update();
        cam.update();
	}
	
	@Override
	public void dispose() {
	    world.getTexture().dispose();
	    sb.dispose();
	    player.dispose();
	}
}
