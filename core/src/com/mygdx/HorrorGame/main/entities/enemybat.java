package com.mygdx.HorrorGame.main.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.mygdx.HorrorGame.main.MyHorrorGame;
import com.mygdx.HorrorGame.main.handlers.MyInput;
import com.mygdx.HorrorGame.main.states.Play;

/**
 * Created by NanoUnit on 4/2/2015.
 */
public abstract class enemybat extends B2DSprite {

    protected int health;
    protected  int maxHealth;
    protected boolean dead;
    protected  int damage;
    protected boolean flintching;
    protected  long flintchTimer;

    public Enemy(TiledMap tm)
    {
        super(tm)




    }
    public boolean isDead(){return dead;}
    public int getDamage() {return damage;}
    public void hit(int damage)
    {
        if (dead || flintching )return;
        health -= damage;
        if(health < 0) health =0;
        if (health == 0) dead = true;
        flintching = true;
        flintching = System.nanoTime();



    }






    public enemybat(Body body){
        super(body);

        Texture tex = MyHorrorGame.res.getTexture("enemy");

        TextureRegion[] sprites = TextureRegion.split(tex, 55, 40)[0];
        setAnimation(sprites, 1 / 10f);



    }

    public void update(float dt){

        animation.update(dt);





    }

    public void collectHealth(){ numHealth++;}
    public int getNumHealth(){return numHealth;}
}
