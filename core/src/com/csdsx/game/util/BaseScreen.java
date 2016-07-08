package com.csdsx.game.util;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.csdsx.game.control.IControl;

/**
 * 自定义类似ScreenAdapter
 * Created by Administrator on 2016-07-01.
 */
public abstract class BaseScreen implements Screen {

    public BaseGame baseGame;

    public IControl control;

    public IControl getControl() {
        return control;
    }

    public void setControl(IControl control) {
        this.control = control;
    }

    public void setScreen(BaseScreen screen) {
        baseGame.setScreen(screen);
    }

    public void setGame(BaseGame game) {
        this.baseGame = game;
    }

    public abstract void onInit() ;

    public abstract void onLogic();

    public BaseScreen() {
        onInit();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        onLogic();
        StageWrapper.update();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
