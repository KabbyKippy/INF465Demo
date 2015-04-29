package com.mygdx.HorrorGame.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.HorrorGame.main.handlers.Content;
import com.mygdx.HorrorGame.main.handlers.GameStateManager;
import com.mygdx.HorrorGame.main.handlers.MyInput;
import com.mygdx.HorrorGame.main.handlers.MyInputProcessor;
import com.mygdx.HorrorGame.main.states.GameState;

public class MyHorrorGame extends ApplicationAdapter {

    // Title, width, height, and the scale which increases the game dimensions.
    public static final String TITLE = "Demo";
    public static final int V_WIDTH = 320;
    public static final int V_HEIGHT = 240;
    public static final int SCALE = 2;

    public static final float STEP = 1 / 60f;
    private float accum;

    private SpriteBatch sb;
    private OrthographicCamera cam;
    private OrthographicCamera hudCam;

    private GameStateManager gsm;

    public static Content res;

    @Override
    public void create () {


        Gdx.input.setInputProcessor(new MyInputProcessor()); // now the game uses the custom input processor


        // USED TO GRAB TEXTURES
        res = new Content();
        res.loadTexture("Resources/Sprites/Player/Playeridle.png", "PlayerIdle");
        res.loadTexture("Resources/Sprites/Player/Playeridleleft.png", "PlayerIdleleft");
        res.loadTexture("Resources/Sprites/Player/PlayerWalkAnimationLeft.png", "PlayerWalkLeft");
        res.loadTexture("Resources/Sprites/Player/PlayerWalkAnimationRight.png", "PlayerWalkRight");
        res.loadTexture("Resources/Sprites/Player/ATKLeft.png", "PlayerATKLeft");
        res.loadTexture("Resources/Sprites/Player/ATKRight.png", "PlayerATKRight");
        res.loadTexture("Resources/Sprites/Player/DashAtkLeft.png", "PlayerDashATKLeft");
        res.loadTexture("Resources/Sprites/Player/DashAtkRight.png", "PlayerDashATKRight");
        res.loadTexture("Resources/Sprites/Player/ATKUpLeft.png", "PlayerATKUpeft");
        res.loadTexture("Resources/Sprites/Player/ATKUpRight.png", "PlayerATKUpRight");
        res.loadTexture("Resources/Sprites/Enemies/img1.png", "enemy");
        res.loadTexture("Resources/Sprites/Enemies/TenguWalk.gif", "tengu");
        res.loadTexture("Resources/Sprites/Other/crystal.png", "Item1");
        res.loadTexture("Resources/HUD/Hud.gif", "hud");

        sb = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
        hudCam = new OrthographicCamera();
        hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);
        gsm = new GameStateManager(this);

    }

    @Override
    public void render () {

        accum += Gdx.graphics.getDeltaTime();
        while(accum >= STEP){
            accum -= STEP;
            gsm.update(STEP);
            gsm.render();
            MyInput.update(); // updates the key strokes, what key is being pressed
        }



    }

    public void dispose(){}


    public SpriteBatch getSpriteBatch(){ return sb; }
    public OrthographicCamera getCam(){ return cam; }
    public OrthographicCamera getHUDCam() {return hudCam; }


    // Not going to be used, honestly.

    public void resize(int w, int h){}

    public void pause(){}

    public void resume(){}


}
