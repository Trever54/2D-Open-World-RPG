package com.mock.handlers;

import java.util.Stack;

import com.mock.main.Game;
import com.mock.states.GameState;
import com.mock.zones.InsideTP;
import com.mock.zones.MainWorld;

public class GameStateManager {
    
    public static final int MAIN_WORLD = 0;
    public static final int INSIDE_TP = 1;
    private Game game;
    private Stack<GameState> gameStates;
    
    public GameStateManager(Game game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(MAIN_WORLD);
    }
    
    public void update(float dt) {
        gameStates.peek().update(dt);
    }
    
    public void render() {
        gameStates.peek().render();
    }
    
    private GameState getState(int state) {
        if (state == MAIN_WORLD) return new MainWorld(this);
        if (state == INSIDE_TP) return new InsideTP(this);
        return null;
    }
    
    public void setState(int state) {
        popState();
        pushState(state);
    }
    
    public void pushState(int state) {
        gameStates.push(getState(state));
    }
    
    public void popState() {
        GameState g = gameStates.pop();
        g.dispose();
    }
    
    public void dispose() {}
    
    public Game game() {
        return this.game;
    }
}
