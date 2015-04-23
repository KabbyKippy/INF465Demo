package com.mygdx.HorrorGame.main.handlers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

/**
 * Created by NanoUnit on 3/24/2015.
 */
public class MyInputProcessor extends InputAdapter {

    @Override
    public boolean keyDown(int k){

        if ( k  == Keys.UP){
            MyInput.setKey(MyInput.BUTTON1 , true);

        }// Z to jump buttonm is pressed



        // X to change color of platform

        if( k == Keys.LEFT){
            MyInput.setKey(MyInput.BUTTON2, true );


        }

        if( k == Keys.RIGHT){
            MyInput.setKey(MyInput.BUTTON3, true );


        }

        if( k == Keys.SPACE){
            MyInput.setKey(MyInput.BUTTON4, true );


        }


        return true;}


    @Override
    public boolean keyUp(int k) {
        if ( k  == Keys.UP ){
            MyInput.setKey(MyInput.BUTTON1 , false); //button is now unpressed

        }
        if( k == Keys.LEFT){
            MyInput.setKey(MyInput.BUTTON2, false );


        }
        if( k == Keys.RIGHT){
            MyInput.setKey(MyInput.BUTTON3, false );


        }

        if( k == Keys.SPACE){
            MyInput.setKey(MyInput.BUTTON4, false );


        }

        return true;}
}
