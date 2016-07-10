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
    public HMap() {
        // new textures
        textures = new Texture[1];
        for(int i = 0; i < 1; i++) {
            textures[i] = new Texture("cell.png");
        }


        //new combs
        combs = new Comb[61];
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
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for(Comb comb:combs) {
            batch.draw(textures[comb.type], comb.pos_x, comb.pos_y);
        }
    }

    public void draw(Batch batch) {
        batch.begin();
        for(Comb comb:combs) {
            batch.draw(textures[comb.type], comb.pos_x, comb.pos_y);
        }
        batch.end();
    }
}
