package com.csdsx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.List;

/**
 * 部件 共22种
 * 每一个部件都有一个基准点
 * 就是一个cell
 * 根据这个cell来定位map上的格子
 * Created by Administrator on 2016-06-30.
 */
public class Unit extends Actor{
    //种类id 随机生成 根据id来决定形状
    private int type_id;
    //在屏幕上的位置索引 1,2,3 以此来决定坐标
    private int pos_index;
    private Color color;
    private List<Cell> cell_list;
    private Texture mTexture;
    private TextureRegion mTextureRegion;
    private static int padding_x = 20;
    private static int padding_y = 500;
    private static int padding_st = 130;
    public Unit() {
        mTexture = new Texture(Gdx.files.internal("dian.png"));
        this.mTextureRegion = new TextureRegion(this.mTexture);
    }
    public Unit(String name, int type_id, int pos_index) {
        this.type_id = type_id;
        this.pos_index = pos_index;
        mTexture = new Texture(Gdx.files.internal(name+".png"));
        this.mTextureRegion = new TextureRegion(this.mTexture);
        this.setPosition(padding_x+pos_index*padding_st, padding_y);
        this.setHeight(mTextureRegion.getRegionHeight());
        this.setWidth(mTextureRegion.getRegionWidth());
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
