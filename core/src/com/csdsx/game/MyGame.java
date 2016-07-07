package com.csdsx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.csdsx.game.util.BaseGame;
import com.csdsx.game.util.Log;
import com.csdsx.game.util.StageWrapper;

/**
 * Created by Administrator on 2016-07-01.
 */
public class MyGame extends BaseGame {
    public static final int GAME_WIDTH = 405;
    public static final int GAME_HEIGHT = 720;
    public float screen_width;
    public float screen_height;

    public static final String tag = "MyGame";
    @Override
    public void create() {
        super.create();
        Log.debug(tag, "create");
        screen_width = Gdx.graphics.getWidth();
        screen_height = Gdx.graphics.getHeight();
        StageWrapper.initStage(GAME_WIDTH, GAME_HEIGHT);
        setScreen(new MenuScreen());
    }
}
