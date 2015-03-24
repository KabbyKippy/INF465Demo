package com.mygdx.HorrorGame.main.handlers;

/**
 * Created by Kai on 3/11/2015.
 */
public class B2DVars {
    // pixels per meter ratio
    // This class is purely for handling variables
    public static final float PPM = 100;

    // category bits ... this decides what collides with what
    // these values decide what the object is considered so the ground is considered to be an object of type 2.. etc
    public static final short BIT_GROUND = 2;
    public static final short BIT_PLAYER = 4;
  // the ball got removed  public static final short BIT_BALL = 8;
/*
 Try not to use the default which is 1
 also these numbers refer to bits so you want to use numbers of 2^x power

 0000 0000 0000 0010   the ground
 0000 0000 0000 0100  box
 0000 0000 0000 1000 ball



 */
}
