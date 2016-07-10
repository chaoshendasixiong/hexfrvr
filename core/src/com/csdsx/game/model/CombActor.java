package com.csdsx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Administrator on 2016-07-09.
 */
public class CombActor extends Actor {
    public Comb comb;
    public TextureRegion textureRegion;
    public CombActor(Comb comb,Texture texture) {
        this.comb = comb;
        this.textureRegion = new TextureRegion(texture);
        this.setWidth(textureRegion.getRegionWidth());
        this.setHeight(textureRegion.getRegionHeight());
//        this.setOrigin(comb.pos_x, comb.pos_y);
        this.setPosition(comb.pos_x, comb.pos_y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, getX(), getY(),
                textureRegion.getRegionWidth() / 2,
                textureRegion.getRegionHeight() / 2,
                textureRegion.getRegionWidth(),
                textureRegion.getRegionHeight(), getScaleX(), getScaleY(),
                getRotation());
    }
}
