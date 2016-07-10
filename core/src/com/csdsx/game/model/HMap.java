package com.csdsx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.csdsx.game.util.Log;

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
    public Comb[] combs;//61 sizes which to draw the comb map
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
            if(comb== null || comb.hited) {
                continue;
            }
            comb.type = 0;
        }
    }

    public void hitMap(int[] data, int type) {
//        cur_data = new int[data];
        cur_data = data;
        cur_type = type;
        for(int i = 0; i < data.length; i+=2) {
            int x = data[i];
            int y = data[i+1];

            int index = (y-1)*9+x;
            boolean flag = true;
//            for(int k = 0; k < noShow.length; k++) {
//                if(noShow[k] == index) {
//                    flag = false;
//                    break;
//                }
//            }
            if(flag) {
                combs[index-1].type = type;
            }
        }
    }
    public int[] cur_data;
    public int cur_type;

    public boolean doHit() {
        if(cur_data == null) {
            return false;
        }
        for(int i = 0; i < cur_data.length; i+=2) {
            int x = cur_data[i];
            int y = cur_data[i+1];

            int index = (y-1)*9+x;
            combs[index-1].type = cur_type;
            combs[index-1].hited = true;
        }
        cur_data = null;
        cur_type = 0;
        return true;
    }

    /*
    3 4 5 6 7
   2 3 4 5 6 7
  2 3 4 5 6 7 8
 1 2 3 4 5 6 7 8
1 2 3 4 5 6 7 8 9
 1 2 3 4 5 6 7 8
  2 3 4 5 6 7 8
   2 3 4 5 6 7
    3 4 5 6 7


    1 1 1 1 1
   2 2 2 2 2 2
  3 3 3 3 3 3 3
 4 4 4 4 4 4 4 4
5 5 5 5 5 5 5 5 5
 6 6 6 6 6 6 6 6
  7 7 7 7 7 7 7
   8 8 8 8 8 8
    9 9 9 9 9

3 1  4 1  5 1  6 1  7 1
1 5  1 4  2 3  2 2  3 1
3 9  2 8  2 7  1 6  1 5

 1   2   3   4   5   6   7   8   9
  10  11  12  13  14  15  16  17  18
19  20  21  22  23  24  25  26  27
  28  29  30  31  32  33  34  35  36
37  38  39  40  41  42  43  44  45
  46  47  48  49  50  51  52  53  54
55  56  57  58  59  60  61  62  63
  64  65  66  67  68  69  70  71  72
73  74  75  76  77  78  79  80  81

	    3   4   5   6   7
      11  12  13  14  15  16
    20  21  22  23  24  25  26
  28  29  30  31  32  33  34  35
37  38  39  40  41  42  43  44  45
  46  47  48  49  50  51  52  53
    56  57  58  59  60  61  62
      65  66  67  68  69  70
        75  76  77  78  79
     */

    public static int[][] line= {
        {3  ,4  ,5  ,6  ,7},
        {11  ,12  ,13  ,14  ,15  ,16},
        {20  ,21  ,22  ,23  ,24  ,25  ,26},
        {28 , 29  ,30  ,31 , 32 , 33 , 34  ,35},
        {37  ,38  ,39  ,40  ,41  ,42  ,43  ,44  ,45},
        {46  ,47  ,48  ,49  ,50  ,51  ,52  ,53},
        {56  ,57  ,58  ,59  ,60  ,61  ,62},
        {65  ,66  ,67  ,68  ,69  ,70},
        {75  ,76  ,77  ,78  ,79},

            {3 ,11 ,20 ,28 ,37},
            {4 ,12 ,21 ,29 ,38 ,46},
            {5 ,13 ,22 ,30 ,39 ,47 ,56},
            {6 ,14 ,23 ,31 ,40 ,48 ,57 ,65},
            {7 ,15 ,24 ,32 ,32 ,41 ,49 ,58 ,66 ,75},
            {16 ,25 ,33 ,42 ,50 ,59 ,67 ,76},
            {26 ,34 ,43 ,51 ,60 ,68 ,77},
            {35 ,44 ,52 ,61 ,69 ,78},
            {45 ,53 ,62 ,70 ,79},

            {7 ,16 ,26 ,35 ,45},
            {6 ,15 ,25 ,34 ,44 ,53},
            {5 ,14 ,24 ,33 ,43 ,52 ,62},
            {4 ,13 ,23 ,32 ,42 ,51 ,61 ,70},
            {3 ,12 ,22 ,31 ,41 ,50 ,60 ,69 ,79},
            {11 ,21 ,30 ,40 ,49 ,59 ,68 ,78},
            {20 ,29 ,39 ,48 ,58 ,67 ,77},
            {28 ,38 ,47 ,57 ,66 ,76},
            {37 ,46 ,56 ,65 ,75}
    };

    public void compute() {
        for (int i = 0; i < line.length; i++) {
            int b_len = line[i].length;
            boolean flag = true;
            for(int j = 0; j < b_len; j++) {
                flag = flag&combs[line[i][j]-1].hited;
            }
            if(flag) {
                Log.debug("","zzzzzzzzzzzzzzzzzzz");
                for(int j = 0; j < b_len; j++) {
                    combs[line[i][j]-1].hited = false;
                }
            }
        }

    }
}
