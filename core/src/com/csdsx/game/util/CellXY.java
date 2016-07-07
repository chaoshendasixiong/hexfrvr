package com.csdsx.game.util;

/**
 * Created by Administrator on 2016-06-30.
 */
public class CellXY {
    //   蜂窝网络定位
    static int cell_len = 23;
    static float len = (float)(cell_len*1.732508);

    public static float getX(int x, int y) {

        if (y%2==0) {
            return len*x;
        }else{//奇数
            return len*x-len/2;
        }
    }
    public static float getY(int y) {
//        if(y%2 == 0) {
//            int n = y/2-1;
//            return n*3*cell_len+2.5f*cell_len;
//        }else{
//            int n = (3*y-1)/2;
//            return len*n;
//        }
        return y*cell_len*3/2;
    }
}
