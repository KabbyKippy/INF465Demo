package com.mygdx.HorrorGame.main.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.mygdx.HorrorGame.main.MyHorrorGame;
import com.mygdx.HorrorGame.main.handlers.MyInput;
import com.mygdx.HorrorGame.main.states.Play;

/**
 * Created by Aaron on 3/25/2015.
 */
public class Player extends B2DSprite {

    long startTime;
    long endTime;
    private int numHealth;

    public Player(Body body){
        super(body);

        Texture tex = MyHorrorGame.res.getTexture("PlayerIdle");

        TextureRegion[] sprites = TextureRegion.split(tex, 40, 40)[0];
        setAnimation(sprites, 1 / 10f);



        //if ()

       /* player input to what they are doing






         */

    }

    public void update(float dt){

        animation.update(dt);
        if(MyInput.isPressed(MyInput.BUTTON1)) {
            startTime = System.nanoTime();


            Texture tex = MyHorrorGame.res.getTexture("PlayerIdle");

            TextureRegion[] sprites = TextureRegion.split(tex, 40, 40)[0];

            setAnimation(sprites, 1 / 12f);


            System.out.println("I'm jumping");
            endTime = System.nanoTime();

            System.out.println(startTime + " , " + endTime +  " , " + (endTime - startTime) + " I'm not jumping anymore.");
            System.out.println(dt);
        }
        endTime = 0;
        startTime = 0;
        if(MyInput.isPressed(MyInput.BUTTON2)){
            System.out.println("I'm walking left");

            Texture  tex = MyHorrorGame.res.getTexture("PlayerWalkLeft");

            TextureRegion[] sprites = TextureRegion.split(tex, 40, 40)[0];



            setAnimation(sprites, 1 / 12f);



        }
/*
        if(MyInput.isDown(MyInput.BUTTON3) && MyInput.isPressed(MyInput.BUTTON1)){

            Texture  tex = MyHorrorGame.res.getTexture("PlayerIdle");

            TextureRegion[] sprites = TextureRegion.split(tex, 40, 40)[0];

            setAnimation(sprites, 1 / 12f);

            tex = MyHorrorGame.res.getTexture("PlayerWalkRight");

            sprites = TextureRegion.split(tex, 40, 40)[0];

            setAnimation(sprites, 1 / 12f);





        }*/


        if(MyInput.isPressed(MyInput.BUTTON3)){
            Texture  tex = MyHorrorGame.res.getTexture("PlayerWalkRight");

            TextureRegion[] sprites = TextureRegion.split(tex, 40, 40)[0];

            setAnimation(sprites, 1 / 12f);

            System.out.println("I'm walking right");}




    }

    public void collectHealth(){ numHealth++;}
    public int getNumHealth(){return numHealth;}
}
