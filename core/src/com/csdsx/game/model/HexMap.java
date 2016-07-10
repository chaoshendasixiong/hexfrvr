package com.csdsx.game.model;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.csdsx.game.util.Log;
import com.csdsx.game.util.StageWrapper;

/**
 * 9x9的 共有61个格子
 * Created by Administrator on 2016-07-05.
 */
public class HexMap {
    int x_size = 9;
    int y_size = 9;
    //使用数组来作为地图
    Cell[] map = new Cell[81];
/*
1 1,2,8,9    2,1, 2,8 2,9  3,1  3,9  4,9 6,9 7,1 7,9  8,1 8,8 8,9    9 1,2,8,9
1,2,8,9,10,17,18,19,27,36,54,55,63,64,71,72,73,74,80,81
 */
    static int[] noShow = {1,2,8,9,10,17,18,19,27,36,54,55,63,64,71,72,73,74,80,81};
    public HexMap() {
        int i = 1;
        int j = 1;
        int index = 0;
        for(; i <= 9; i++) {
            for(; j <= 9; j++) {
                boolean flag = true;
                for(int k = 0; k < noShow.length; k++) {
                    if(index + 1 == noShow[k]) {
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    map[index] = new Cell(j, i, "cell.png");

                }else{
                    map[index] = null;
                }
                index++;
            }
            j=1;
        }
    }
    public void addStage() {
        int i = 1;
        int j = 1;
        int index = 0;
        for(; i <= 9; i++) {
            for(; j <= 9; j++) {
                if(map[index] != null) {
                    StageWrapper.baseStage.addActor(map[index]);
                }
                index++;
            }
            j=1;
        }
    }

    public void show(int x, int y, int type_id) {
        int index = (y-1)*9+x-1;
        for(int k = 0; k < noShow.length; k++) {
            if(index + 1 == noShow[k]) {
                return;
            }
        }
        int[] addons = new int[4];
        addons[0] = 0;
        switch (type_id){
            case 1:
                addons = new int[1];
                addons[0] = 0;
                break;
            case 2:
                //y =1 x=[3,6]
                //y =2 x=[2,6]
                //y=3 x=[2,7]
                //y=4 x=[1,7]
                //y=5  x=[2,8]
                //y=6 x=[2,7]
                //y=7 x=[3,7]
                //y=8 x=[3,6]
                //y=9 x=//
                addons[1] = 1;
                addons[2] = 8;
                addons[3] = 9;
                break;
        }
        for(int i = 0; i< addons.length;i++) {
            map[index+i].setNewTexture("cell_.png");
//            StageWrapper.baseStage.addActor(map[index]);
        }

    }
}
