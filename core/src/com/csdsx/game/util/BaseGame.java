package com.csdsx.game.util;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

/**
 * 自定义类似ApplicationAdapter
 * 加入了其它方法
 * 游戏由多个screen组成
 * game-screen模式 一个Game 多个screen 通过setScreen来切换
 * 我们通过ApplicationListener和screen来实现screen的操作
 * Created by Administrator on 2016-07-01.
 * Created by Administrator on 2016-07-01.
 */
public class BaseGame implements ApplicationListener {
    private SpriteBatch spriteBatch;
    private BaseScreen currentScreen;
    private float time;

    public void setScreen(BaseScreen screen) {
        currentScreen = screen;
    }


    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        currentScreen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
