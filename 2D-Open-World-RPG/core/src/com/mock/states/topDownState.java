package com.mock.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mock.entities.Player;
import com.mock.handlers.GameKeys;
import com.mock.handlers.GameStateManager;
import com.mock.handlers.MyContactListener;
import com.mock.handlers.TiledMapHandler;

public class topDownState extends GameState {
    
    public static boolean debug = false;
    
    private Box2DDebugRenderer b2dr;
    private World world;
    private MyContactListener contactListener;  
    private TiledMapHandler tmh;
    private Player player;

    public topDownState(GameStateManager gsm, String tiledPath) {
        super(gsm);
        b2dr = new Box2DDebugRenderer();
        contactListener = new MyContactListener();
        tmh = new TiledMapHandler(tiledPath);
        world = tmh.getWorld();
        world.setContactListener(contactListener);
        player = createPlayer();   
    }

    public void update(float dt) {
        GameKeys.update();
        world.step(dt, 6, 2);
        player.update(dt);
        cam.position.set(
                player.getPosition().x + player.getWidth() / 2,
                player.getPosition().y + player.getHeight() / 2,
                0);
        cam.update();
    }

    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.setProjectionMatrix(cam.combined);
        
        // render objects
        tmh.renderTerrainLayer(sb, cam);
        tmh.renderCollisionLayer(sb, cam);
        player.render(sb);  
        tmh.renderTopLayer(sb, cam);
        
        // Box2D Debugging stuff
        if (debug) {
            b2dr.render(world, cam.combined);
        }
    }

    public void dispose() {
        world.dispose();
        player.dispose();
    }
    
    private Player createPlayer() {
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape(); 
        bdef.type = BodyType.DynamicBody;
        Body body = world.createBody(bdef);
        shape.setAsBox(16, 16);
        fdef.shape = shape;
        body.createFixture(fdef);
        body.setUserData("player");
        return new Player(body, new Sprite(new Texture("player.png")), 16, 16, 32, 32);
    }
}
