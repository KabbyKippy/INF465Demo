package com.mygdx.HorrorGame.main.handlers;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

/**
 * Created by NanoUnit on 3/23/2015.
 */
public class MyContactListener implements ContactListener{


    private int numFootContact;
    private Array<Body> bodiesToRemove;
   // Called when two fixtues start to collide with each other

    public  MyContactListener(){
        super();
        bodiesToRemove = new Array<Body>();



    }



    public void beginContact(Contact c){


        Fixture fa = c.getFixtureA();
        Fixture fb = c.getFixtureB();


        // determines if the player is on the ground
        if(fa == null || fb == null){return;}
        if(fa.getUserData() != null && fa.getUserData().equals("foot")){

            numFootContact++;

        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot")){

            numFootContact++;

        }
        //.out.println( fa.getUserData() + "," + fb.getUserData()); // when you run this you see that fa is the ground and fb is the box then the ground


        if(fa.getUserData() != null && fa.getUserData().equals("lantern")){

            // remove crystal
            bodiesToRemove.add(fa.getBody());

        }
        if(fb.getUserData() != null && fb.getUserData().equals("lantern")){

            bodiesToRemove.add(fb.getBody());

        }
        if(fb.getUserData() != null && fb.getUserData().equals("")){

            numFootContact++;

        }




    }

    // called when two fixtures are no longer colliding with each other
    public void endContact(Contact c) {

        Fixture fa = c.getFixtureA();
        Fixture fb = c.getFixtureB();

        // determines if the player is not on the ground
        if(fa == null || fb == null){return;}
        if(fa.getUserData() != null && fa.getUserData().equals("foot")){

            numFootContact--;

        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot")){

            numFootContact--;

        }

    }



    public boolean isPlayerOnGround(){return numFootContact > 0;}
    public Array<Body> getBodiesToRemove() {return bodiesToRemove;}




     // collision detection detects two things first colide withe ach other
    // presolve
    // collision handeling what does it do when they collide ex. bounce off
    //postsolve

    public void preSolve(Contact c, Manifold m ){}




    public void postSolve(Contact c, ContactImpulse ci){}
}
