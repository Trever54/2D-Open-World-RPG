package com.mock.input;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * This class helps handle all touch and click related input
 * Supports a maximum of 6 pointers at one time.
 * @author Trever Mock
 *
 */
public class TouchInput {
    
    public static boolean touchUp = false;
    public static boolean touchDown = false;

    private static final int MAX_POINTERS = 6;
    
    private static Vector2[] touchDraggedPositions;
    private static Vector2[] touchDownPositions;
    private static Vector2[] touchUpPositions;
    private static boolean[] touched;
    
    static {
        touchDraggedPositions = new Vector2[MAX_POINTERS];
        touchDownPositions = new Vector2[MAX_POINTERS];
        touchUpPositions = new Vector2[MAX_POINTERS];
        touched = new boolean[MAX_POINTERS];
        for (int i = 0; i < touchDraggedPositions.length; i++) {
            touchDraggedPositions[i] = null;
            touchDownPositions[i] = null;
            touchUpPositions[i] = null;
            touched[i] = false;
        }
    }
    
    public static void update() {
        for (int i = 0; i < MAX_POINTERS; i++) {
            if (touchDownPositions[i] != null) {
                touched[i] = true;
            }
            if (touchUpPositions[i] != null) {
                clearPosition(i);
                touched[i] = false;
            }
        }
        touchUp = false;
    }
    
    public static void setPositionDragged(int screenX, int screenY, int pointer) {
        if (pointer < MAX_POINTERS) {
            touchDraggedPositions[pointer] = new Vector2(screenX, screenY);
        }
    }
    
    public static void setPositionTouchDown(int screenX, int screenY, int pointer) {
        if (pointer < MAX_POINTERS) {
            touchDownPositions[pointer] = new Vector2(screenX, screenY);
        }
        touchDown = true;
    }
    
    public static void setPositionTouchUp(int screenX, int screenY, int pointer) {
        if (pointer < MAX_POINTERS) {
            touchUpPositions[pointer] = new Vector2(screenX, screenY);
        }
        touchUp = true;
        touchDown = false;
    }
    
    public static int numOfPointers() {
        int count = 0;
        for (int i = 0; i < touchDraggedPositions.length; i++) {
            if (touchDraggedPositions[i] != null) {
                count++;
            }
        }
        return count;
    }
    
    public static void clearPosition(int pointer) {
        touchDraggedPositions[pointer] = null;
        touchDownPositions[pointer] = null;
        touchUpPositions[pointer] = null;
    }
    
    public static void clearAllPositions() {
        for (int i = 0; i < touchDraggedPositions.length; i++) {
            touchDraggedPositions[i] = null;
            touchDownPositions[i] = null;
            touchUpPositions[i] = null;
        }
    }
    
    /**
     * Checks to see if the point dragged from the touch down point 
     * is within the bounding rectangle for the sprite parameter.
     * @param sprite - the sprite to get the bounding rectangle from
     * @return true if the point is contained; false otherwise
     */
    public static boolean containsTouchDragged(Sprite sprite) {
        for (int i = 0; i < touchDraggedPositions.length; i++) {
            if (touchDraggedPositions[i] != null
                    && sprite.getBoundingRectangle().contains(touchDraggedPositions[i])) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks to see if the point at the point of being touched is within 
     * the bounding rectangle for the sprite parameter.
     * @param sprite - the sprite to get the bounding rectangle from
     * @return true if the point is contained; false otherwise
     */
    public static boolean containsTouchDown(Sprite sprite) {
        for (int i = 0; i < touchDownPositions.length; i++) {
            if (touchDownPositions[i] != null
                    && sprite.getBoundingRectangle().contains(touchDownPositions[i])) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks to see if the point where the touch ends 
     * is within the bounding rectangle for the sprite parameter.
     * @param sprite - the sprite to get the bounding rectangle from
     * @return true if the point is contained; false otherwise
     */
    public static boolean containsTouchUp(Sprite sprite) {
        for (int i = 0; i < touchUpPositions.length; i++) {
            if (touchUpPositions[i] != null
                    && sprite.getBoundingRectangle().contains(touchUpPositions[i])) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks to see if the point at touch down is contains within a bounding
     * rectangle for the sprite parameter. This method acts differently from
     * touch down in the sense that it will only record the first time
     * step of the point being touched down.
     * @param sprite - the sprite to get the bounding rectangle from
     * @return true if the point is contained; false otherwise
     */
    public static boolean isPressed(Sprite sprite) {
        for (int i = 0; i < touchDownPositions.length; i++) {
            if (touchDownPositions[i] != null
                    && !touched[i]
                    && sprite.getBoundingRectangle().contains(touchDownPositions[i])) {
                return true;
            }
        }
        return false;
    }
}
