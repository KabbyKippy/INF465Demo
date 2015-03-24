package com.mygdx.HorrorGame.main.handlers;

import com.mygdx.HorrorGame.main.MyHorrorGame;
import com.mygdx.HorrorGame.main.states.GameState;
import com.mygdx.HorrorGame.main.states.Play;

import java.util.Stack;

/**
 * Created by Kai on 3/11/2015.
 */
public class GameStateManager {

    private MyHorrorGame game;

    private Stack<GameState> gameStates;

    public static final int PLAY = 999;

    public GameStateManager(MyHorrorGame game){
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(PLAY);
    }

    public void update(float dt){
        gameStates.peek().update(dt);
    }

    public void render(){
        gameStates.peek().render();
    }

    private GameState getState(int state){
        if(state == PLAY) return new Play(this);
        return null;
    }

    public void setState(int state){
        popState();
        pushState(state);
    }

    public void pushState(int state){
        gameStates.push(getState(state));
    }

    public void popState(){
        GameState g = gameStates.pop();
        g.dispose();
    }


    public MyHorrorGame getGame() { return game; }


}
