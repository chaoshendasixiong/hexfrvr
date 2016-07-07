package com.csdsx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.csdsx.game.MyGame;
import com.csdsx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MyGame.GAME_WIDTH;
		config.height = MyGame.GAME_HEIGHT;
//		new LwjglApplication(new MyGdxGame(), config);
		new LwjglApplication(new MyGame(), config);

	}
}
