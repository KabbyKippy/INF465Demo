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
    boolean finAtt = false;
    boolean right = false;
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

        if(MyInput.isPressed(MyInput.BUTTON4))
        {
            if(finAtt == false)
            {
                finAtt = true;

                if(right == true)
                {
                    Texture tex = MyHorrorGame.res.getTexture("PlayerATKRight");
                    TextureRegion[] sprites = TextureRegion.split(tex, 80, 40)[0];

                    setAnimation(sprites, 1 / 12f);
                }

                else
                {
                    Texture tex = MyHorrorGame.res.getTexture("PlayerATKLeft");
                    TextureRegion[] sprites = TextureRegion.split(tex, 80, 40)[0];

                    setAnimation(sprites, 1 / 12f);
                }






            }

        }

        if(MyInput.isPressed(MyInput.BUTTON4) && MyInput.wasPressed(MyInput.BUTTON3))
        {
            if(finAtt == false)
            {
                finAtt = true;
                right = true;
                Texture tex = MyHorrorGame.res.getTexture("PlayerATKRight");

                TextureRegion[] sprites = TextureRegion.split(tex, 80, 40)[0];

                setAnimation(sprites, 1 / 12f);


                System.out.println("I'm attacking right" + " ");
            }

        }

        if(MyInput.isPressed(MyInput.BUTTON4) && MyInput.wasPressed(MyInput.BUTTON2))
        {
            if(finAtt == false)
            {
                finAtt = true;
                right = false;
                Texture tex = MyHorrorGame.res.getTexture("PlayerATKLeft");

                TextureRegion[] sprites = TextureRegion.split(tex, 80, 40)[0];

                setAnimation(sprites, 1 / 12f);


                System.out.println("I'm attacking left" + " ");
            }

        }

        if(MyInput.isPressed(MyInput.BUTTON1) && MyInput.wasPressed(MyInput.BUTTON3)) {
            startTime = System.nanoTime();


            Texture tex = MyHorrorGame.res.getTexture("PlayerIdle");

            TextureRegion[] sprites = TextureRegion.split(tex, 40, 40)[0];

            setAnimation(sprites, 1 / 12f);

            System.out.println("I'm jumping right");
            right = true;
        }
        if(MyInput.isPressed(MyInput.BUTTON1) && MyInput.wasPressed(MyInput.BUTTON2)) {
            startTime = System.nanoTime();
            right = false;


            Texture tex = MyHorrorGame.res.getTexture("PlayerIdleleft");

            TextureRegion[] sprites = TextureRegion.split(tex, 40, 40)[0];

            setAnimation(sprites, 1 / 12f);


            System.out.println("I'm jumping left");

        }


        endTime = 0;
        startTime = 0;
        if(MyInput.isPressed(MyInput.BUTTON2)){
            System.out.println("I'm walking left");
            right = false;

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
            right = true;
            Texture  tex = MyHorrorGame.res.getTexture("PlayerWalkRight");

            TextureRegion[] sprites = TextureRegion.split(tex, 40, 40)[0];

            setAnimation(sprites, 1 / 12f);

            System.out.println("I'm walking right");}


        // If an animation needs to halt after one iteration, it goes here.
        if(animation.getTimesPlayed() == 1)
        {
            // Finish attacking
           if(finAtt) {

               if(right) {
                   Texture tex = MyHorrorGame.res.getTexture("PlayerIdle");

                   TextureRegion[] sprites = TextureRegion.split(tex, 40, 40)[0];
                   setAnimation(sprites, 1 / 10f);
                   System.out.println("I'm done" + " " + finAtt + " ");
                   animation.setTimesPlayed(0);
                   finAtt = false;
               }
               if(!right){
                   Texture tex = MyHorrorGame.res.getTexture("PlayerIdleleft");

                   TextureRegion[] sprites = TextureRegion.split(tex, 40, 40)[0];
                   setAnimation(sprites, 1 / 10f);
                   System.out.println("I'm done" + " " + finAtt + " ");
                   animation.setTimesPlayed(0);
                   finAtt = false;
               }
            }
        }
    }

    public void collectHealth(){ numHealth++;}
    public int getNumHealth(){return numHealth;}
    public boolean damaged(){
        System.out.println("I'm being hurt right now.");
    }
}
