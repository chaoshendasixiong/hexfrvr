package com.csdsx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.csdsx.game.MyGame;

/**
 * Created by Administrator on 2016-07-13.
 */
public class Score extends Actor{
    public static int ADD_SCORE = 40;
    public int score;
    BitmapFont bitmapFont;
    public static String FONT_NAME = "font.fnt";
    public static String FONT_PNG = "font.png";


    public Score() {
        bitmapFont = new BitmapFont(Gdx.files.internal(FONT_NAME),
                Gdx.files.internal(FONT_PNG), true, false);
        bitmapFont.getRegion().flip(false, true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        String socre_str = String.valueOf(score);
        bitmapFont.draw(batch, socre_str, MyGame.GAME_WIDTH-100,0);
    }
    public void addSuccess() {
        score+=ADD_SCORE;
    }

    public void resetScore() {
        score = 0;
    }

    public void addScore(int score) {
        this.score+=score;
    }
}
