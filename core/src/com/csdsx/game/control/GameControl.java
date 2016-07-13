package com.csdsx.game.control;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.csdsx.game.MyGame;
import com.csdsx.game.model.*;
import com.csdsx.game.util.Log;
import com.csdsx.game.util.StageWrapper;

/**
 * Created by Administrator on 2016-07-08.
 */
public class GameControl implements IControl{
    HMap map;
    Ding[] dings;
    ImageButton reset;
    Score score;
    public GameControl() {
        this.map = new HMap();
        StageWrapper.baseStage.addActor(map);
        dings = new Ding[3];
        randomDIng();
        reset = new ImageButton(new SpriteDrawable(new Sprite(new Texture("reset.png"))));
        reset.setPosition(0,0);//MyGame.GAME_WIDTH/2,MyGame.GAME_HEIGHT/2);
        reset.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GameControl.this.randomDIng();
            }
        });
        StageWrapper.baseStage.addActor(reset);
        score  = new Score();
        StageWrapper.baseStage.addActor(score);
    }

    private void randomDIng() {
        for(int i = 0; i < dings.length; i++) {
            int id = MathUtils.random(1, 22);
            if(dings[i]!= null) {
                dings[i].remove();
            }
            dings[i] = new Ding(id, i);
            dings[i].gameControl = this;
            StageWrapper.baseStage.addActor(dings[i]);
            Log.debug("", dings[i].getX()+" "+dings[i].getY());
        }
    }

    @Override
    public void control() {
//        this.map.draw(StageWrapper.spriteBatch);
        //判断击中的部件并计算落点 是否符合条件
        map.reset();
        for(Ding ding:dings) {

            if(ding.selected) {
                int[] result = ding.hit();
                if(result == null) {
                    continue;
                }else {
                    map.hitMap(result, ding.type);
                }
            }
        }
        int extra_score = map.compute();
        score.addScore(extra_score);
    }



    public boolean doHit() {

        boolean flag = map.doHit();
        if(flag) {
            score.addSuccess();
        }
        return flag;
    }

    public static int toIndex(int x, int y) {
        return (y-1)*9+x;
    }

    public boolean isHasHit(int x, int y) {
        int index = toIndex(x, y);
        Comb comb = map.combs[index-1];
        if(comb == null || comb.hited) {
            return true;
        }else{
            return false;
        }

    }
}
