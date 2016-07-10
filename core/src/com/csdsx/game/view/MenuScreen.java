package com.csdsx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.csdsx.game.control.GameControl;
import com.csdsx.game.model.HexMap;
import com.csdsx.game.model.Player;
import com.csdsx.game.model.Unit;
import com.csdsx.game.model.UnitFactory;
import com.csdsx.game.util.*;

/**
 * Created by Administrator on 2016-07-01.
 */
public class MenuScreen extends BaseScreen {
    TextureRegion s;
    Sprite sp;
    public static final String tag = "MenuScreen";
    HexMap map;
    Unit[] units;
    public static Unit cur_unit = null;

    @Override
    public void onInit() {
        units = new Unit[3];
        /*

        // 移动操作
        MoveByAction action1 = Actions.moveBy(250, 220, 3);
        ScaleByAction action2 = Actions.scaleBy(3, 3, 3);
        RotateByAction action3 = Actions.rotateBy(90, 3);

        // 还原操作
        ScaleToAction action4 = Actions.scaleTo(1, 1);
        RotateToAction action5 = Actions.rotateTo(0);
        MoveToAction action6 = Actions.moveTo(800 / 2, 480 / 2);

        // 顺序执行action
        SequenceAction sequenceAction = Actions.sequence(action1, action2,
                action3, action4, action5, action6);
        sequenceAction.setActor(player);

        // 无限循环三个动作
        RepeatAction epeatAction = Actions.repeat(RepeatAction.FOREVER,
                sequenceAction);

//        player.addAction(action1);
//        Actions.addAction(action1, player);
*/
        /*
        Player player = new Player();
        StageWrapper.baseStage.addActor(player);
*/
        map = new HexMap();
        map.addStage();
        for(int i = 0; i < 3; i++) {
            int id = MathUtils.random(1, 22);
            units[i] = UnitFactory.createUnit(id, i);
            StageWrapper.baseStage.addActor(units[i]);
        }
        this.control = new GameControl();
        Log.debug(tag, "onInit");
    }

    @Override
    public void onLogic() {
        //判断部件在map上的地图是否匹配 匹配就显示图形
        if(MenuScreen.cur_unit != null) {
            float x = cur_unit.getOd_x()+cur_unit.getX();
            float y = cur_unit.getOd_y()+cur_unit.getY();
            char[] result = CellXY.getLogic_xy(x, y);
            if(result == null) {
                return;
            }
            map.show(result[0]-'0', result[1]-'0', cur_unit.getType_id());
            Log.debug(tag, String.valueOf(result));
        }
    }
}
