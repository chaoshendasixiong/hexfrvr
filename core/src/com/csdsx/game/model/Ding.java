package com.csdsx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.csdsx.game.util.Log;
import com.csdsx.game.view.MenuScreen;

/**
 * Created by Administrator on 2016-07-09.
 */
public class Ding extends Group{
    public int sub_type;
    public int type;//
    public int turn_index;
    public Texture texture;
    public Comb[] combs;
    public CombActor[] combActors;
    public static float scale =3/5f;
    public static float padding_x = 0;
    public static float padding_y = 500;
    public static float padding_st = 135;

    public float offset_x;
    public float offset_y;

    public float build_x;
    public float build_y;

    public boolean flag;
    public static int[][] dataSet= {
        {1,1},
        {2,1,3,1,1,2,2,2},
        {2,1,1,2,2,2,2,3},
        {1,1,2,1,1,2,2,2,},
        {1,1,1,2,2,3,2,4},
        {3,1,2,2,2,3,1,4},
        {1,1,2,1,3,1,4,1},
        {2,1,3,1,1,2,3,2},
        {1,1,2,1,2,2,2,3},
        {2,1,2,2,1,3,2,3},
        {1,1,1,2,2,2,3,1},
        {2,1,1,2,2,3,3,3},
        {1,1,2,1,3,1,1,2},
        {2,1,1,2,2,2,3,3},
        {1,1,2,1,1,2,1,3},
        {3,1,1,2,2,2,3,2},
        {1,1,1,2,2,2,2,3},
        {3,1,1,2,2,2,2,3},
        {2,1,1,2,2,2,3,2},
        {1,1,2,1,1,2,2,3},
        {2,1,1,2,2,2,1,3},
        {1,1,2,1,3,1,2,2}
    };

    public Ding(int sub_type, int turn_index) {
        this.sub_type = sub_type;
        this.turn_index = turn_index;
        offset_x = 20+turn_index*padding_st;
        offset_y = padding_y;
        String name = "";
        if(sub_type == 1) {
            name = "dian.png";
            type = 1;
        }else if (sub_type >=2 && sub_type <=4) {
            name = "ling.png";
            type = 2;
        }else if (sub_type >=5 && sub_type <=7) {
            name = "lang.png";
            type = 3;
        }else if (sub_type >=8 && sub_type <=12){
            name = "huan.png";
            type = 4;
        }else if (sub_type >=13 && sub_type <=17) {
            name = "qu.png";
            type = 5;
        }else if(sub_type >= 18 && sub_type <=22) {
            name = "zhe.png";
            type = 6;
        }else {

        }
        Log.debug("", name+"    "+sub_type);
        texture = new Texture(name);
        if(sub_type == 1) {
            combs = new Comb[1];
            combActors = new CombActor[1];
        }else{
            combs = new Comb[4];
            combActors = new CombActor[4];
        }
        initDing();
        this.setScale(scale);

        setOrigin(0,0);
        this.setPosition(offset_x, offset_y);
//        setWidth(200);
//        setHeight(200);
//        setOrigin(combActors[0].getX(), combActors[0].getY());
//        setOrigin(200,360);
//        setOrigin(405,-560);
    }

    public boolean selected;

    private void initDing() {
        for (int i = 0; i < combs.length; i++) {
            int x = dataSet[sub_type-1][i*2];
            int y = dataSet[sub_type-1][i*2+1];
            combs[i] = new Comb(x, y);
//            combs[i].pos_x += offset_x;
//            combs[i].pos_y += offset_y;
            Log.debug("",combs[i].pos_x+"   "+combs[i].pos_y );
            combActors[i] = new CombActor(combs[i],texture);
            this.addActor(combActors[i]);
            combActors[i].addListener(new DragListener() {
                private float startDragX;
                private float startDragY;

                private float start_x;
                private float start_y;

                @Override
                public void dragStart(InputEvent event, float x, float y, int pointer) {
                    super.dragStart(event, x, y, pointer);
                    startDragX = x;
                    startDragY = y;
                    start_x = event.getTarget().getParent().getX();
                    start_y = event.getTarget().getParent().getY();
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                    ScaleToAction action = Actions.scaleTo(1.0f, 1.0f, 0.2f);
                    Actor actor = event.getTarget().getParent();
                    actor.addAction(action);
                    Ding ding = (Ding)actor;
                    ding.selected = true;
//                    Ding ding = (Ding)actor;
//                    for(CombActor combActor:ding.combActors) {
//                        combActor.addAction(action);
//                    }
                    return super.touchDown(event, x, y, pointer, button);

                }




                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                    MoveByAction action = Actions.moveBy(0,100,0.5f);
                    Actor actor = event.getTarget().getParent();
                    Ding ding = (Ding)actor;
                    ding.selected = false;
                    MoveToAction action1 = Actions.moveTo(ding.offset_x, offset_y, 0.2f);
                    ScaleToAction action2 = Actions.scaleTo(scale, scale, 0.2f);
                    ParallelAction parallelAction = new ParallelAction(action1, action2);
                    actor.addAction(parallelAction);
                    super.touchUp(event, x, y, pointer, button);
                }

                @Override
                public void drag(InputEvent event, float x, float y, int pointer) {
                    super.drag(event, x, y, pointer);
                    Actor actor = event.getTarget().getParent();
                    float x0 = actor.getX();
                    float y0 = actor.getY();
                    if (x != startDragX) {
                        x0 += x - startDragX;
                    }
                    if (y != startDragY) {
                        y0 += y - startDragY;
                    }
                    actor.setPosition(x0, y0);
                }

                public void dragStop(InputEvent event, float x, float y, int pointer) {
                    super.dragStop(event, x, y, pointer);
                }
            });
        }
    }

    public void resetPos() {
        this.setPosition(offset_x, offset_y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
//        batch.end();
//        batch.begin();
//        for(Comb comb:combs) {
//            batch.draw(texture, comb.pos_x, comb.pos_y);
//        }
//        batch.end();

    }

    public int[] hit() {
        int[] result = new int[combActors.length*2];
        int index = 0;
        for(CombActor combActor:combActors) {
            float x = combActor.comb.pos_x+Comb.len/2 +this.getX();
            float y = combActor.comb.pos_y+Comb.cell_len+this.getY();
            int[] re = Comb.getLogic_xy(x, y);
            if(re== null) {
                return null;
            }
            result[index++] = re[0];
            result[index++] = re[1];
        }
        return result;
    }

}
