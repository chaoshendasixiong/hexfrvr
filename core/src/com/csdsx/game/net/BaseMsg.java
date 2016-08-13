package com.csdsx.game.net;

/**
 * Created by Administrator on 2016-07-23.
 */
public class BaseMsg {
    String msg;
    public BaseMsg(String msg) {
        this.msg = msg;
    }

    public void ToByte() {

    }

    public byte[] getBytes(){
        return msg.getBytes();
    }
}
