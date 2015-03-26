package com.mygdx.HorrorGame.main.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.HorrorGame.main.MyHorrorGame;
import com.mygdx.HorrorGame.main.handlers.MyInput;

/**
 * Created by Aaron on 3/25/2015.
 */
public class Player extends B2DSprite {

    private int numHealth;

    public Player(Body body){
        super(body);

        Texture tex = MyHorrorGame.res.getTexture("PlayerIdle");

        TextureRegion[] sprites = TextureRegion.split(tex, 40, 40)[0];

        setAnimation(sprites, 1/12f);



        //if ()

       /* player input to what they are doing






         */








    }

    public void collectHealth(){ numHealth++;}
    public int getNumHealth(){return numHealth;}
}
