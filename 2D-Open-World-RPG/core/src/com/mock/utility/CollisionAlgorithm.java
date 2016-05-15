package com.mock.utility;

import static com.mock.handlers.B2DVars.PPM;
import static com.mock.main.Game.BIT_SIZE;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

public class CollisionAlgorithm {

    private TiledMapTileLayer collisionLayer;
    
    private boolean[][] coveredTable;
    
    public CollisionAlgorithm(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }
    
    /**
     * Main method that returns a list of Vector2[] vertices which represent a single body
     */
    public ArrayList<Vector2[]> optimizeBodies() {
        ArrayList<Vector2[]> shapes = new ArrayList<Vector2[]>();
        int minX, maxX, minY, maxY, diffX, diffY;
        fillTableFalse();
        for (int row = 0; row < collisionLayer.getHeight(); row++) {
            for (int col = 0; col < collisionLayer.getWidth(); col++) { 
                Cell cell = collisionLayer.getCell(col, row);
                // check if cell exists
                if (cell == null) continue;
                if (cell.getTile() == null) continue;
                if (coveredTable[col][row]) continue;
                minY = checkBelow(col, row);
                maxY = checkAbove(col, row);
                minX = checkLeft(col, row);
                maxX = checkRight(col, row);
                diffX = maxX - minX;
                diffY = maxY - minY;
                if (diffY > diffX) {    // vertically oriented 
                    shapes.add(createVerticalBody(maxY, minY, col));
                    updateTableVertical(col, row);
                } else {    // horizontally oriented
                    shapes.add(createHorizontalBody(maxX, minX, row));
                    updateTableHorizontal(col, row);
                } 
            }
        }
        return shapes;
    }
    
    private void updateTableVertical(int col, int row) {
        int origCol = col;
        int origRow = row;
        coveredTable[col][row] = true;
        Cell cell = null;
        // up
        do {
            row++;
            cell = collisionLayer.getCell(col, row);
            if (row >= collisionLayer.getHeight() || coveredTable[col][row]) break;
            coveredTable[col][row] = true;
        } while (cell != null);
        // down
        col = origCol;
        row = origRow;
        do {
            row--;   
            cell = collisionLayer.getCell(col, row);
            if (row < 0 || coveredTable[col][row]) break;
            coveredTable[col][row] = true;
        } while (cell != null);
    }
    
    private void updateTableHorizontal(int col, int row) {
        int origCol = col;
        int origRow = row;
        coveredTable[col][row] = true;
        Cell cell = null;
        // right
        do {
            col++;
            cell = collisionLayer.getCell(col, row);
            if (col >= collisionLayer.getWidth() || coveredTable[col][row]) break;
            coveredTable[col][row] = true;
        } while (cell != null);
        // left
        col = origCol;
        row = origRow;
        do {
            col--;   
            cell = collisionLayer.getCell(col, row);
            if (col < 0 || coveredTable[col][row]) break;
            coveredTable[col][row] = true;
        } while (cell != null);
    }
    
    private Vector2[] createVerticalBody(int maxY, int minY, int col) {
        Vector2[] vertices = new Vector2[4];
        vertices[0] = new Vector2((col * BIT_SIZE) / PPM, (maxY * BIT_SIZE) / PPM);
        vertices[1] = new Vector2(((col * BIT_SIZE) + BIT_SIZE) / PPM, (maxY * BIT_SIZE) / PPM);
        vertices[2] = new Vector2((col * BIT_SIZE) / PPM, (minY * BIT_SIZE) / PPM);
        vertices[3] = new Vector2(((col * BIT_SIZE) + BIT_SIZE) / PPM, (minY * BIT_SIZE) / PPM);
        return vertices;
    }
    
    private Vector2[] createHorizontalBody(int maxX, int minX, int row) {
        Vector2[] vertices = new Vector2[4];
        vertices[0] = new Vector2((maxX * BIT_SIZE) / PPM, (row * BIT_SIZE) / PPM);
        vertices[1] = new Vector2((maxX * BIT_SIZE) / PPM, ((row * BIT_SIZE) + BIT_SIZE) / PPM);
        vertices[2] = new Vector2((minX * BIT_SIZE) / PPM, (row * BIT_SIZE) / PPM);
        vertices[3] = new Vector2((minX * BIT_SIZE) / PPM, ((row * BIT_SIZE) + BIT_SIZE) / PPM);
        return vertices;
    }
    
    private int checkRight(int col, int row) {
        Cell cell = null;
        do {
            col++;   
            cell = collisionLayer.getCell(col, row);
            if (col >= collisionLayer.getWidth() || coveredTable[col][row]) break;
        } while (cell != null);
        return col;
    }
    
    private int checkLeft(int col, int row) {
        Cell cell = null;
        do {
            col--;   
            cell = collisionLayer.getCell(col, row);
            if (col < 0 || coveredTable[col][row]) break;
        } while (cell != null);
        col++;
        return col;
    }
    
    private int checkAbove(int col, int row) {
        Cell cell = null;
        do {
            row++;   
            cell = collisionLayer.getCell(col, row);
            if (row >= collisionLayer.getHeight() || coveredTable[col][row]) break;
        } while (cell != null);
        return row;
    }
    
    private int checkBelow(int col, int row) {
        Cell cell = null;
        do {
            row--;   
            cell = collisionLayer.getCell(col, row);
            if (row < 0 || coveredTable[col][row]) break;
        } while (cell != null);
        row++;
        return row;
    }
    
    private void fillTableFalse() {
        coveredTable = new boolean[collisionLayer.getWidth()][collisionLayer.getHeight()];
        for (int y = 0; y < collisionLayer.getHeight(); y++) {
            for (int x = 0; x < collisionLayer.getWidth(); x++) {
                coveredTable[x][y] = false;
            }
        } 
    }
    
    
}
