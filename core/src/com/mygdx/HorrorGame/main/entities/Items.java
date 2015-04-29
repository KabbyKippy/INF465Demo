package com.mygdx.HorrorGame.main.entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.HorrorGame.main.MyHorrorGame;

/**
 * Created by NanoUnit on 4/16/2015.
 */
public class Items extends B2DSprite{

    public Items(Body body){

        super(body);
        Texture tex = MyHorrorGame.res.getTexture("Item1");
        TextureRegion[] sprites = TextureRegion.split(tex, 16,16)[0];
        setAnimation(sprites, 1/12f);


    }
}
