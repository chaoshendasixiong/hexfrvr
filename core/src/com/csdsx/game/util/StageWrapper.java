package com.csdsx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.csdsx.game.control.IControl;

/**
 * Created by Administrator on 2016-07-01.
 */
public class StageWrapper {
    public static final String tag = "StageWrapper";
    public static BaseStage baseStage;
    private static SpriteBatch spriteBatch;


    /**
     * 简单对stage的包装
     * 负责管理 如游戏窗口大小
     * 接受输入事件 stage实现了inputProcessor接口
     * 同时负责绘制 通过stage的batch来操作
     */
    public static void initStage(int w, int h) {
        Log.debug(tag, "initStage");
        spriteBatch = new SpriteBatch();
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(true, w, h);
        ScalingViewport viewport = new ScalingViewport(Scaling.stretch, w, h, camera);
        baseStage = new BaseStage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(baseStage);
//        baseStage.addListener(new BaseListener());
    }

    public static void update() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        baseStage.act(Gdx.graphics.getDeltaTime());
        baseStage.draw();
    }
}
