package com.mock.handlers;

import static com.mock.handlers.B2DVars.PPM;
import static com.mock.main.Game.BIT_SIZE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    private TiledMap tileMap;
    private OrthogonalTiledMapRenderer tmr;
    private TiledMapTileLayer terrainLayer;
    private TiledMapTileLayer decorationLayer;
    private TiledMapTileLayer collisionLayer;
    private TiledMapTileLayer topLayer;
    private World world;
    
    public TiledMapHandler(String path) {
        tileMap = new TmxMapLoader().load(path);
        tmr = new OrthogonalTiledMapRenderer(tileMap);
        terrainLayer = (TiledMapTileLayer) tileMap.getLayers().get("Terrain Layer");
        decorationLayer = (TiledMapTileLayer) tileMap.getLayers().get("Decoration Layer");
        collisionLayer = (TiledMapTileLayer) tileMap.getLayers().get("Collision Layer");
        topLayer = (TiledMapTileLayer) tileMap.getLayers().get("Top Layer");
        world = new World(new Vector2(0, 0), true);
        setUpCollisionBodies();
        // setUpCollisionBodiesTest();
        
        /* example using vertices
        float[] vertices = new float[8];
        vertices[0] = 0;
        vertices[1] = 0;
        
        vertices[2] = 500 / PPM;
        vertices[3] = 0;
        
        vertices[4] = 500 / PPM;
        vertices[5] = 100 / PPM;
        
        vertices[6] = 0;
        vertices[7] = 100 / PPM;
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape(); 
        bdef.type = BodyType.StaticBody;
        Body body = world.createBody(bdef);
        shape.set(vertices);
        fdef.shape = shape;
        body.createFixture(fdef);
        body.setUserData("collisionTile");
        */
        
    }
    
    public void renderTerrainLayer(SpriteBatch sb, OrthographicCamera cam) {
        tmr.setView(cam);
        tmr.getBatch().begin();
        tmr.renderTileLayer(terrainLayer);
        tmr.getBatch().end();
    }
    
    public void renderDecorationLayer(SpriteBatch sb, OrthographicCamera cam) {
        tmr.setView(cam);
        tmr.getBatch().begin();
        tmr.renderTileLayer(decorationLayer);
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
    
    private void setUpCollisionBodiesTest() {
        HashMap<Integer, ArrayList<Vector2>> listMap = new HashMap<Integer, ArrayList<Vector2>>();
        HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        ArrayList<Vector2> verticesList;
        Vector2[] vertices; 
        for (int row = 0; row < collisionLayer.getHeight(); row++) {
            for (int col = 0; col < collisionLayer.getWidth(); col++) {
                // get cell
                Cell cell = collisionLayer.getCell(col, row);
                // check if cell exists
                if (cell == null) continue;
                if (cell.getTile() == null) continue;
                Vector2 bottomLeft = new Vector2((col * BIT_SIZE) / PPM, (row * BIT_SIZE) / PPM); 
                Vector2 bottomRight = new Vector2(((col * BIT_SIZE) + BIT_SIZE) / PPM, (row * BIT_SIZE) / PPM); 
                Vector2 topRight = new Vector2(((col * BIT_SIZE) + BIT_SIZE) / PPM, ((row * BIT_SIZE) + BIT_SIZE) / PPM);
                Vector2 topLeft = new Vector2((col * BIT_SIZE) / PPM, ((row * BIT_SIZE) + BIT_SIZE) / PPM);
                int index = 0;
                int listNum = -1; 
                // base case
                if (listMap.isEmpty()) {
                    listNum = 0;
                    verticesList = new ArrayList<Vector2>();
                } else {
                    // check each to see if a list contains any of the vertices. If one does, that's the list to be used
                    for (int i = 0; i < listMap.size(); i++) {
                        if (listMap.get(i).contains(topLeft)) {
                            listNum = i;
                            break;
                        }
                        if (listMap.get(i).contains(topRight)) {
                            listNum = i;
                            break;
                        }
                        if (listMap.get(i).contains(bottomRight)) {
                            listNum = i;
                            break;
                        }
                        if (listMap.get(i).contains(bottomLeft)) {
                            listNum = i;
                            break;
                        }
                    }
                    verticesList = listMap.get(listNum);
                    index = indexMap.get(listNum);
                }
                // if no list was found, make a new one
                if (listNum == -1) {
                    listNum = listMap.size();
                    verticesList = new ArrayList<Vector2>();
                }
                // Add vertices to list in the correct index's
                // bottom left
                if (!verticesList.contains(bottomLeft)) {
                    verticesList.add(index, bottomLeft);
                    index++;
                } else { // does contain, so don't add? but find index
                    for (int i = 0; i < verticesList.size(); i++) {
                        if (verticesList.get(i).equals(bottomLeft)) {
                            index = i + 1;
                            break;
                        }
                    }
                }
                // bottom right
                if (!verticesList.contains(bottomRight)) {
                    verticesList.add(index, bottomRight);
                    index++;
                } else { // does contain, so don't add? but find index
                    for (int i = 0; i < verticesList.size(); i++) {
                        if (verticesList.get(i).equals(bottomRight)) {
                            index = i + 1;
                            break;
                        }
                    }
                }
                // top right
                if (!verticesList.contains(topRight)) {
                    verticesList.add(index, topRight);
                    index++;
                } else { // does contain, so don't add? but find index
                    for (int i = 0; i < verticesList.size(); i++) {
                        if (verticesList.get(i).equals(topRight)) {
                            index = i + 1;
                            break;
                        }
                    }
                }
                // top left
                if (!verticesList.contains(topLeft)) {
                    verticesList.add(index, topLeft);
                    index++;
                } else { // does contain, so don't add? but find index
                    for (int i = 0; i < verticesList.size(); i++) {
                        if (verticesList.get(i).equals(topLeft)) {
                            index = i + 1;
                            break;
                        }
                    }
                } 
                // put list back in hashmap
                int itr = 1;
                System.out.println("ITERATION: " + itr);
                for (int j = 0; j < verticesList.size(); j++) {
                    System.out.println(j + ": " + verticesList.get(j).x * PPM + ", " + verticesList.get(j).y * PPM);
                }
                System.out.println();
                listMap.put(listNum, verticesList);
                indexMap.put(listNum, index);
            }
        }
        
        // TODO: Need to elimnate straight lines that serve no purpose
        // force fix for right now
        listMap.get(0).remove(9);
        listMap.get(0).remove(8);
        
        System.out.println();
        System.out.println("START");
        for (int i = 0; i < listMap.size(); i++) {
            System.out.println("--------");
            vertices = new Vector2[listMap.get(i).size()];
            vertices = listMap.get(i).toArray(vertices);
            for (int j = 0; j < vertices.length; j++) {
                System.out.println(j + ": " + vertices[j].x * PPM + ", " + vertices[j].y * PPM);
            }
            // create box2d body/fixture
            BodyDef bdef = new BodyDef();
            FixtureDef fdef = new FixtureDef();
            PolygonShape shape = new PolygonShape(); 
            bdef.type = BodyType.StaticBody;
            Body body = world.createBody(bdef); 
            shape.set(vertices);
            fdef.shape = shape;
            body.createFixture(fdef);
            body.setUserData("collisionTile");
        } 
    }
    
    // TODO: Delete later
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
                shape.setAsBox((BIT_SIZE / 2) / PPM, (BIT_SIZE / 2) / PPM);
                fdef.shape = shape;
                body.createFixture(fdef);
                body.setUserData("collisionTile");
                body.setTransform(new Vector2(((col * BIT_SIZE) + (BIT_SIZE / 2)) / PPM, ((row * BIT_SIZE) + (BIT_SIZE / 2)) / PPM), 0);
            }
        }
    }
    
    public World getWorld() {
        return this.world;
    }
}
