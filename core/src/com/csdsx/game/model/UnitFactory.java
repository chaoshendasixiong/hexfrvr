package com.csdsx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.csdsx.game.util.Log;
import com.csdsx.game.view.MenuScreen;

import javax.swing.*;

/**
 * Created by Administrator on 2016-07-07.
 */
public class UnitFactory {
    enum UnitType{
        //6种图形+旋转 最后共22种
        NULL(0,""),
        dian(1,"dian"),
        ling1(2, "ling1"),
        ling2(3,"ling2"),
        ling3(4,"ling3"),
        lang1(5,"lang1"),
        lang2(6,"lang2"),
        lang3(7,"lang3"),
        huan1(8,"huan1"),
        huan2(9,"huan2"),
        huan3(10,"huan3"),
        huan4(11,"huan4"),
        huan5(12,"huan5"),
        qu1(13,"qu1"),
        qu2(14,"qu2"),
        qu3(15,"qu3"),
        qu4(16,"qu4"),
        qu5(17,"qu5"),
        zhe1(18,"zhe1"),
        zhe2(19,"zhe2"),
        zhe3(20,"zhe3"),
        zhe4(21,"zhe4"),
        zhe5(22,"zhe5");
        private  int value;
        private String name;
        private UnitType(int value, String name) {
            this.value = value;
            this.name  = name;
        }
        public int getValue() {
            return value;
        }

        public String getName() {
            return this.name;
        }
        public static UnitType get(int value) {
            for(UnitType unitType:UnitType.values()) {
                if(unitType.getValue() == value) {
                    return unitType;
                }
            }
            return NULL;
        }
    }
    public static Unit createUnit(int type, int index) {
        UnitType unitType = UnitType.get(2);
        Log.debug("",unitType.getName());
//        switch (unitType){
//            case dian:
        final Unit unit = new Unit(unitType.getName(), type, index);
        switch (unitType) {
            case dian :case ling3:case lang1:case lang3:case huan2:
            case huan4:case qu1:case qu3:case qu5:case zhe3:case zhe5:
                unit.setOdPos(15,18);
                break;
            case ling2:case ling1:case huan1:case huan5:case qu2:case zhe2:
                unit.setOdPos(30, 18);
                break;
            case huan3:case zhe4:
                unit.setOdPos(45, 18);
                break;
            case lang2:case qu4:case zhe1:
                unit.setOdPos(60,18);
                break;
        }
                unit.addListener(new DragListener() {
                    private float startDragX;
                    private float startDragY;
                    @Override
                    public void dragStart(InputEvent event, float x, float y, int pointer) {
                        super.dragStart(event, x, y, pointer);
                        startDragX = x;
                        startDragY = y;
                    }

                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        ScaleToAction action = Actions.scaleTo(1.2f, 1.2f, 0.3f);
                        event.getTarget().addAction(action);
                        MenuScreen.cur_unit = unit;
                        return super.touchDown(event, x, y, pointer, button);

                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        ScaleToAction action = Actions.scaleTo(1.0f, 1.0f, 0.3f);
                        event.getTarget().addAction(action);
                        MenuScreen.cur_unit = null;
                        super.touchUp(event, x, y, pointer, button);
                    }

                    @Override
                    public void drag(InputEvent event, float x, float y, int pointer) {
                        super.drag(event, x, y, pointer);
                        Actor actor = event.getTarget();
                        float x0 = actor.getX();
                        float y0 = actor.getY();
                        if (x != startDragX)
                        {
                            x0 += x - startDragX;
                        }
                        if (y != startDragY)
                        {
                            y0 += y - startDragY;
                        }
                        actor.setPosition(x0, y0);
                    }

                    public void dragStop(InputEvent event, float x, float y, int pointer) {
                        super.dragStop(event, x, y, pointer);
                    }
                });
//                break;
//            case lang1:
//            default:
//                break;
//        }
        return unit;
    }
}
