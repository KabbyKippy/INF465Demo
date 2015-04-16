package com.mygdx.HorrorGame.main.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.HorrorGame.main.MyHorrorGame;
import com.mygdx.HorrorGame.main.handlers.GameStateManager;

/**
 * Created by Kai on 3/11/2015.
 */

// only going to extend off of this, not making any instances of it.
public abstract class GameState {

    protected GameStateManager gsm;
    // ref to the game itself.
    protected MyHorrorGame game;

    protected SpriteBatch sb;
    protected OrthographicCamera cam;
    protected OrthographicCamera hudCam;

    protected GameState(GameStateManager gsm){
        this.gsm = gsm;
        game = gsm.getGame();
        sb = game.getSpriteBatch();
        cam = game.getCam();
        hudCam = game.getHUDCam();
    }

    public abstract boolean handleInput(float dt);
    public abstract void update(float dt);
    public abstract void render();
    public abstract void dispose();


}
