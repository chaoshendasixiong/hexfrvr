package com.csdsx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.csdsx.game.model.HexMap;
import com.csdsx.game.model.Player;
import com.csdsx.game.model.Unit;
import com.csdsx.game.model.UnitFactory;
import com.csdsx.game.util.BaseScreen;
import com.csdsx.game.util.BaseStage;
import com.csdsx.game.util.Log;
import com.csdsx.game.util.StageWrapper;

/**
 * Created by Administrator on 2016-07-01.
 */
public class MenuScreen extends BaseScreen {
    TextureRegion s;
    Sprite sp;
    public static final String tag = "MenuScreen";
    HexMap map;
    Unit[] units;
    @Override
    public void onInit() {
        units = new Unit[3];
        /*
        Player player = new Player();
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

        StageWrapper.baseStage.addActor(player);
        */
        HexMap map = new HexMap();
        map.addStage();
        for(int i = 0; i < 3; i++) {
            int id = MathUtils.random(1, 22);
            units[i] = UnitFactory.createUnit(id, i);
            StageWrapper.baseStage.addActor(units[i]);
        }
        Log.debug(tag, "onInit");
    }

    @Override
    public void onLogic() {
    }
}
