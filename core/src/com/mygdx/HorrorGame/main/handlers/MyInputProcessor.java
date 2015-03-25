package com.mygdx.HorrorGame.main.handlers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

/**
 * Created by NanoUnit on 3/24/2015.
 */
public class MyInputProcessor extends InputAdapter {

    @Override
    public boolean keyDown(int k){

        if ( k  == Keys.W){
            MyInput.setKey(MyInput.BUTTON1 , true);

        }// Z to jump buttonm is pressed



        // X to change color of platform

        if( k == Keys.A){
            MyInput.setKey(MyInput.BUTTON2, true );


        }

        if( k == Keys.D){
            MyInput.setKey(MyInput.BUTTON3, true );


        }


        return true;}


    @Override
    public boolean keyUp(int k) {
        if ( k  == Keys.W){
            MyInput.setKey(MyInput.BUTTON1 , false); //button is now unpressed

        }
        if( k == Keys.A){
            MyInput.setKey(MyInput.BUTTON2, false );


        }
        if( k == Keys.D){
            MyInput.setKey(MyInput.BUTTON3, false );


        }

        return true;}
}
