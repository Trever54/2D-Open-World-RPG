package com.mock.states;

import static com.mock.main.Game.BIT_SIZE;
import static com.mock.utility.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mock.entities.Background;
import com.mock.entities.Player;
import com.mock.handlers.ContactHandler;
import com.mock.handlers.TiledMapHandler;
import com.mock.hud.HUDManager;
import com.mock.input.GameKeys;
import com.mock.input.TouchInput;
import com.mock.main.Game;
import com.mock.main.GameStateManager;

import actions.TextAction;

public class TopDownState extends GameState {
    
    public static boolean debug = false;
    
    private Background background;
    
    public static boolean freezePlayer = false;
    public static boolean castRay = false;
    
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera b2dCam;
    private World world;
    private ContactHandler contactListener;
    private TiledMapHandler tmh;
    private HUDManager hudManager;
    
    private Vector2 p1, p2;
    private ShapeRenderer sr;
    
    public Player player;

    public TopDownState(GameStateManager gsm, String tiledPath) {
        super(gsm);
        b2dr = new Box2DDebugRenderer();
        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false, (Game.V_WIDTH / PPM) / Game.ZOOM, (Game.V_HEIGHT / PPM) / Game.ZOOM);
        contactListener = new ContactHandler();
        tmh = new TiledMapHandler(tiledPath);
        world = tmh.getWorld();
        world.setContactListener(contactListener);
        player = createPlayer();
        hudManager = new HUDManager();
        p1 = new Vector2();
        p2 = new Vector2();
        sr = new ShapeRenderer();
        sr.setColor(Color.BLUE);
        background = null;
    }

    public void update(float dt) {
        world.step(dt, 6, 2);
        if (background != null) { background.update(dt); }
        if (castRay) { handleRayCasting(); }
        handleTextActions();
        if (!freezePlayer) { player.update(dt); }
        cam.position.set(
                player.getPosition().x,
                player.getPosition().y,
                0);
        hudManager.update(dt);
        cam.zoom = Game.ZOOM;
        cam.update();
        GameKeys.update();
        TouchInput.update();
    }
    
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.setProjectionMatrix(cam.combined);

        // render objects
        if (background != null) { background.render(sb); }
        tmh.renderTerrainLayer(sb, cam);
        player.render(sb);
        tmh.renderCollisionLayer(sb, cam);
        tmh.renderTopLayer(sb, cam);
        hudManager.render();
        
        // handle misc things
        handleZoneSwitching();
        
        // Box2D Debugging stuff
        if (debug) {
            sr.setProjectionMatrix(b2dCam.combined);
            sr.begin(ShapeType.Line);
            sr.line(p1, p2);
            sr.end();
            b2dr.render(world, b2dCam.combined);
        }
    }

    public void dispose() {
        hudManager.dispose();
        world.dispose();
        player.dispose();
        if (background != null) { background.dispose(); }
        tmh.dispose();
        b2dr.dispose();
        sr.dispose();
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
    
    private void handleRayCasting() {
        float px = player.getPosition().x / PPM;
        float py = player.getPosition().y / PPM;
        float pw = player.getWidth() / PPM;
        float ph = player.getHeight() / PPM;
        p1 = new Vector2(px, py);
        if (player.facingLeft) {
            p2 = new Vector2(px - pw, py);
        }
        if (player.facingRight) {
            p2 = new Vector2(px + pw, py);
        }
        if (player.facingDown) {
            p2 = new Vector2(px, py - ph);
        }
        if (player.facingUp) {
            p2 = new Vector2(px, py + ph);
        }
        world.rayCast(contactListener.callback, p1, p2);
        castRay = false;
    }
    
    private void handleZoneSwitching() {
        if (ContactHandler.zoneStrings.isEmpty()) { return; }
        String zone = ContactHandler.zoneStrings.pop();
        switch (zone) {
            case "MAIN_WORLD": gsm.setState(GameStateManager.MAIN_WORLD); break;
            case "TEST_ZONE": gsm.setState(GameStateManager.TEST_ZONE); break;
        }
    }
    
    private void handleTextActions() {
        if (ContactHandler.textActions.isEmpty()) { return; }
        TextAction textAction = ContactHandler.textActions.peek();
        freezePlayer = true;
        HUDManager.textMode = true;
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
    
    // TODO: Pretty much the same as createChangeState but with a textAction
    protected void createTextAction(float cellX, float cellY, TextAction textAction) {
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
        body.setUserData(textAction);
    }
    
    protected void setBackground(String path) {
        background = new Background(path);
    }
      
}
