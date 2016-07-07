package com.csdsx.game.util;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by Administrator on 2016-07-01.
 */
public class Log {
    static {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
    }
    public static void debug(String tag, String message) {
        Gdx.app.debug(tag, message);

//        System.out.println(tag+"\t"+message);
    }
}
