package com.mock.handlers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mock.utility.CollisionAlgorithm;

public class TiledMapHandler {

    private TiledMap tileMap;
    private OrthogonalTiledMapRenderer tmr;
    private TiledMapTileLayer terrainLayer;
    private TiledMapTileLayer collisionLayer;
    private TiledMapTileLayer topLayer;
    private World world;
    
    public TiledMapHandler(String path) {
        tileMap = new TmxMapLoader().load(path);
        tmr = new OrthogonalTiledMapRenderer(tileMap);
        terrainLayer = (TiledMapTileLayer) tileMap.getLayers().get("Terrain Layer");
        collisionLayer = (TiledMapTileLayer) tileMap.getLayers().get("Collision Layer");
        topLayer = (TiledMapTileLayer) tileMap.getLayers().get("Top Layer");
        world = new World(new Vector2(0, 0), true);
        // Box2D Collision Algorithm
        CollisionAlgorithm ca = new CollisionAlgorithm(collisionLayer);
        ArrayList<Vector2[]> vList = ca.optimizeBodies();
        for (Vector2[] v : vList) {
            createBody(v);
        }
    }
    
    public void renderTerrainLayer(SpriteBatch sb, OrthographicCamera cam) {
        tmr.setView(cam);
        tmr.getBatch().begin();
        tmr.renderTileLayer(terrainLayer);
        tmr.getBatch().end();
    }
    
    public void renderCollisionLayer(SpriteBatch sb, OrthographicCamera cam) {
        tmr.setView(cam);
        tmr.getBatch().begin();
        tmr.renderTileLayer(collisionLayer);
        tmr.getBatch().end();
    }
    
    public void renderTopLayer(SpriteBatch sb, OrthographicCamera cam) {
        tmr.setView(cam);
        tmr.getBatch().begin();
        tmr.renderTileLayer(topLayer);
        tmr.getBatch().end();
    }
    
    public void dispose() {
        tmr.dispose();
        tileMap.dispose();
    }
    
    private void createBody(Vector2[] vertices) {
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape(); 
        bdef.type = BodyType.StaticBody;
        Body body = world.createBody(bdef);
        shape.set(vertices);
        fdef.shape = shape;
        body.createFixture(fdef);
        body.setUserData("collision_tile");
    }
    
    public World getWorld() {
        return this.world;
    }
}
