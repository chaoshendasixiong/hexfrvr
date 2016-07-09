package com.csdsx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;
import com.csdsx.game.util.CellXY;
import com.csdsx.game.util.Log;

import java.io.Serializable;

/**
 * 六边形格子类
 * Created by Administrator on 2016-06-30.
 */
public class Cell extends Actor{
    //颜色
    private Color color;
    //逻辑坐标x
    private int x;
    //逻辑坐标y
    private int y;

    private float pos_x;
    private float pos_y;

    private Texture mTexture;
    private TextureRegion mTextureRegion;
    public Cell(int x, int y, String name) {
        this.x = x;
        this.y = y;
        initPos();
        initSprite(name);

    }

    private void initSprite(String name) {
        this.mTexture = new Texture(Gdx.files.internal(name));
        this.mTextureRegion = new TextureRegion(this.mTexture);
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Actor actor = event.getTarget();

                actor.setColor(Color.BROWN);
//                ScaleByAction action2 = Actions.scaleBy(3, 3, 3);
//                actor.addAction(action2);
            }
        });
        this.setHeight(this.mTextureRegion.getRegionHeight());
        this.setWidth(this.mTextureRegion.getRegionWidth());
    }

    private void initPos() {

        this.pos_x = CellXY.getX(x, y);
        this.pos_y = CellXY.getY(y);
        this.setPosition(pos_x, pos_y, Align.center);
        Log.debug("=========", String.valueOf(this.x) + " " + String.valueOf(this.y));
        Log.debug("=========",String.valueOf(this.pos_x)+" "+String.valueOf(this.pos_y));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(mTextureRegion, getX(), getY(),
                mTextureRegion.getRegionWidth() / 2,
                mTextureRegion.getRegionHeight() / 2,
                mTextureRegion.getRegionWidth(),
                mTextureRegion.getRegionHeight(), getScaleX(), getScaleY(),
                getRotation());
    }
}
