package org.java.ecluse.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Graphics.DisplayMode;
import org.java.ecluse.Ecluse;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// DisplayMode primaryMode = config.getDesktopDisplayMode();
		// config.title = "Ecluse simulation";
		// config.setFromDisplayMode(primaryMode);
		// config.vSyncEnabled = true;
		config.initialBackgroundColor = Color.NAVY;
		config.resizable = false;
		config.height = 1080;
		config.width = 2048;
		new LwjglApplication(new Ecluse(), config);
	}
}
