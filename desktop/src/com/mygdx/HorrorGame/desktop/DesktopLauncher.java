package com.mygdx.HorrorGame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.HorrorGame.main.MyHorrorGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

        cfg.title = MyHorrorGame.TITLE;
        cfg.width = MyHorrorGame.V_WIDTH * MyHorrorGame.SCALE;
        cfg.height = MyHorrorGame.V_HEIGHT * MyHorrorGame.SCALE;

        new LwjglApplication(new MyHorrorGame(), cfg);
	}
}
