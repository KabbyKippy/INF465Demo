package com.mygdx.HorrorGame.main.handlers;

/**
 * Created by NanoUnit on 3/24/2015.
 */
public class MyInput {

    public static boolean[] keys;
    public static boolean[] pkeys;

    public static final int NUM_KEYS = 3; // number of buttons
    public static final int BUTTON1 = 0; // first button
    public static final int BUTTON2 = 1; // second button
    public static final int BUTTON3 = 2; // third button

    static {

        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];

    }

    public static void update() {


        for(int i = 0; i< NUM_KEYS; i++){
            pkeys[i] = keys[i];




    }
    }
// i is button 1 or button 2
    public static void setKey(int i, boolean b){keys[i] = b;}
    public static boolean isDown(int i){return keys[i];}
    public static boolean isPressed(int i ){return keys[i] && !pkeys[i];}
    public static boolean wasPressed(int i ){return pkeys[i];}

}
