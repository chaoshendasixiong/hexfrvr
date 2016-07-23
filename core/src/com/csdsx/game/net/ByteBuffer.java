package com.csdsx.game.net;

import java.io.InputStream;
import java.util.Arrays;
import java.util.jar.Pack200;

/**
 * 每次取出一个满足条件的包
 * 缓冲区可能存在多个包
 * Created by Administrator on 2016-07-23.
 */
public class ByteBuffer {
    public byte[] buffer;

    public static int BIG_ENDIAN = 0;
    public static int LITTLE_ENDIAN = 1;

    public static int ENDIAN  = BIG_ENDIAN;

    public static int HEAD_LEN = 2;

    public static int BUF_LEN = 512;

    public static byte[] BUF = new byte[BUF_LEN];

    public static int NEED_LEN = 0;


    /*

     InputStream in = socket.getInputStream();
                try {
                    int avail_len = in.available();
                    //是否粘包
                    if(NEED_LEN > 0 ) {
                        //读取数据到缓冲区 在判断能否组成一个新的包
                        if(avail_len < NEED_LEN) {
                            NEED_LEN = NEED_LEN - avail_len;
                            int new_len = buffer.length+NEED_LEN;

                        }
                    }else{
                        if(avail_len <= HEAD_LEN) {
                            //数据错误 直接关闭连接
                            return;
                        }else{
                            //读取包头验证数据有效性

                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true) {
                    char[] head = new char[HEAD_LEN];
                    try {
                        buffer
                        int len = buffer.read(head, 0, HEAD_LEN);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
     */

    public InputStream in;
    public ByteBuffer(InputStream in) {
        this.in = in;
    }

    public byte[] getData() throws Exception{
        //从inputstream中读取一个数据
        int avail_len = in.available();
        byte[] buf = new byte[avail_len];
        in.read(buf, 0, avail_len);
        int last_len = 0;
        if(buffer!= null) {
            last_len = buffer.length;
        }
        byte[] new_buffer = new byte[avail_len+last_len];
        if(last_len > 0) {
            System.arraycopy(buffer, 0, new_buffer, 0, last_len);
        }
        System.arraycopy(buf, 0, new_buffer, last_len, avail_len);
        buffer = new_buffer;

        return getPacket();
    }

    public byte[] getPacket() {
        int len = buffer.length;
        if(len <= HEAD_LEN) {
            return null;
        }else{
            int content_len = read_HEAD_LEN();
            if(content_len < len - HEAD_LEN) {
                byte[] result = new byte[content_len];
                System.arraycopy(buffer, HEAD_LEN, result, 0, content_len);
                int over_len = len - HEAD_LEN - content_len;
                byte[] new_buffer = new byte[over_len];
                System.arraycopy(buffer, HEAD_LEN + content_len, new_buffer, 0, over_len);
                buffer = new_buffer;
                return result;
            }
            else{
                return null;
            }
        }
    }

    public int read_HEAD_LEN() {
        if(HEAD_LEN == 2) {
            return readShort(buffer);
        }else if (HEAD_LEN == 4){
            return readInt(buffer);
        }else{
            return -1;
        }
    }

    public short readShort(byte[] buf) {
        return 0;
    }

    public int readInt(byte[] buf) {
        return 0;
    }
}
