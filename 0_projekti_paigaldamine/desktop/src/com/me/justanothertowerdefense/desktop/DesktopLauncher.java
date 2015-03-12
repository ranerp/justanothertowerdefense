package com.me.justanothertowerdefense.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.me.justanothertowerdefense.JustAnotherTowerDefense;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "just-another-tower-defense";
        config.useGL30 = false;
        config.width = 480;
        config.height = 320;

		new LwjglApplication(new JustAnotherTowerDefense(), config);
	}
}
