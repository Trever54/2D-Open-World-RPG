package com.mock.states;

import static com.mock.handlers.B2DVars.PPM;
import static com.mock.main.Game.BIT_SIZE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
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
import com.mock.hud.HUDManager;
import com.mock.main.Game;

public class TopDownState extends GameState {
    
    public static boolean debug = false;
    
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera b2dCam;
    private World world;
    private MyContactListener contactListener;
    private TiledMapHandler tmh;
    private HUDManager hudManager;
    public Player player;

    public TopDownState(GameStateManager gsm, String tiledPath) {
        super(gsm);
        b2dr = new Box2DDebugRenderer();
        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false, (Game.V_WIDTH / PPM) / Game.ZOOM, (Game.V_HEIGHT / PPM) / Game.ZOOM);
        contactListener = new MyContactListener();
        tmh = new TiledMapHandler(tiledPath);
        world = tmh.getWorld();
        world.setContactListener(contactListener);
        player = createPlayer();
        hudManager = new HUDManager();
    }

    public void update(float dt) {
        GameKeys.update();
        world.step(dt, 6, 2);
        player.update(dt);
        cam.position.set(
                player.getPosition().x,
                player.getPosition().y,
                0);
        hudManager.update(dt);
        cam.zoom = Game.ZOOM;
        cam.update();
    }
    
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.setProjectionMatrix(cam.combined);
        
        // render objects
        tmh.renderTerrainLayer(sb, cam);
        player.render(sb);  
        tmh.renderCollisionLayer(sb, cam);
        tmh.renderTopLayer(sb, cam);
        hudManager.render();
        // Box2D Debugging stuff
        if (debug) {
            b2dr.render(world, b2dCam.combined);
        }
    }

    public void dispose() {
        world.dispose();
        player.dispose();
        hudManager.dispose();
        tmh.dispose();
    }
    
    private Player createPlayer() {
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        bdef.type = BodyType.DynamicBody;
        Body body = world.createBody(bdef);
        shape.setAsBox((BIT_SIZE / 2) / PPM, (BIT_SIZE / 2) / PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
        body.setUserData("player");
        return new Player(body, new Texture("player_sprite_sheet.png"));
    }
    
    protected void createChangeState(float cellX, float cellY, String state) {
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        bdef.type = BodyType.StaticBody;
        Body body = world.createBody(bdef);
        shape.setAsBox((BIT_SIZE / 2) / PPM, (BIT_SIZE / 2) / PPM);
        body.setTransform(new Vector2(
                ((cellX * BIT_SIZE) + (BIT_SIZE / 2)) / PPM, 
                ((cellY * BIT_SIZE) + (BIT_SIZE / 2)) / PPM), 0);
        fdef.shape = shape;
        body.createFixture(fdef);
        body.setUserData(state);
    }
}
