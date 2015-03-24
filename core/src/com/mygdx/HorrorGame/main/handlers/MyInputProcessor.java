package com.mygdx.HorrorGame.main.handlers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

/**
 * Created by NanoUnit on 3/24/2015.
 */
public class MyInputProcessor extends InputAdapter {

    public boolean KeyDown(int k){

        if ( k  == Keys.Z){
            MyInput.setKey(MyInput.BUTTON1 , true);

        }// Z to jump buttonm is pressed



        // X to change color of platform

        if( k == Keys.X){
            MyInput.setKey(MyInput.BUTTON2, true );


        }


        return true;}


    public boolean KeyUp(int k) {
        if ( k  == Keys.Z){
            MyInput.setKey(MyInput.BUTTON1 , false); //button is now unpressed

        }
        if( k == Keys.X){
            MyInput.setKey(MyInput.BUTTON2, false );


        }return true;}
}
