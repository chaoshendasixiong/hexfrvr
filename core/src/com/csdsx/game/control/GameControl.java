package com.csdsx.game.control;

import com.badlogic.gdx.math.MathUtils;
import com.csdsx.game.model.*;
import com.csdsx.game.util.Log;
import com.csdsx.game.util.StageWrapper;

/**
 * Created by Administrator on 2016-07-08.
 */
public class GameControl implements IControl{
    HMap map;
    Ding[] dings;
    public GameControl() {
        this.map = new HMap();
        StageWrapper.baseStage.addActor(map);
        dings = new Ding[3];
        randomDIng();

    }

    private void randomDIng() {
        for(int i = 0; i < dings.length; i++) {
            int id = MathUtils.random(1, 22);
            dings[i] = new Ding(id, i);
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
                }

                map.hitMap(result, ding.type);
            }
        }
    }
}
