package com.mock.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class TiledMapHandler {

    /*
     * GOALS/TODO:
     * 1. Clean up code to desirable measure
     *      - get Box2D Bodies working properly
     *      - get Collision detection working (just print statement)
     *      - get collision detection working completely (finish it)
     */

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
        setUpCollisionBodies();
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
    
    private void setUpCollisionBodies() {
        for (int row = 0; row < collisionLayer.getHeight(); row++) {
            for (int col = 0; col < collisionLayer.getWidth(); col++) {
                // get cell
                Cell cell = collisionLayer.getCell(col, row);
                // check if cell exists
                if (cell == null) continue;
                if (cell.getTile() == null) continue;
                // create box2d body/fixture
                BodyDef bdef = new BodyDef();
                FixtureDef fdef = new FixtureDef();
                PolygonShape shape = new PolygonShape(); 
                bdef.type = BodyType.StaticBody;
                Body body = world.createBody(bdef);
                shape.setAsBox(16, 16);
                fdef.shape = shape;
                body.createFixture(fdef);
                body.setUserData("collisionTile");
                body.setTransform(new Vector2((col * 32) + 16, (row * 32) + 16), 0);
            }
        }
    }
    
    public World getWorld() {
        return this.world;
    }
}
