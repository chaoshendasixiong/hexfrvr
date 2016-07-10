package com.csdsx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * Created by Administrator on 2016-07-09.
 */
public class HMap extends Actor{
    /*
    Texture cell_texture;
    Texture dian_texture;
    Texture ling_texture;
    Texture lang_texture;
    Texture huan_texture;
    Texture qu_texture;
    Texture zhe_texture;
    */
    Texture[] textures;
    Comb[] combs;//61 sizes which to draw the comb map
    //which 9x9 map to remove
    static int[] noShow = {1,2,8,9,10,17,18,19,27,36,54,55,63,64,71,72,73,74,80,81};
    static String[] names = {"cell.png", "dian.png", "ling.png", "lang.png","huan.png","qu.png","zhe.png"};
    public HMap() {
        // new textures
        textures = new Texture[7];
        for(int i = 0; i < 7; i++) {
            textures[i] = new Texture(names[i]);
        }


        //new combs
        combs = new Comb[81];
        int index = 1;
        int combs_idx = 0;
        for(int i = 1; i<= 9; i++) {
            for(int j = 1; j <= 9; j++ ,index++){
                boolean flag = true;
                for(int k = 0; k < noShow.length; k++) {
                    if(noShow[k] == index) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    combs[combs_idx++] = new Comb(j,i);
                    System.out.println(combs_idx);
                }else{
                    combs[combs_idx++] = null;
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for(Comb comb:combs) {
            if(comb!= null) {
                batch.draw(textures[comb.type], comb.pos_x, comb.pos_y);
            }
        }
    }

    public void draw(Batch batch) {
        batch.begin();
        for(Comb comb:combs) {
            batch.draw(textures[comb.type], comb.pos_x, comb.pos_y);
        }
        batch.end();
    }
    public void reset() {
        for(Comb comb:combs) {
            if(comb== null) {
                continue;
            }
            comb.type = 0;
        }
    }

    public void hitMap(int[] data, int type) {

        for(int i = 0; i < data.length; i+=2) {
            int x = data[i];
            int y = data[i+1];

            int index = (y-1)*9+x;
            boolean flag = true;
            for(int k = 0; k < noShow.length; k++) {
                if(noShow[k] == index) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                combs[index-1].type = type;
            }
        }
    }
}
