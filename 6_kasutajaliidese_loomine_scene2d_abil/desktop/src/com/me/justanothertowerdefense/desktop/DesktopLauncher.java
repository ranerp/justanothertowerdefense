package com.me.justanothertowerdefense.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.me.justanothertowerdefense.JustAnotherTowerDefense;
import com.me.justanothertowerdefense.Settings;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "just-another-tower-defense";
        config.width = Settings.WIDTH;
        config.height = Settings.HEIGHT;

        new LwjglApplication(new JustAnotherTowerDefense(), config);
	}
}
