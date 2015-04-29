package com.mygdx.HorrorGame.main.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.HorrorGame.main.MyHorrorGame;

/**
 * Created by NanoUnit on 4/23/2015.
 */
public class enemytengu extends B2DSprite{
    private int numHealth;

    public enemytengu(Body body){
        super(body);

        Texture tex = MyHorrorGame.res.getTexture("tengu");

        TextureRegion[] sprites = TextureRegion.split(tex, 30, 30)[0];
        setAnimation(sprites, 1 / 10f);



    }

    public void update(float dt){

        animation.update(dt);





    }

    public void collectHealth(){ numHealth++;}
    public int getNumHealth(){return numHealth;}
}

