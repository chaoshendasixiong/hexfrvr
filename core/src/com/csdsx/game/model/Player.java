package com.csdsx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.csdsx.game.MyGame;

/**
 * Created by Administrator on 2016-07-01.
 */
public class Player extends Actor {
    private Pixmap mPixmap;
    private Texture mTexture;
    private TextureRegion mTextureRegion;
    private Sprite mSprite;
    private float timer;
    public Player()
    {
        mPixmap = new Pixmap(Gdx.files.internal("cell.png"));
        mTexture = new Texture(mPixmap);
        mTextureRegion = new TextureRegion(mTexture);
        mSprite = new Sprite(mTextureRegion);
        mSprite.setPosition(0,0);
//        mSprite.setColor(Color.BLUE);
//        this.setScale(0.5f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
//        timer += Gdx.graphics.getDeltaTime();
//        // 1.移动
//        if (timer < 3)
//        {
//            // 每秒移动50像素
//            mSprite.translateX(50 * Gdx.graphics.getDeltaTime());
//        }
//        // 2.放缩
//        else if (timer > 3 && timer < 6)
//        {
//            // 每秒xy方向上放大1倍
//            mSprite.setPosition(MyGame.GAME_WIDTH / 2, MyGame.GAME_HEIGHT / 2);
//            mSprite.scale(1 * Gdx.graphics.getDeltaTime());
//        }
//        // 3.翻转
//        else if (timer > 6 && timer < 9)
//        {
//            // 每秒旋转90度
//            mSprite.setScale(1, 1);
//            mSprite.rotate(90 * Gdx.graphics.getDeltaTime());
//        }
//        else
//        {
//            timer = 0;
//            mSprite.setPosition(MyGame.GAME_WIDTH / 2, MyGame.GAME_HEIGHT / 2);
//            mSprite.setScale(1, 1);
//            mSprite.setRotation(0);
//        }
        mSprite.draw(batch);
//        batch.draw(mTextureRegion, getX(), getY(),
//                mTextureRegion.getRegionWidth() / 2,
//                mTextureRegion.getRegionHeight() / 2,
//                mTextureRegion.getRegionWidth(),
//                mTextureRegion.getRegionHeight(), getScaleX(), getScaleY(),
//                getRotation());
    }
}
