package com.mygdx.HorrorGame.main.handlers;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by NanoUnit on 3/23/2015.
 */
public class MyContactListener implements ContactListener{



   // Called when two fixtues start to collide with each other
    public void beginContact(Contact c){


        Fixture fa = c.getFixtureA();
        Fixture fb = c.getFixtureB();

        System.out.println( fa.getUserData() + "," + fb.getUserData()); // when you run this you see that fa is the ground and fb is the box then the ground








    }

    // called when two fixtures are no longer colliding with each other
    public void endContact(Contact c) {












    }



















     // collision detection detects two things first colide withe ach other
    // presolve
    // collision handeling what does it do when they collide ex. bounce off
    //postsolve

    public void preSolve(Contact c, Manifold m ){}




    public void postSolve(Contact c, ContactImpulse ci){}
}
